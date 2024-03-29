package com.servlet.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlet.domain.member.Member;
import com.servlet.domain.member.MemberRepository;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MemberFormServlet", urlPatterns = "/servlet/members/save")
public class MemberFormServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String METHOD_POST = "POST";
    private final String METHOD_GET = "GET";

    @Override
    protected void service(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        String method = reqest.getMethod();
        if(method.equals(METHOD_POST) ){
            doPost(reqest,response);
        }
        else if(method.equals(METHOD_GET)){
            doGet(reqest, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Member member = makeMemberObj(request);
        Member saveMember = saveMember(member);

        setPostResponse(response, member);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        setGetHeader(response);
        setGetBody(response);
    }


    private void setGetHeader(HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
    }

    private void setGetBody(HttpServletResponse response) throws IOException {
        PrintWriter w = response.getWriter();
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                "    username: <input type=\"text\" name=\"username\" />\n" +
                "    age:      <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" + "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }

    private Member makeMemberObj(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        return new Member(username, age);
    }

    private Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    private void setPostResponse(HttpServletResponse response, Member member) throws IOException {
        setPostHeader(response);
        setPostBody(response, member);
    }

    private void setPostBody(HttpServletResponse response, Member member) throws IOException {
        response.getWriter().write(objectMapper.writeValueAsString(member));
    }

    private void setPostHeader(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
    }
}
