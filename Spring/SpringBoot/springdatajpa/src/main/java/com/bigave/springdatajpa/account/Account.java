package com.bigave.springdatajpa.account;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter @Setter
@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String userName;

    private String password;

    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(userName, account.userName) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password);
    }
}
