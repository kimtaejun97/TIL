package com.studyweb.studyweb.modules.study.event;

import com.studyweb.studyweb.modules.study.Study;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

@RequiredArgsConstructor
@Getter
public class StudyUpdateEvent {

    private Study study;
    private String message;
    public StudyUpdateEvent(Study study, String message ) {
        this.study = study;
        this.message=  message;

    }
}
