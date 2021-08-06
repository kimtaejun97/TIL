package com.studyweb.studyweb.modules.account;

import com.studyweb.studyweb.infra.MockMvcTest;
import com.studyweb.studyweb.infra.mail.EmailMessage;
import com.studyweb.studyweb.infra.mail.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
public class AccountControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    EmailService emailService;

    @Autowired
    AccountService accountService;

    @DisplayName("회원 가입 화면 보이는지 테스트")
    @Test
    void signUpform() throws Exception {
            mockMvc.perform(get("/sign-up"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("account/sign-up"))
                    .andExpect(model().attributeExists("signUpForm"));
    }

    @DisplayName("회원 가입 처리 - 입력값 오류")
    @Test
    void signUpSubmit_with_wrong_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("nickName","bigave")
                .param("email", "wrong..")
                .param("password","12345")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"))
                .andExpect(unauthenticated());
    }

    @DisplayName("회원 가입 처리 - 입력값 정상")
    @Test
    void signUpSubmit_with_correct_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("nickName","bigave")
                .param("email", "correct@email.com")
                .param("password","12345678")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(authenticated().withUsername("bigave"));


        Account account =accountRepository.findByEmail("correct@email.com");
        assertNotNull(account);
        assertNotEquals(account.getPassword(), "12345678");
        assertNotNull(account.getEmailCheckToken());
        //send 메서드가 호출 되었는지.
        then(emailService).should().send(any(EmailMessage.class));

    }

    @DisplayName("인증 메일 확인 - 입력값 오류")
    @Test
    void checkEmailToken_with_wrong_input() throws Exception {

        mockMvc.perform(get("/check-email-token")
        .param("token", "aaabbb")
        .param("email","test@email.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/checked-email"))
                .andExpect(model().attributeExists("error"))
                .andExpect(unauthenticated());
    }

    @DisplayName("인증 메일 확인 - 입력값 정상")
    @Test
    void checkEmailToken_with_correct_input() throws Exception {
        Account account = Account.builder()
                .nickName("bigave")
                .email("test@email.com")
                .password("12345678")
                .build();
        Account newAccount = accountRepository.save(account);
        newAccount.generateEmailCheckToken();

        mockMvc.perform(get("/check-email-token")
                .param("token", newAccount.getEmailCheckToken())
                .param("email",newAccount.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("account/checked-email"))
                .andExpect(model().attributeDoesNotExist("error"))
                .andExpect(model().attributeExists("numberOfUser"))
                .andExpect(model().attributeExists("nickName"))
                .andExpect(authenticated().withUsername("bigave"));

    }

    @DisplayName("인증 메일 재전송 - 제한 시간(10분) 이전에 시도.")
    @Test
    void resendConfirmEmail_blocked() throws Exception {
        Account account = Account.builder()
                .nickName("bigave")
                .email("test@email.com")
                .password("12345678")
                .build();
        Account newAccount = accountRepository.save(account);
        newAccount.generateEmailCheckToken();

        String prevToken = newAccount.getEmailCheckToken();

        accountService.login(account);


        mockMvc.perform(get("/check-email"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/check-email"))
                .andExpect(model().attribute("email", account.getEmail()));

        mockMvc.perform(get("/resend-confirm-email"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/check-email"))
                .andExpect(model().attributeExists("error"));

    }

    @DisplayName("인증 메일 재전송 - 제한 시간(10분) 이후에 시도.")
    @Test
    void resendConfirmEmail_success() throws Exception {
        Account account = Account.builder()
                .nickName("bigave")
                .email("test@email.com")
                .password("12345678")
                .build();
        Account newAccount = accountRepository.save(account);
        newAccount.generateEmailCheckToken();

        String prevToken = newAccount.getEmailCheckToken();

        accountService.login(account);

        account.setEmailCheckTokenLastGeneration(account.getEmailCheckTokenLastGeneration().minusMinutes(11));


        mockMvc.perform(get("/check-email"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/check-email"))
                .andExpect(model().attribute("email", account.getEmail()));

        mockMvc.perform(get("/resend-confirm-email"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));


        assertThat(prevToken).isNotEqualTo(newAccount.getEmailCheckToken());
        then(emailService).should().send(any(EmailMessage.class));

        mockMvc.perform(get("/check-email-token")
                .param("token", newAccount.getEmailCheckToken())
                .param("email",newAccount.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("account/checked-email"))
                .andExpect(model().attributeDoesNotExist("error"))
                .andExpect(model().attributeExists("numberOfUser"))
                .andExpect(model().attributeExists("nickName"))
                .andExpect(authenticated().withUsername("bigave"));
    }

}