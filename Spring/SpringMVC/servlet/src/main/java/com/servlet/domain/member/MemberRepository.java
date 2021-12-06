package com.servlet.domain.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository(){}

    public static MemberRepository getInstance(){
        return instance;
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(sequence,member);
        return member;
    }

    public ArrayList<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clear(){
        store.clear();
    }

    public Member findById(Long id) {
        return store.get(id);
    }
}
