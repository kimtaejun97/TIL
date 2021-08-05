package com.studyweb.studyweb.modules.event;


import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.UserAccount;
import com.studyweb.studyweb.modules.study.Study;
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

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollments;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public boolean isEnrollableFor(UserAccount userAccount){
        return !isClosed() && !isEnrolled(userAccount)
                && (study.isMember(userAccount) || study.isManager(userAccount))
                && !isAttended(userAccount);
    }

    public boolean isDisenrollableFor(UserAccount userAccount){
        return !isClosed() && isEnrolled(userAccount) && !isAttended(userAccount);

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

    public Integer numberOfRemainSpots(){
        return this.limitOfEnrollments - (int)enrollments.stream().filter(Enrollment::isAccepted).count();
    }


    public Integer numberOfAcceptedUser() {
        return (int)enrollments.stream().filter(Enrollment::isAccepted).count();
    }

    public boolean canAccept(Enrollment enrollment){
        if(this.numberOfRemainSpots() !=0 && !enrollment.isAccepted() && this.eventType.equals(EventType.CONFIRMATIVE)){
            return true;
        }
        return false;
    }

    public boolean canReject(Enrollment enrollment) {
        if(enrollment.isAccepted() && this.eventType.equals(EventType.CONFIRMATIVE)){
            return true;
        }
        return false;
    }

    public Enrollment getEnrollmentByAccount(Account account) {
        for(Enrollment e :enrollments){
            if(e.getAccount().equals(account))
                return e;
        }

        throw new IllegalArgumentException("해당 모임에 참가 신청을 하지 않았습니다.");

    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        enrollment.setEvent(this);
    }

    public boolean isAccepted(Enrollment enrollment) {
        return this.getEnrollments().contains(enrollment) && enrollment.isAccepted();
    }
}
