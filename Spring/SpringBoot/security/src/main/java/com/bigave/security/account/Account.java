package com.bigave.security.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
