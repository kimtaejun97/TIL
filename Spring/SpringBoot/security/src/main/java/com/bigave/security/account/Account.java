package com.bigave.security.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String password;


}
