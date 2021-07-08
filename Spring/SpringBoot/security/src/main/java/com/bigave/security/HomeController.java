package com.bigave.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String hello(){
        return "admin";
    }

    @GetMapping("/my")
    public String my(){
        return "my";
    }
}
