package com.studyweb.studyweb.modules.event.event;

import com.studyweb.studyweb.infra.config.AppProperties;
import com.studyweb.studyweb.infra.mail.EmailMessage;
import com.studyweb.studyweb.infra.mail.EmailService;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.event.Event;
import com.studyweb.studyweb.modules.notification.Notification;
import com.studyweb.studyweb.modules.notification.NotificationRepository;
import com.studyweb.studyweb.modules.notification.NotificationType;
import com.studyweb.studyweb.modules.study.Study;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Async
@Transactional
@RequiredArgsConstructor
@Component
@Slf4j
public class EventEventHandler {

    private final NotificationRepository notificationRepository;
    private final AppProperties appProperties;
    private final TemplateEngine templateEngine;
    private final EmailService emailService;

    @EventListener
    public void handleEventCreatedEvent(EventCreatedEvent eventCreatedEvent){
        Study study = eventCreatedEvent.getEvent().getStudy();

        List<Account> accounts = new ArrayList<>();

        accounts.addAll(study.getMembers());
        accounts.addAll(study.getManagers());

        accounts.forEach(a ->{
            if(a.isStudyCreatedByEmail()){
                sendNotificationEmail(eventCreatedEvent.getEvent(), a, eventCreatedEvent.getMessage(), "스터디 웹 :: "+ study.getTitle()+" 새 모임 생성 알림.");
            }
            if(a.isStudyCreatedByWeb()){
                saveNotification(eventCreatedEvent.getEvent(), a, eventCreatedEvent.getMessage(), NotificationType.EVENT_ENROLLMENT);
            }
        });
    }

    @EventListener
    public void handleEventAcceptedEvent(EventEnrollmentEvent eventEnrollmentEvent){
        Account account = eventEnrollmentEvent.getEnrollment().getAccount();
        Event event = eventEnrollmentEvent.getEnrollment().getEvent();

        if(account.isStudyEnrollmentResultByEmail()){
            sendNotificationEmail(event, account, eventEnrollmentEvent.getMessage(), event.getTitle()+" 모임의 참가신청 관련 소식이 있습니다.");
        }
        if(account.isStudyEnrollmentResultByWeb()){
            saveNotification(event, account , eventEnrollmentEvent.getMessage(), NotificationType.EVENT_ENROLLMENT);
        }
    }

    private void saveNotification(Event event, Account a, String message, NotificationType notificationType) {

        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setAccount(a);
        notification.setChecked(false);
        notification.setCreatedLocalDateTime(LocalDateTime.now());
        notification.setTitle(event.getTitle());
        notification.setLink("/study/"+ event.getStudy().getEncodedPath()+"/events/"+event.getId());
        notification.setMessage(message);

        notificationRepository.save(notification);
    }

    private void sendNotificationEmail(Event event, Account a, String message, String subject) {
        Context context = new Context();
        context.setVariable("nickName", a.getNickName());
        context.setVariable("message", message);
        context.setVariable("host", appProperties.getHost());
        context.setVariable("link", "/study/"+ event.getStudy().getEncodedPath()+"/events/"+event.getId());
        context.setVariable("linkName", event.getTitle());

        String html = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .subject(subject)
                .to(a.getEmail())
                .text(html)
                .build();

        emailService.send(emailMessage);
    }
}
