package com.servlet.web.frontcontroller.v4;


import java.util.Map;

public interface ControllerV4 {
    /**
     * @param params
     * @param model
     * @return viewName
     */

    String process(Map<String, String> params, Map<String, Object> model);
}
