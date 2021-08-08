package com.studyweb.studyweb.modules.event.event;

import com.studyweb.studyweb.modules.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

@RequiredArgsConstructor
@Getter
public class EventCreatedEvent {

    private Event event;
    private String message;

    public EventCreatedEvent(Event event, String message) {
        this.event = event;
        this.message = message;
    }
}
