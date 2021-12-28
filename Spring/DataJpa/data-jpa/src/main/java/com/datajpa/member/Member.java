package com.datajpa.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    protected Member() {} // private 로 하면 reflection 이용이 막힌다?

    public Member(String username) {
        this.username = username;
    }
}
