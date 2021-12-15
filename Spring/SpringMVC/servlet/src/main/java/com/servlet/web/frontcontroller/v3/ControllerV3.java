package com.servlet.web.frontcontroller.v3;

import java.util.Map;

public interface ControllerV3 {
    /**
     * @param params
     * @return ModelView
     */
    ModelView process(Map<String, String> params);
}
