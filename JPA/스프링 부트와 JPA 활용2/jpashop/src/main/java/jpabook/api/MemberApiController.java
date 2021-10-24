package jpabook.api;

import jpabook.module.member.Member;
import jpabook.module.member.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long newAccountId = memberService.join(member);
        return new CreateMemberResponse(newAccountId);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Long newAccountId = memberService.join(request.toEntity());
        return new CreateMemberResponse(newAccountId);
    }

    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;

        public Member toEntity(){
            Member member = new Member();
            member.setName(name);
            return member;
        }
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }



}
