package com.bigave.applicationevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationeventsApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationeventsApplication.class);
        app.addListeners(new SampleListener());
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);


    }

}
