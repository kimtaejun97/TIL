package com.bigave.springaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringaopApplication {

    public static void main(String[] args)
    {
        SpringApplication app =  new SpringApplication(SpringaopApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }

}
