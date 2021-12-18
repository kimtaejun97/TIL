package com.servlet.web.springmvc.v1;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class SpringMemberListControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process(){
        ArrayList<Member> members = memberRepository.findAll();
        ModelAndView mv = createModelAndView(members);

        return mv;
    }

    private ModelAndView createModelAndView(ArrayList<Member> members) {
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }
}
