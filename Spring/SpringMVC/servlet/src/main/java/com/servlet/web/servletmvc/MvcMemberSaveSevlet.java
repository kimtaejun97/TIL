package com.servlet.web.servletmvc;

import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="MvcMemberSaveSevlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveSevlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private final String SAVE_VIEW_PATH = "/WEB-INF/views/save-result.jsp";
    private final String USER_NAME = "username";
    private final String AGE = "age";


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveMember(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(SAVE_VIEW_PATH);
        dispatcher.forward(request, response);
    }

    private void saveMember(HttpServletRequest request) {
        String username = request.getParameter(USER_NAME);
        int age = Integer.parseInt(request.getParameter(AGE));
        Member newMember = memberRepository.save(new Member(username, age));

        request.setAttribute("member", newMember);
    }
}
