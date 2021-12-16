package com.servlet.web.frontcontroller.v3;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;

import java.util.ArrayList;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String VIEW_NAME = "members";

    @Override
    public ModelView process(Map<String, String> params) {
        ArrayList<Member> members = memberRepository.findAll();

        ModelView modelView = new ModelView(VIEW_NAME);
        modelView.getModel().put("members", members);

        return modelView;
    }
}
