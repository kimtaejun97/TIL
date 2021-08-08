package com.studyweb.studyweb.modules.event;

import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.event.event.EventEnrollmentEvent;
import com.studyweb.studyweb.modules.event.event.EventCreatedEvent;
import com.studyweb.studyweb.modules.study.Study;
import com.studyweb.studyweb.modules.event.form.EventForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final EnrollmentRepository enrollmentRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Event createEvent(Study study, Event event, Account account) {
        event.setStudy(study);
        event.setCreatedBy(account);
        event.setCreatedDateTime(LocalDateTime.now());

        Event newEvent = eventRepository.save(event);

        applicationEventPublisher.publishEvent(new EventCreatedEvent(event, "새로운 모임이 등록 되었습니다."));


        return newEvent;
    }


    public void updateEvent(Event event, EventForm eventForm) {
        modelMapper.map(eventForm, event);
        updateAcceptUser(event);

        applicationEventPublisher.publishEvent(new EventCreatedEvent(event, event.getTitle()+" 모임 정보가 변경 되었습니다."));


    }

    public void enroll(Account account, Event event) {
        if(!enrollmentRepository.existsByEventAndAccount(event,account)){
            Enrollment enrollment = Enrollment.builder()
                    .accepted(false)
                    .account(account)
                    .attended(false)
                    .enrolledAt(LocalDateTime.now())
                    .build();

            if(event.numberOfRemainSpots()>0 && event.getEventType().equals(EventType.FCFS)){
                enrollment.setAccepted(true);
                applicationEventPublisher.publishEvent(new EventEnrollmentEvent(enrollment, "모임의 참가 신청이 수락되었습니다."));
            }

            event.addEnrollment(enrollment);
            enrollmentRepository.save(enrollment);
        }

    }

    public void acceptUser(Event event, Enrollment enrollment) {

        if(!event.canAccept(enrollment)){
            throw new IllegalArgumentException("모임에 참여할 수 있는 상태가 아닙니다.");
        }

        enrollment.setAccepted(true);
        applicationEventPublisher.publishEvent(new EventEnrollmentEvent(enrollment, " 모임 참가 신청이 수락 되었습니다."));

    }

    public void rejectUser(Event event, Enrollment enrollment) {

        if(!event.canReject(enrollment)){
            throw new IllegalArgumentException("참여를 취소할 수 있는 상태가 아닙니다.");
        }

        enrollment.setAccepted(false);

        applicationEventPublisher.publishEvent(new EventEnrollmentEvent(enrollment, " 모임 참가 신청이 거절 되었습니다."));



    }

    private void checkConfirmativeEventType(Event event) {
        if(!event.getEventType().equals(EventType.CONFIRMATIVE)){
            throw new IllegalArgumentException("모임이 관리자 확인 방식이 아닙니다. ");

        }
    }
    public List<Event> getEventsByStudy(Study study) {
        return eventRepository.findByStudyOrderByStartDateTime(study);
    }

    public void disEnroll(Event event, Enrollment enrollment) {

        if(enrollment.isAttended()){
            throw new IllegalArgumentException("이미 출석 처리 되어 참석을 취소할 수 없습니다.");
        }

        enrollmentRepository.delete(enrollment);
        event.getEnrollments().remove(enrollment);

        updateAcceptUser(event);

    }

    public void updateAcceptUser(Event event){

        if(event.numberOfRemainSpots() !=0){

            List<Enrollment> enrollments = enrollmentRepository.findByEventAndAccepted(event, false);

            if(enrollments.size() != 0){
                enrollments.stream()
                        .map(Enrollment::getEnrolledAt)
                        .collect(Collectors.toList()).sort(Comparator.naturalOrder());

                while (event.numberOfRemainSpots() !=0){
                    enrollments.remove(0).setAccepted(true);
                }
            }
        }
    }

    public void checkin(Event event, Enrollment enrollment) {

        checkAccepted(event, enrollment);
        checkAttendedFlag(enrollment, false);

        enrollment.setAttended(true);
    }

    public void cancelCheckin(Event event, Enrollment enrollment) {

        checkAccepted(event, enrollment);
        checkAttendedFlag(enrollment, true);

        enrollment.setAttended(false);
    }

    private void checkAttendedFlag(Enrollment enrollment, boolean flag) {
        if(enrollment.isAttended() != flag){
            throw new IllegalArgumentException("변경하려는 상태와 출석 상태가 같습니다.");
        }
    }

    private void checkAccepted(Event event, Enrollment enrollment) {
        if(!event.isAccepted(enrollment)){
            throw new IllegalArgumentException("모임에 참가 확정되지 않은 사용자 입니다.");
        }
    }

    public void CancelEvent(Event event) {
        applicationEventPublisher.publishEvent(new EventCreatedEvent(event, event.getTitle()+ "모임이 취소 되었습니다."));

        eventRepository.delete(event);


    }
}
