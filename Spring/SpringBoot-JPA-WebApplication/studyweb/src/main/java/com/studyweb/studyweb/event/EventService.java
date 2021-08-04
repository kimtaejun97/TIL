package com.studyweb.studyweb.event;

import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Enrollment;
import com.studyweb.studyweb.domain.Event;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.event.form.EventForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    public Event createEvent(Study study, Event event, Account account) {
        event.setStudy(study);
        event.setCreatedBy(account);
        event.setCreatedDateTime(LocalDateTime.now());

        Event newEvent = eventRepository.save(event);

        return newEvent;
    }

    public Event getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();

        if(event == null){
            throw new IllegalArgumentException(eventId +"는 존재하지 않는 모임 번호 입니다.");
        }
        return event;
    }

    public void updateEvent(Event event, EventForm eventForm) {
        modelMapper.map(eventForm, event);
    }

    public void enroll(Account account, Event event) {
        Enrollment enrollment = Enrollment.builder()
                .accepted(false)
                .account(account)
                .attended(false)
                .enrolledAt(LocalDateTime.now())
                .event(event)
                .build();

        if(event.numberOfRemainSpots()!=0 && event.getEventType().equals(EventType.FCFS)){
            enrollment.setAccepted(true);
        }

        enrollmentRepository.save(enrollment);


    }

    public void acceptUser(Event event, Enrollment enrollment) {

        if(!event.getEventType().equals(EventType.CONFIRMATIVE)){
            throw new IllegalArgumentException("모임이 관리자 확인 방식이 아닙니다. ");
        }

        if(!event.canAccept(enrollment)){
            throw new IllegalArgumentException("이미 모임 정원이 가득 찼거나, 이미 참석된 유저 입니다.");
        }

        enrollment.setAccepted(true);

    }

    public void rejectUser(Event event, Enrollment enrollment) {

        if(!event.canReject(enrollment)){
            throw new IllegalArgumentException("모임에 참석하지 않은 유저 입니다.");
        }

        enrollmentRepository.delete(enrollment);
        event.getEnrollments().remove(enrollment);

    }

    public void disEnroll(Event event, Enrollment enrollment) {

        enrollmentRepository.delete(enrollment);
        event.getEnrollments().remove(enrollment);


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
}
