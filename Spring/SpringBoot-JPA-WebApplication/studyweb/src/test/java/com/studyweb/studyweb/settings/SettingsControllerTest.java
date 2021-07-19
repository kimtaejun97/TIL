package com.studyweb.studyweb.settings;

import com.studyweb.studyweb.WithAccount;
import com.studyweb.studyweb.account.AccountRepository;
import com.studyweb.studyweb.account.AccountService;
import com.studyweb.studyweb.domain.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    AccountRepository accountRepository;

    @AfterEach
    void cleanup(){
        accountRepository.deleteAll();
    }

    @WithAccount("bigave")
    @DisplayName("프로필 수정하기 - 입력 값 정상")
    @Test
    void updateProfile() throws Exception{
        mockMvc.perform(post("/settings/profile")
            .param("bio", "짧은 소개 수정")
            .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/settings/profile"))
                .andExpect(flash().attributeExists("message"));

        Account bigave = accountRepository.findByNickName("bigave");
        assertEquals(bigave.getBio(),"짧은 소개 수정");

    }

    @WithAccount("bigave")
    @DisplayName("프로필 수정하기 - 입력 값 에러 : 너 긴 데이터")
    @Test
    void updateProfile_with_wrong_data() throws Exception{
        mockMvc.perform(post("/settings/profile")
                .param("bio", "짧은 소개 수정 long long long long long long long long long long long long long long  long long")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/profile"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"))
                .andExpect(model().hasErrors());

        Account bigave = accountRepository.findByNickName("bigave");
        assertNull(bigave.getBio());

    }

    @WithAccount("bigave")
    @DisplayName("프로필 수정하기 View")
    @Test
    void updateProfileView() throws Exception {

        mockMvc.perform(get("/settings/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/profile"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"));
    }
}