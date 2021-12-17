package com.servlet.web.frontcontroller.v5;

import com.servlet.web.frontcontroller.MyView;
import com.servlet.web.frontcontroller.v3.MemberFormControllerV3;
import com.servlet.web.frontcontroller.v3.MemberListControllerV3;
import com.servlet.web.frontcontroller.v3.MemberSaveControllerV3;
import com.servlet.web.frontcontroller.v3.ModelView;
import com.servlet.web.frontcontroller.v4.MemberFormControllerV4;
import com.servlet.web.frontcontroller.v4.MemberListControllerV4;
import com.servlet.web.frontcontroller.v4.MemberSaveControllerV4;
import com.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {
    private final Map<String, Object> handlerMappings = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappings();
        initHandlerAdapters();
    }

    private void initHandlerMappings() {
        handlerMappings.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappings.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappings.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappings.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappings.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappings.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        ModelView modelView = handlerAdapter.handle(request, response, handler);
        MyView view = viewResolver(modelView.getViewName());

        view.render(modelView.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = handlerMappings.get(requestURI);

        return handler;
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for(MyHandlerAdapter handlerAdapter : handlerAdapters){
            if(handlerAdapter.supports(handler)){
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("요청을 처리할 수 있는 HandlerAdapter를 찾을 수 없습니다. handler = " + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
