package com.servlet.basic.response;

import org.springframework.http.HttpHeaders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        // status line
        response.setStatus(HttpServletResponse.SC_OK);

        // response-header
        response.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setHeader("custom-Header", "my Header");

        // message-body
        response.getWriter().write("OK");

        content(response);
        cookie(response);
        redirect(response);
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
//        response.setCharacterEncoding("utf-8");
//        response.setContentLength(3); // 생략시 자동 생성.
    }

    private void cookie(HttpServletResponse response) {
        // response.setHeader("Set-Cookie", "myCookie=Umm it's delicious; Max-Age=600");
        Cookie cookie = new Cookie("myCookie",  "delicious");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_FOUND);
        //Location: localhost:8080/basic/hello-form.html
        response.sendRedirect("/basic/hello-form.html");
    }
}
