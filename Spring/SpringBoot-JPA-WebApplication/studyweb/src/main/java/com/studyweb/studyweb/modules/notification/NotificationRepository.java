package com.studyweb.studyweb.modules.notification;

import com.studyweb.studyweb.modules.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Long countNotificationByAccountAndChecked(Account account, boolean checked);

    @Transactional
    List<Notification> findByAccountAndCheckedOrderByCreatedLocalDateTimeDesc(Account account, boolean checked);

    @Transactional
    List<Notification> findByAccountAndChecked(Account account, boolean b);

    void deleteByAccountAndChecked(Account account, boolean b);
}
