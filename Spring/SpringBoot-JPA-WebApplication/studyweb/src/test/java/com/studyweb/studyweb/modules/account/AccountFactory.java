package com.studyweb.studyweb.modules.account;

import com.studyweb.studyweb.modules.account.form.SignUpForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountFactory {

    private AccountService accountService;

    public Account createAccount(String nickName){
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail(nickName+"@email.com");
        signUpForm.setNickName(nickName);
        signUpForm.setPassword("12345678");

        return accountService.processNewAccount(signUpForm);


    }
}
