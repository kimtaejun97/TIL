package com.studyweb.studyweb.event;

import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Enrollment;
import com.studyweb.studyweb.domain.Event;
import com.studyweb.studyweb.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EnrollmentRepository enrollmentRepository;

    public Event createEvent(Study study, Event event, Account account) {
        event.setStudy(study);
        event.setCreatedBy(account);
        event.setCreatedDateTime(LocalDateTime.now());

        Event newEvent = eventRepository.save(event);


//        Enrollment enrollment = new Enrollment();
//        enrollment.setEvent(newEvent);
//        enrollment.setEnrolledAt(LocalDateTime.now());
//        enrollment.setAccepted(true);
//        enrollment.setAttended(true);
//        enrollment.setAccount(account);
//        Enrollment newEnrollment = enrollmentRepository.save(enrollment);
//
//        newEvent.getEnrollments().add(newEnrollment);

        return newEvent;
    }
}
