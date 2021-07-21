package com.studyweb.studyweb.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyweb.studyweb.WithAccount;
import com.studyweb.studyweb.account.AccountRepository;
import com.studyweb.studyweb.account.AccountService;
import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Tag;
import com.studyweb.studyweb.settings.form.TagForm;
import com.studyweb.studyweb.tags.TagRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    AccountService accountService;

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


    @WithAccount("bigave")
    @DisplayName("비밀번호 수정하기 View")
    @Test
    void updatePasswordView() throws Exception {
        mockMvc.perform(get("/settings/password"))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/password"))
                .andExpect(model().attributeExists("password"));
    }

    @WithAccount("bigave")
    @DisplayName("비밀번호 수정하기 - 정상 입력")
    @Test
    void updatePassword_correct_input() throws Exception {
        mockMvc.perform(post("/settings/password")
                .param("nickName","bigave")
                .param("newPassword", "11112222")
                .param("checkPassword","11112222")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/settings/password"))
                .andExpect(flash().attributeExists("message"));

        Account account = accountRepository.findByNickName("bigave");
        assertTrue(passwordEncoder.matches("11112222", account.getPassword()));


    }

    @WithAccount("bigave")
    @DisplayName("비밀번호 수정하기 - 이전과 같은 비밀번호")
    @Test
    void updatePassword_same_password() throws Exception {
        mockMvc.perform(post("/settings/password")
                .param("nickName","bigave")
                .param("newPassword", "123123123")
                .param("checkPassword","123123123")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/password"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("password"));

    }

    @WithAccount("bigave")
    @DisplayName("비밀번호 수정하기 - 두 비밀번호 불일치")
    @Test
    void updatePassword_wrong_password() throws Exception {
        mockMvc.perform(post("/settings/password")
                .param("newPassword", "11112222")
                .param("nickName","bigave")
                .param("checkPassword","22221111")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/password"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("password"));

    }

    @WithAccount("bigave")
    @DisplayName("태그 수정 폼")
    @Test
    void updateTagsForm() throws Exception {
        mockMvc.perform(get("/settings/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/tags"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("whitelist"))
                .andExpect(model().attributeExists("tags"));
    }

    @WithAccount("bigave")
    @DisplayName("태그 추가")
    @Test
    void addTag() throws Exception {
        TagForm tagForm = new TagForm();
        tagForm.setTagTitle("spring");

        mockMvc.perform(post("/settings/tags/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tagForm))
                .with(csrf()))
                .andExpect(status().isOk());

        Tag tag = tagRepository.findByTitle("spring");

        assertNotNull(tag);
        assertTrue(accountRepository.findByNickName("bigave").getTags().contains(tag));

    }


    @WithAccount("bigave")
    @DisplayName("태그 제거")
    @Test
    void removeTag() throws Exception {
        Account account = accountRepository.findByNickName("bigave");

        Tag newTag = tagRepository.save(Tag.builder()
                .title("spring")
                .build());
        accountService.addTag(account, newTag);

        TagForm tagForm = new TagForm();
        tagForm.setTagTitle("spring");

        mockMvc.perform(post("/settings/tags/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tagForm))
                .with(csrf()))
                .andExpect(status().isOk());

        Tag tag = tagRepository.findByTitle("spring");

        assertFalse(accountRepository.findByNickName("bigave").getTags().contains(tag));

    }


}