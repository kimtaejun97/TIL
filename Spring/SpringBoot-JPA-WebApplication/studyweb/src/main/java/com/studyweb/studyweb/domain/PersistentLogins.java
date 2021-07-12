package com.studyweb.studyweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "persistent_logins")
@Entity
public class PersistentLogins {

    @Id
    @Column(length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 64)
    private String token;

    @Column(name = "last_used",nullable = false, length = 64)
    private LocalDateTime lastUsed;
}
