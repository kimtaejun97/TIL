package com.study.springbatch.config;

import com.study.springbatch.Member;

public class MemberService {

    private long id= 0;

    public Member readMember() {
        if(id < 10) {
            return new Member(String.valueOf(++id), "user");
        }
        return null;
    }

    public void writeMember(Member member) {
        System.out.println(member);
    }
}
