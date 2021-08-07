package com.studyweb.studyweb.modules.study.event;

import com.studyweb.studyweb.infra.config.AppProperties;
import com.studyweb.studyweb.infra.mail.EmailMessage;
import com.studyweb.studyweb.infra.mail.EmailService;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.AccountPredicate;
import com.studyweb.studyweb.modules.account.AccountRepository;
import com.studyweb.studyweb.modules.notification.Notification;
import com.studyweb.studyweb.modules.notification.NotificationRepository;
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

import java.time.LocalDateTime;


@Async
@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class StudyEventHandler {

    private final StudyRepository studyRepository;
    private final AccountRepository accountRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final AppProperties appProperties;

    private final NotificationRepository notificationRepository;

    @EventListener
    public void handleStudyCreatedEvent(StudyCreatedEvent studyCreatedEvent){
        //detached 상태에서는 tags와 zones를 가져올 수 없기 때문에 persist 상태로 만들어주기 위해 다시 조회
        Study study =  studyRepository.findStudyWithTagsAndZonesByPath(studyCreatedEvent.getStudy().getPath());

        //Iterable<T> findAll(Predicate var1)
        Iterable<Account> accounts = accountRepository.findAll(AccountPredicate.findByTagsAndZones(study.getTags(), study.getZones()));

        accounts.forEach(a ->{
            if(a.isStudyCreatedByEmail()){
                sendStudyCreatedNotificationEmail(study, a);
            }
            if(a.isStudyCreatedByWeb()){
                saveCreatedStudyNotification(study, a);
            }
        });


    }

    private void saveCreatedStudyNotification(Study study, Account a) {
        Notification notification = new Notification();
        notification.setNotificationType(NotificationType.STUDY_CREATED);
        notification.setAccount(a);
        notification.setChecked(false);
        notification.setCreatedLocalDateTime(LocalDateTime.now());
        notification.setTitle(study.getTitle());
        notification.setLink("/study/"+ study.getEncodedPath());
        notification.setMessage("관심 태그로 설정해둔 스터디가 개설 되었습니다.");

        notificationRepository.save(notification);
    }

    private void sendStudyCreatedNotificationEmail(Study study, Account a) {
        Context context = new Context();
        context.setVariable("nickName", a.getNickName());
        context.setVariable("message", "관심 태그로 설정해둔 스터디가 개설 되었습니다.");
        context.setVariable("host", appProperties.getHost());
        context.setVariable("link", "/study/"+ study.getEncodedPath());
        context.setVariable("linkName", study.getTitle());

        String message = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .subject("스터디 웹 :: "+ study.getTitle()+" 스터디 생성 알림.")
                .to(a.getEmail())
                .text(message)
                .build();

        emailService.send(emailMessage);
    }
}
