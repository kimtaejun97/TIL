package com.bigave.externalsettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Value("${bigave.name}")
    private String name;

    @Autowired
    BigaveProperties bigaveProperties;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(name);
        System.out.println(bigaveProperties.getName());
        System.out.println(bigaveProperties.getAge());
        System.out.println(bigaveProperties.getSessionTimeout());
    }
}
