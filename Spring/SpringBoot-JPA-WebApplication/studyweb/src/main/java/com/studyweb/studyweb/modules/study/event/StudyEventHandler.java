package com.studyweb.studyweb.modules.study.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Async
@Slf4j
@Transactional(readOnly = true)
@Component
public class StudyEventHandler {

    @EventListener
    public void handleStudyCreatedEvent(StudyCreatedEvent studyCreatedEvent){
        log.info(studyCreatedEvent.getStudy().getTitle() + " is created.");
        //TODO 이메일, 알림 처리
    }
}
