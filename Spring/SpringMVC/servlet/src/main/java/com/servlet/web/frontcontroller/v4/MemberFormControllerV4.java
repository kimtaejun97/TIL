package com.servlet.web.frontcontroller.v4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4{

    private final String VIEW_NAME = "new-form";

    @Override
    public String process(Map<String, String> params, Map<String, Object> model) {
        return VIEW_NAME;
    }
}
