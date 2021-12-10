package com.servlet.web.frontcontroller.v2;


import com.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    private final String VIEW_PATH = "/WEB-INF/views/new-form.jsp";

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView(VIEW_PATH);
    }
}
