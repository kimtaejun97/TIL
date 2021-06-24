package com.bigave.eventpublisher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler  {

    @EventListener
    @Async
    public void handle(MyEvent myEvent) {
        System.out.println(Thread.currentThread());
        System.out.println("이벤트를 받음 data: "+ myEvent.getData());

    }

    @EventListener
    @Async
    public void handle(ContextRefreshedEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("=== Context RefreshedEvent === ");
        ApplicationContext applicationContext = event.getApplicationContext();

    }
    @EventListener
    @Async
    public void handle(ContextClosedEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("=== Context ClosedEvent ===");
    }
    @EventListener
    @Async
    public void handle(ContextStartedEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("=== Context StartedEvent ===");
    }
    @EventListener
    @Async
    public void handle(ContextStoppedEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("=== Context StoppedEvent ===");
    }

}
