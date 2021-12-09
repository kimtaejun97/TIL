package com.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {
    private Map<String, ControllerV1> controllers = new HashMap<>();
    private final String FORM_VIEW_PATH = "/front-controller/v1/members/new-form";
    private final String LIST_VIEW_PATH = "/front-controller/v1/members";
    private final String SAVE_VIEW_PATH = "/front-controller/v1/members/save";

    public FrontControllerV1() {
        controllers.put(FORM_VIEW_PATH, new MemberFormControllerV1());
        controllers.put(SAVE_VIEW_PATH, new MemberSaveControllerV1());
        controllers.put(LIST_VIEW_PATH, new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV1 controller = controllers.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
