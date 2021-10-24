package jpabook.module.member;

import jpabook.api.MemberApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(Member member) {
        if(memberRepository.existsByName(member.getName())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findById(Long memberId){
        return memberRepository.findById(memberId);
    }

    @Transactional
    public Long update(Long id, String name) {
        Member member = memberRepository.findById(id);
        member.update(name);

        return member.getId();
    }
}
