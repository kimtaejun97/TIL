package com.studyweb.studyweb.modules.main;

import com.studyweb.studyweb.infra.AbstractContainerBaseTest;
import com.studyweb.studyweb.infra.MockMvcTest;
import com.studyweb.studyweb.modules.account.AccountRepository;
import com.studyweb.studyweb.modules.account.AccountService;
import com.studyweb.studyweb.modules.account.form.SignUpForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@MockMvcTest

class MainControllerTest  extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void createNewAccount(){
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("test@email.com");
        signUpForm.setNickName("bigave");
        signUpForm.setPassword("12345678");

        accountService.processNewAccount(signUpForm);
    }

    @AfterEach
    void deleteAccount(){
        accountRepository.deleteAll();
    }


    @DisplayName("로그인 - 이메일 정상 입력")
    @Test
    void login_with_email() throws Exception {

        mockMvc.perform(post("/login")
                .param("username","test@email.com")
                .param("password","12345678")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("bigave"));
    }
    @DisplayName("로그인 - 닉네임 정상 입력")
    @Test
    void login_with_nickName() throws Exception {

        mockMvc.perform(post("/login")
                .param("username","bigave")
                .param("password","12345678")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("bigave"));

    }

    @DisplayName("로그인 실패")
    @Test
    void login_failed() throws Exception {

        mockMvc.perform(post("/login")
                .param("username","nonono")
                .param("password","12345718")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(unauthenticated());

    }

    @WithMockUser
    @DisplayName("로그아웃")
    @Test
    void logout() throws Exception {

        mockMvc.perform(post("/logout")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated());

    }

}