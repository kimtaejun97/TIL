package com.servlet.web.frontcontroller.v5.adapter;

import com.servlet.web.frontcontroller.v3.ModelView;
import com.servlet.web.frontcontroller.v4.ControllerV4;
import com.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> params = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(params, model);

        return createModelView(model, viewName);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(name -> params.put(name, request.getParameter(name)));

        return params;
    }

    private ModelView createModelView(Map<String, Object> model, String viewName) {
        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);

        return modelView;
    }
}
