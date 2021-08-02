package com.studyweb.studyweb.domain;


import com.studyweb.studyweb.account.UserAccount;
import com.studyweb.studyweb.event.EventType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(name = "Event.withEnrollments", attributeNodes = {
        @NamedAttributeNode("enrollments")
})

@Getter @Setter
@Entity
public class Event {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Study study;

    @ManyToOne
    private Account createdBy;

    @Column(nullable = false)
    private String title;

    @Lob
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime endEnrollmentDateTime;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    private Integer limitOfEnrollments;

    @OneToMany(mappedBy = "event")
    private List<Enrollment> enrollments;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public boolean isEnrollableFor(UserAccount userAccount){
        return !isClosed() && (study.isMember(userAccount) || study.isManager(userAccount)) && !isEnrolled(userAccount);
    }

    public boolean isDisenrollableFor(UserAccount userAccount){
        return !isClosed() && isEnrolled(userAccount);

    }

    public boolean isAttended(UserAccount userAccount){
        Account account = userAccount.getAccount();

        for(Enrollment e : enrollments){
            if(e.getAccount().equals(account) && e.isAttended())
                return true;
        }
        return false;
    }

    private boolean isClosed() {
        return this.endEnrollmentDateTime.isBefore(LocalDateTime.now());
    }

    private boolean isEnrolled(UserAccount userAccount){
        Account account = userAccount.getAccount();

        for(Enrollment e : enrollments){
            if(e.getAccount().equals(account))
                return true;
        }
        return false;
    }

    public String numberOfRemainSpots(){
        return Integer.toString(this.limitOfEnrollments - (int)enrollments.stream().filter(Enrollment::isAccepted).count());
    }



}
