package com.servlet.web.frontcontroller.v1;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MemberListControllerV1 implements ControllerV1{

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String VIEW_PATH = "/WEB-INF/views/members.jsp";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);

        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request, response);
    }
}
