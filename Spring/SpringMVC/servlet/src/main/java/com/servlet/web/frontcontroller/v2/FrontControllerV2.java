package com.servlet.web.frontcontroller.v2;

import com.servlet.web.frontcontroller.MyView;
import com.servlet.web.frontcontroller.v1.ControllerV1;
import com.servlet.web.frontcontroller.v1.MemberFormControllerV1;
import com.servlet.web.frontcontroller.v1.MemberListControllerV1;
import com.servlet.web.frontcontroller.v1.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {
    private Map<String, ControllerV2> controllers = new HashMap<>();
    private final String FORM_VIEW_PATH = "/front-controller/v2/members/new-form";
    private final String LIST_VIEW_PATH = "/front-controller/v2/members";
    private final String SAVE_VIEW_PATH = "/front-controller/v2/members/save";

    public FrontControllerV2() {
        controllers.put(FORM_VIEW_PATH, new MemberFormControllerV2());
        controllers.put(SAVE_VIEW_PATH, new MemberSaveControllerV2());
        controllers.put(LIST_VIEW_PATH, new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllers.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(request, response);
        view.render(request,response);
    }
}
