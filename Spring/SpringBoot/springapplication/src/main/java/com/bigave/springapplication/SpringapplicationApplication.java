package com.bigave.springapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringapplicationApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringapplicationApplication.class, args);

//        SpringApplication app = new SpringApplication(SpringapplicationApplication.class);
//        app.run(args);

        new SpringApplicationBuilder()
                .sources(SpringapplicationApplication.class)
                .run(args);
    }

}
