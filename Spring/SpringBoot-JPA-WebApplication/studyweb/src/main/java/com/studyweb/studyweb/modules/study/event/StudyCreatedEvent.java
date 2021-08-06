package com.studyweb.studyweb.modules.study.event;

import com.studyweb.studyweb.modules.study.Study;
import lombok.Getter;

@Getter
public class StudyCreatedEvent {
    private Study study;
    public StudyCreatedEvent(Study study) {
        this.study = study;
    }
}
