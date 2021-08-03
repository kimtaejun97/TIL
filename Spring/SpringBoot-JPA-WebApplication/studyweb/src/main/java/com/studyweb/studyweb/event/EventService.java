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
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

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

    public Event getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();

        if(event == null){
            throw new IllegalArgumentException(eventId +"는 존재하지 않는 모임 번호 입니다.");
        }
        return event;
    }

    public void updateEvent(Event event, EventForm eventForm) {
        modelMapper.map(eventForm, event);

        //TODO 모집 인원을 늘린 선착순 모임, 자동으로 받아지기.
    }
}
