package jpabook.module.member;

import jpabook.module.member.Member;
import jpabook.module.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("Member 저장")
    @Test
    void saveTest(){
        // given
        Member member = new Member();
        member.setName("kim");

        //when
        Member save = memberRepository.save(member);

        //then
        assertThat(memberRepository.findById(save.getId())).isEqualTo(member);
    }

}