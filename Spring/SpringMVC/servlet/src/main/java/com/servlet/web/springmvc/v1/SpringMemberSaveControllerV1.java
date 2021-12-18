package com.servlet.web.springmvc.v1;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request){
        Member newMember = saveMember(request);
        ModelAndView mv = createModelAndView(newMember);

        return mv;
    }

    private Member saveMember(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member newMember = memberRepository.save(new Member(username, age));

        return newMember;
    }

    private ModelAndView createModelAndView(Member newMember) {
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", newMember);
        return mv;
    }
}
