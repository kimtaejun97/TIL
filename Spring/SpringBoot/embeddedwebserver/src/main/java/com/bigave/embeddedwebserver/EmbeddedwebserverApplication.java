package com.bigave.embeddedwebserver;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class EmbeddedwebserverApplication {

    public static void main(String[] args) throws LifecycleException {
//        SpringApplication.run(EmbeddedwebserverApplication.class, args);
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addContext("/", "/");

        tomcat.start();
    }

}
