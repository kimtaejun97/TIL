package com.webrtc.modules.main;

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

    @GetMapping("/video")
    public String videoStream(){
        return "video";
    }
}
