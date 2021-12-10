package com.servlet.web.frontcontroller.v3;


import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    private final String VIEW_NAME = "new-form";

    @Override
    public ModelView process(Map<String, String> params) {
        return new ModelView(VIEW_NAME);
    }
}
