package com.datajpa.member;

import com.datajpa.team.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testEntity(){
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 21, teamB);
        Member member4 = new Member("member4", 30, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select  m from Member m", Member.class)
                .getResultList();

        assertThat(members.get(0).getUsername()).isEqualTo("member1");
        assertThat(members.get(0).getAge()).isEqualTo(20);
        assertThat(members.get(0).getTeam()).isEqualTo(teamA);

        assertThat(members.get(2).getUsername()).isEqualTo("member3");
        assertThat(members.get(2).getAge()).isEqualTo(21);
        assertThat(members.get(2).getTeam()).isEqualTo(teamB);
    }

    @Test
    void 쿼리_애노테이션(){
        Team teamA = new Team("TeamA");
        em.persist(teamA);

        Member member1 = new Member("member1", 10, teamA);
        em.persist(member1);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findByUsernameAndAge("member1", 10);

        assertThat(members.get(0).getUsername()).isEqualTo("member1");
        assertThat(members.get(0).getAge()).isEqualTo(10);

    }

}