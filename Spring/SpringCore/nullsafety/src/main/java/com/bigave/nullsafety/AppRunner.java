package com.bigave.nullsafety;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    EventService eventService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String message = eventService.createEvent("kim");

    }
}
