package com.studyweb.studyweb.modules.study.event;

import com.studyweb.studyweb.modules.study.Study;
import lombok.Getter;

@Getter
public class StudyCreatedEvent {
    private Study study;
    private String message;
    public StudyCreatedEvent(Study study, String message) {
        this.study = study;
        this.message = message;
    }
}
