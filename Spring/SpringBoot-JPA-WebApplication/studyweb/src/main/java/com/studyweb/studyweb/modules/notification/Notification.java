package com.studyweb.studyweb.modules.notification;

import com.studyweb.studyweb.modules.account.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Notification {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean Checked;

    @ManyToOne
    private Account account;

    @Column(nullable = false)
    private LocalDateTime createdLocalDateTime;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

}
