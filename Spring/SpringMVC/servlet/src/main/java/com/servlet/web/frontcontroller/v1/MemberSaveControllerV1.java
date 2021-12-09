package com.servlet.web.frontcontroller.v1;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1{

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String SAVE_VIEW_PATH = "/WEB-INF/views/save-result.jsp";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member newMember = saveMember(request);
        request.setAttribute("member", newMember);

        RequestDispatcher dispatcher = request.getRequestDispatcher(SAVE_VIEW_PATH);
        dispatcher.forward(request, response);
    }

    private Member saveMember(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        return memberRepository.save(new Member(username, age));
    }
}
