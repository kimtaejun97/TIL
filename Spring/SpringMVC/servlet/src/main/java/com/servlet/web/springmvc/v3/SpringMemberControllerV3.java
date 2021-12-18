package com.servlet.web.springmvc.v3;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form" , method = RequestMethod.GET)
    @GetMapping("new-form")
    public String newForm(){
        return "new-form";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model){

        Member newMember = saveMember(username, age);
        model.addAttribute("member", newMember);

        return "save-result";
    }

    private Member saveMember(String username, int age) {
        return memberRepository.save(new Member(username, age));
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String memberList(Model model){
        ArrayList<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "members";
    }
}


