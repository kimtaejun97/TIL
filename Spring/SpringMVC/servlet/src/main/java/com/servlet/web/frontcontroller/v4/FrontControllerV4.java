package com.servlet.web.frontcontroller.v4;

import com.servlet.web.frontcontroller.MyView;
import com.servlet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {

    private Map<String, ControllerV4> controllers = new HashMap<>();

    private final String FORM_VIEW_PATH = "/front-controller/v4/members/new-form";
    private final String LIST_VIEW_PATH = "/front-controller/v4/members";
    private final String SAVE_VIEW_PATH = "/front-controller/v4/members/save";

    public FrontControllerV4() {
        controllers.put(FORM_VIEW_PATH, new MemberFormControllerV4());
        controllers.put(SAVE_VIEW_PATH, new MemberSaveControllerV4());
        controllers.put(LIST_VIEW_PATH, new MemberListControllerV4());
    }

   ㅊ

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> params.put(paramName, request.getParameter(paramName)));
        return params;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
