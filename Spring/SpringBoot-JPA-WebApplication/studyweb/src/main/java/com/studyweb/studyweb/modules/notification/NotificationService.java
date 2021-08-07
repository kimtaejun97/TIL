package com.studyweb.studyweb.modules.notification;

import com.studyweb.studyweb.modules.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;


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
}
