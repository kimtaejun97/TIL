package com.webrtc.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class Main {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
