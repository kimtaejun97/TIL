package com.bigave.crossoriginserver;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

//    @CrossOrigin("http://localhost:8080")
    @GetMapping("/cross")
    public String cross(){
        return "Hello Cross Server";
    }
}
