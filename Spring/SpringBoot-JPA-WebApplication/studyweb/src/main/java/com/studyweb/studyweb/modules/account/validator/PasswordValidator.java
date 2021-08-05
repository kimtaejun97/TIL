package com.studyweb.studyweb.modules.account.validator;

import com.studyweb.studyweb.modules.account.AccountRepository;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.form.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class PasswordValidator implements Validator {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(Password.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Password password = (Password) o;

        if(!password.getNewPassword().equals(password.getCheckPassword())){
            errors.rejectValue("newPassword","wrong.newPassword", "새 패스워드와 다시 입력한 패스워드가 일치하지 않습니다.");
        }

        Account byNickName = accountRepository.findByNickName(password.getNickName());
        if(passwordEncoder.matches(password.getCheckPassword(), byNickName.getPassword()))
            errors.rejectValue("newPassword","wrong.newPassword", "이전과 동일한 비밀번호로 변경할 수 없습니다.");

        }

}
