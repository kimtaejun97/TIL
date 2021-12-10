package com.servlet.web.frontcontroller.v3;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3{

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String VIEW_NAME = "save-result";

    @Override
    public ModelView process(Map<String, String> params) {
        Member newMember = saveMember(params);

        ModelView modelView = new ModelView(VIEW_NAME);
        modelView.getModel().put("member", newMember);

        return modelView;
    }

    private Member saveMember(Map<String, String> params) {
        String username = params.get("username");
        int age = Integer.parseInt(params.get("age"));
        Member newMember = memberRepository.save(new Member(username, age));
        return newMember;
    }
}
