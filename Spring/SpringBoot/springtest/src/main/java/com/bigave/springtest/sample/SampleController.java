package com.bigave.springtest.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    SampleService sampleService;

    @GetMapping("/hello")
    public String hello(){
        logger.info("logger : bigave");
        System.out.println("logger : skip");
        return "hello " + sampleService.getName();
    }
}
