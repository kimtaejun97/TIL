package jpabook.api;

import jpabook.module.member.Address;
import jpabook.module.member.Member;
import jpabook.module.member.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

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

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse UpdateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request){
        Long memberId = memberService.update(id, request.getName());
        Member member = memberService.findById(memberId);
        return new UpdateMemberResponse(member.getId(), member.getName());
    }

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<MemberDto> memberDtos = memberService.findMembers().stream()
                .map(m -> new MemberDto(m.getName(), m.getAddress()))
                .collect(Collectors.toList());

        return new Result(memberDtos.size(),memberDtos);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        private Address address;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }
    @Data
    static class UpdateMemberRequest{
        private String name;
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
