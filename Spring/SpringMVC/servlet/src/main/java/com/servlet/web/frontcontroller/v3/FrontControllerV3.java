package com.servlet.web.frontcontroller.v3;

import com.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerV3" ,urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {
    private Map<String, ControllerV3> controllers = new HashMap<>();

    private final String FORM_VIEW_PATH = "/front-controller/v3/members/new-form";
    private final String LIST_VIEW_PATH = "/front-controller/v3/members";
    private final String SAVE_VIEW_PATH = "/front-controller/v3/members/save";

    public FrontControllerV3() {
        controllers.put(FORM_VIEW_PATH, new MemberFormControllerV3());
        controllers.put(SAVE_VIEW_PATH, new MemberSaveControllerV3());
        controllers.put(LIST_VIEW_PATH, new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllers.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> params = createParamMap(request);
        ModelView modelView = controller.process(params);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);
        view.render(modelView.getModel() ,request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> params.put(paramName, request.getParameter(paramName)));
        return params;
    }
}
