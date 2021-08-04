package com.studyweb.studyweb.event.validator;

import com.studyweb.studyweb.domain.Event;
import com.studyweb.studyweb.event.form.EventForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class EventFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return EventForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EventForm eventForm = (EventForm) o;


        if(eventForm.getEndEnrollmentDateTime().isBefore(LocalDateTime.now())){
            errors.rejectValue("endEnrollmentDateTime", "wrong.datetime", "모임 접수 종료 일시를 정확히 입력하세요.");
        }

        if(eventForm.getEndDateTime().isBefore(eventForm.getStartDateTime()) || eventForm.getEndDateTime().isBefore(eventForm.getEndEnrollmentDateTime())){
            errors.rejectValue("endDateTime", "wrong.datetime", "모임 종료 일시를 정확히 입력하세요.");
        }

        if(eventForm.getStartDateTime().isBefore(eventForm.getEndEnrollmentDateTime())){
            errors.rejectValue("startDateTime", "wrong.datetime", "모임 시작 일시를 정확히 입력하세요.");
        }


    }

    public void validateUpdateForm(EventForm eventForm,Event event, Errors errors) {
        if(eventForm.getLimitOfEnrollments() < event.numberOfAcceptedUser()){
            errors.rejectValue("limitOfEnrollments","wrong.limitOfEnrollments","현재 등록된 사용자보다 적은수로 모집인원을 변경할 수 없습니다.");
        }
    }
}
