package com.servlet.web.frontcontroller.v3;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {
    private String ViewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        ViewName = viewName;
    }
}
