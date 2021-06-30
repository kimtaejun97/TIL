package com.bigave.applicationevents;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class PrintArgs {

    public PrintArgs(ApplicationArguments applicationArguments){
        System.out.println(applicationArguments.containsOption("bar"));
    }
}
