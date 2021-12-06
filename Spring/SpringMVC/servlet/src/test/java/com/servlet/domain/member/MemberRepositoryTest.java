package com.servlet.domain.member;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }


    @DisplayName("save")
    @Test
    void save() throws Exception {
        // given
        Member saveMember = new Member("kim", 25);

        // when
        memberRepository.save(saveMember);

        // then
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @DisplayName("findAll")
    @Test
    void findAll() throws Exception {
        // given
        Member saveMember1 = new Member("kim", 25);
        Member saveMember2 = new Member("park", 25);

        // when
        memberRepository.save(saveMember1);
        memberRepository.save(saveMember2);

        // then
        ArrayList<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(saveMember1, saveMember2);
    }

}