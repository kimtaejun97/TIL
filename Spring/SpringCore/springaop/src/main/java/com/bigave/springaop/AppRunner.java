package com.bigave.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    // 인터페이스가 있을경우 인터페이스를 주입받는 것이 좋다. -> 주입받을 구현체를 변경하기 용이해서?
    @Autowired
    EventService eventService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventService.createEvent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }
}
