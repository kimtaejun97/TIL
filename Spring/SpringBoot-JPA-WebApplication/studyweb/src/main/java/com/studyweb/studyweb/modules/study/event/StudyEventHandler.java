package com.studyweb.studyweb.modules.study.event;

import com.studyweb.studyweb.infra.config.AppProperties;
import com.studyweb.studyweb.infra.mail.EmailMessage;
import com.studyweb.studyweb.infra.mail.EmailService;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.AccountPredicate;
import com.studyweb.studyweb.modules.account.AccountRepository;
import com.studyweb.studyweb.modules.notification.Notification;
import com.studyweb.studyweb.modules.notification.NotificationRepository;
import com.studyweb.studyweb.modules.notification.NotificationService;
import com.studyweb.studyweb.modules.notification.NotificationType;
import com.studyweb.studyweb.modules.study.Study;
import com.studyweb.studyweb.modules.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Async
@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class StudyEventHandler {

    private final StudyRepository studyRepository;
    private final AccountRepository accountRepository;
    private final NotificationService notificationService;

    private final NotificationRepository notificationRepository;

    @EventListener
    public void handleStudyCreatedEvent(StudyCreatedEvent studyCreatedEvent){
        //detached 상태에서는 tags와 zones를 가져올 수 없기 때문에 persist 상태로 만들어주기 위해 다시 조회
        Study study =  studyRepository.findStudyWithTagsAndZonesByPath(studyCreatedEvent.getStudy().getPath());

        //Iterable<T> findAll(Predicate var1)
        Iterable<Account> accounts = accountRepository.findAll(AccountPredicate.findByTagsAndZones(study.getTags(), study.getZones()));

        accounts.forEach(a ->{
            if(a.isStudyCreatedByEmail()){
                notificationService.sendNotificationEmail(study, a, studyCreatedEvent.getMessage(), "스터디 웹 :: "+ study.getTitle()+" 스터디 생성 알림.");
            }
            if(a.isStudyCreatedByWeb()){
                notificationService.saveNotification(study, a, studyCreatedEvent.getMessage(), NotificationType.STUDY_CREATED);
            }
        });
    }

    @EventListener
    public void hadleStudyUpdateEvent(StudyUpdateEvent studyUpdateEvent){
        Study study = studyRepository.findStudyWithTeamsByPath(studyUpdateEvent.getStudy().getPath());

        List<Account> accounts = new ArrayList<>();

        accounts.addAll(study.getManagers());
        accounts.addAll(study.getMembers());

        accounts.stream()
                .forEach(account -> {
                    if (account.isStudyUpdatedByEmail()) {
                        notificationService.sendNotificationEmail(study,account,studyUpdateEvent.getMessage(), "스터디 웹: "+study.getTitle()+"에 새로운 소식이 있습니다.");
                    }

                    if (account.isStudyUpdatedByWeb()){
                        notificationService.saveNotification(study,account,studyUpdateEvent.getMessage(),NotificationType.STUDY_UPDATED);

                    }
                });

    }

}
