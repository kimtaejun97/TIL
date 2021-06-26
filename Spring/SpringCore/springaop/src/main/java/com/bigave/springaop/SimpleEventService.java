package com.bigave.springaop;


import org.springframework.stereotype.Service;

@Service
public class SimpleEventService implements EventService{
    @PerfLogging
    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Cerated an Event");
    }
    @PerfLogging
    @Override
    public void publishEvent() {
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Published an Event");
    }

    public void deleteEvent(){
        System.out.println("Delete an Event");
    }
}
