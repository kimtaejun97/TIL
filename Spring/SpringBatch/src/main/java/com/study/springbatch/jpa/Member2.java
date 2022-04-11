package com.study.springbatch.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
public class Member2 {

    @Id
    private String id;
    private String name;

    public Member2(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member2() {
    }
}
