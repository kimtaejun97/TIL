package com.bigave.security.account;


import lombok.Getter;

import java.io.Serializable;


@Getter
public class SessionAccount implements Serializable {
    private String userName;

    public SessionAccount(Account account){
        this.userName = account.getUserName();
    }
}
