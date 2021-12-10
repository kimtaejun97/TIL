package com.servlet.web.frontcontroller.v2;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;
import com.servlet.web.frontcontroller.MyView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String SAVE_VIEW_PATH = "/WEB-INF/views/save-result.jsp";

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member newMember = saveMember(request);
        request.setAttribute("member", newMember);

        return new MyView(SAVE_VIEW_PATH);
    }

    private Member saveMember(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        return memberRepository.save(new Member(username, age));
    }
}
