package com.studyweb.studyweb.modules.account.validator;

import com.studyweb.studyweb.modules.account.AccountRepository;
import com.studyweb.studyweb.modules.account.form.NickNameForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class NickNameValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(NickNameForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NickNameForm nickNameForm = (NickNameForm) o;

        if(accountRepository.existsByNickName(nickNameForm.getNickName())){
            errors.rejectValue("nickName","invalid.nickName","이미 사용중인 닉네임 입니다.");
        }

    }
}
