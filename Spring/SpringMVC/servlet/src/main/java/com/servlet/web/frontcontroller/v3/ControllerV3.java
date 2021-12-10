package com.servlet.web.frontcontroller.v3;

import java.util.Map;

public interface ControllerV3 {
    public ModelView process(Map<String, String> params);
}
