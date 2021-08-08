package com.studyweb.studyweb.modules.event.event;

import com.studyweb.studyweb.modules.event.Enrollment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

@RequiredArgsConstructor
@Getter
public class EventEnrollmentEvent {

    private Enrollment enrollment;
    private String message;


    public EventEnrollmentEvent(Enrollment enrollment, String message) {
        this.enrollment = enrollment;
        this.message = message;
    }
}
