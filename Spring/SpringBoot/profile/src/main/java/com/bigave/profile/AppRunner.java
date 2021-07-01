package com.bigave.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner{

    @Autowired
    private String hello;
    @Autowired
    BigaveProperties bigaveProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(hello);
        System.out.println(bigaveProperties.getName());
        System.out.println(bigaveProperties.getAge());
    }
}
