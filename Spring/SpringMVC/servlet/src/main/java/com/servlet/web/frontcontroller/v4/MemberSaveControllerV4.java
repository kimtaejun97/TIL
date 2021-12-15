package com.servlet.web.frontcontroller.v4;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4{

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String VIEW_PATH = "save-result";

    @Override
    public String process(Map<String, String> params, Map<String, Object> model) {
        String username = params.get("username");
        int age = Integer.parseInt(params.get("age"));

        Member newMember = memberRepository.save(new Member(username, age));

        model.put("member", newMember);
        return VIEW_PATH;
    }
}
