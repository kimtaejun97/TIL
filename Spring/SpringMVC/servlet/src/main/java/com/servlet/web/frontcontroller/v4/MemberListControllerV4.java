package com.servlet.web.frontcontroller.v4;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;

import java.util.ArrayList;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4{

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String VIEW_NAME = "members";

    @Override
    public String process(Map<String, String> params, Map<String, Object> model) {
        ArrayList<Member> members = memberRepository.findAll();
        model.put("members", members);

        return VIEW_NAME;
    }
}
