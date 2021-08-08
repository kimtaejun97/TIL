package com.studyweb.studyweb.modules.notification;

import com.studyweb.studyweb.infra.config.AppProperties;
import com.studyweb.studyweb.infra.mail.EmailMessage;
import com.studyweb.studyweb.infra.mail.EmailService;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.study.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final AppProperties appProperties;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;


    public void removeNotifications(Account account) {
        notificationRepository.deleteByAccountAndChecked(account, true);
    }

    public void checkedNotification(Notification notification) {

        if(notification.isChecked()){
            throw new IllegalArgumentException("이미 확인한 알림입니다.");
        }

        notification.setChecked(true);

    }

    public Notification getNotification(Account account, Long notificationId) throws IllegalAccessException {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();

        if(!notification.getAccount().equals(account)){
            throw new IllegalAccessException("잘못된 접근 입니다.");
        }

        return notification;

    }

    public void checkedAllNotifications(List<Notification> notifications) {

        if(notifications == null){
            throw new IllegalArgumentException("읽지 않은 알림이 없습니다.");
        }

        for(Notification n : notifications){
            n.setChecked(true);
        }

    }

    public void saveNotification(Study study, Account a, String message, NotificationType notificationType) {

        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setAccount(a);
        notification.setChecked(false);
        notification.setCreatedLocalDateTime(LocalDateTime.now());
        notification.setTitle(study.getTitle());
        notification.setLink("/study/"+ study.getEncodedPath());
        notification.setMessage(message);

        notificationRepository.save(notification);
    }

    public void sendNotificationEmail(Study study, Account a, String message, String subject) {
        Context context = new Context();
        context.setVariable("nickName", a.getNickName());
        context.setVariable("message", message);
        context.setVariable("host", appProperties.getHost());
        context.setVariable("link", "/study/"+ study.getEncodedPath());
        context.setVariable("linkName", study.getTitle());

        String html = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .subject(subject)
                .to(a.getEmail())
                .text(html)
                .build();

        emailService.send(emailMessage);
    }
}
