package com.studyweb.studyweb.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //TODO email, nickname 중복 여부.
        SignUpForm signUpForm = (SignUpForm) errors;
        if(accountRepository.existsByEmail(signUpForm.getEmail())){
            errors.rejectValue("email","invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일 입니다.");
        }
        if(accountRepository.existsNickName(signUpForm.getNickName())){
            errors.rejectValue("nickName","invalid.nickName", new Object[]{signUpForm.getNickName()}, "이미 사용중인 닉네 입니다.");
        }

    }
}
