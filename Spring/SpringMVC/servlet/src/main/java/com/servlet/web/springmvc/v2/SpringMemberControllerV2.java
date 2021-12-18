package com.servlet.web.springmvc.v2;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("springmvc/v2/members")
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/members/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("/members/save")
    public ModelAndView save(HttpServletRequest request){
        Member newMember = saveMember(request);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", newMember);

        return mv;
    }

    private Member saveMember(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        return memberRepository.save(new Member(username, age));
    }

    @RequestMapping
    public ModelAndView memberList(){
        ArrayList<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;
    }
}

