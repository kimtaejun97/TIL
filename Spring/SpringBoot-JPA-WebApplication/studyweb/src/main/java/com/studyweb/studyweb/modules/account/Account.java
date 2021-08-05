package com.studyweb.studyweb.modules.account;

import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.zone.Zone;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickName;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    private String bio;

    private String url;

    private String occupation;

    private String location;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private boolean studyCreatedByEmail;
    private boolean studyCreatedByWeb =true;

    private boolean studyEnrollmentResultByEmail;
    private boolean studyEnrollmentResultByWeb= true;

    private boolean studyUpdatedByEmail;
    private boolean studyUpdatedByWeb = true;
    private LocalDateTime emailCheckTokenLastGeneration;

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    private Set<Zone> zones = new HashSet<>();


    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenLastGeneration = LocalDateTime.now();

    }



    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    public boolean canConfirmEmail() {
        return this.emailCheckTokenLastGeneration.isBefore(LocalDateTime.now().minusMinutes(10));
    }

    public void completeSignUp() {
        this.emailVerified =true;
        this.joinedAt = LocalDateTime.now();
    }


}
