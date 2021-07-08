package com.bigave.security;

import com.bigave.security.account.Account;
import com.bigave.security.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = accountService.createAccount("kimtaejun", "1234");

        System.out.println(account.getPassword());
    }
}
