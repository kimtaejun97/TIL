package com.servlet.web.frontcontroller.v2;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;
import com.servlet.web.frontcontroller.MyView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String VIEW_PATH = "/WEB-INF/views/members.jsp";

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);

        return new MyView(VIEW_PATH);
    }
}
