package com.studyweb.studyweb.modules.event;

import com.studyweb.studyweb.modules.account.CurrentUser;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.study.Study;
import com.studyweb.studyweb.modules.event.form.EventForm;
import com.studyweb.studyweb.modules.event.validator.EventFormValidator;
import com.studyweb.studyweb.modules.study.StudyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/study/{path}")
public class EventController {

    private final StudyService studyService;
    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final EventFormValidator eventFormValidator;
    private final EventRepository eventRepository;
    private final EnrollmentRepository enrollmentRepository;

    @InitBinder("eventForm")
    public void EventFormInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(eventFormValidator);
    }

    @GetMapping("/new-event")
    public String newEventForm(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyToUpdateWithManager(account, path);
        model.addAttribute(study);
        model.addAttribute(new EventForm());

        return "event/form";
    }


    @PostMapping("/new-event")
    public String newEventSubmit(@CurrentUser Account account, @PathVariable String path, @Valid EventForm eventForm, Errors errors, Model model){

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        if(errors.hasErrors()){
            model.addAttribute(study);
            return "event/form";
        }

        Event event = eventService.createEvent(study, modelMapper.map(eventForm, Event.class), account);


        return "redirect:/study/"+study.getPath(path)+ "/events/"+event.getId();
    }
    @GetMapping("/events")
    public String ViewEventList(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudy(path);
        model.addAttribute(account);
        model.addAttribute(study);

        List<Event> events =  eventService.getEventsByStudy(study);
        List<Event> openEvents = new ArrayList<>();
        List<Event> closedEvents = new ArrayList<>();

        events.forEach(e->{
            if(e.getEndDateTime().isBefore(LocalDateTime.now())){
                closedEvents.add(e);
            }
            else{
                openEvents.add(e);
            }
        });

        model.addAttribute("newEvents", openEvents);
        model.addAttribute("oldEvents",closedEvents);

        return "study/events";
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(@CurrentUser Account account, @PathVariable String path, @PathVariable("eventId") Event event, Model model){

        model.addAttribute(account);
        model.addAttribute(studyService.getStudy(path));
        model.addAttribute(event);

        return"/event/view";

    }

    @GetMapping("/events/{eventId}/edit")
    public String eventEditForm(@CurrentUser Account account, @PathVariable String path,@PathVariable("eventId") Event event ,Model model){
        Study study = studyService.getStudyToUpdateWithManager(account, path);

        model.addAttribute(account);
        model.addAttribute(study);
        model.addAttribute(event);
        model.addAttribute(modelMapper.map(event, EventForm.class));

        return "/event/update-form";
    }

    @PostMapping("/events/{eventId}/edit")
    public String eventEditSubmit(@CurrentUser Account account, @PathVariable String path,@PathVariable("eventId") Event event, Model model,
                                  @Valid EventForm eventForm, Errors errors){

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        eventForm.setEventType(event.getEventType());

        eventFormValidator.validateUpdateForm(eventForm, event, errors);

        if(errors.hasErrors()){
            model.addAttribute(account);
            model.addAttribute(study);
            model.addAttribute(event);

            return "/event/update-form";
        }
        eventService.updateEvent(event, eventForm);

        return "redirect:/study/"+study.getPath(path)+"/events/"+event.getId();
    }

    @DeleteMapping("/events/{eventId}")
    public String CancelEvent(@CurrentUser Account account, @PathVariable String path,@PathVariable("eventId") Event event){

        Study study = studyService.getStudyToUpdateWithManager(account, path);

        eventService.CancelEvent(event);

        return "redirect:/study/"+study.getPath(path)+"/events";
    }

    @PostMapping("/events/{eventId}/enroll")
    public String Enroll(@CurrentUser Account account , @PathVariable String path, @PathVariable("eventId") Event event) {

        Study study = studyService.getStudyOnly(account, path);
        eventService.enroll(account, event);

        return "redirect:/study/"+study.getPath(path) + "/events/"+event.getId();
    }

    @PostMapping("/events/{eventId}/disenroll")
    public String disEnroll(@CurrentUser Account account , @PathVariable String path, @PathVariable("eventId") Event event){

        Study study = studyService.getStudyOnly(account, path);
        Enrollment enrollment = event.getEnrollmentByAccount(account);

        eventService.disEnroll(event,enrollment);


        return "redirect:/study/"+study.getPath(path) + "/events/"+event.getId();

    }

    @PostMapping("/events/{eventId}/enrollments/{enrollId}/accept")
    public String acceptUser(@CurrentUser Account account, @PathVariable String path,
                             @PathVariable("eventId") Event event, @PathVariable("enrollId") Enrollment enrollment){

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        eventService.acceptUser(event, enrollment);

        return "redirect:/study/"+study.getPath(path) + "/events/"+event.getId();

    }

    @PostMapping("/events/{eventId}/enrollments/{enrollId}/reject")
    public String rejectUser(@CurrentUser Account account, @PathVariable String path,
                             @PathVariable("eventId") Event event, @PathVariable("enrollId") Enrollment enrollment){

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        eventService.rejectUser(event, enrollment);

        return "redirect:/study/"+study.getPath(path) + "/events/"+event.getId();

    }

    @PostMapping("/events/{eventId}/enrollments/{enrollId}/checkin")
    public String checkin(@CurrentUser Account account, @PathVariable String path,
                          @PathVariable("eventId") Event event, @PathVariable("enrollId") Enrollment enrollment){

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        eventService.checkin(event, enrollment);

        return "redirect:/study/"+study.getPath(path) + "/events/" +event.getId();
    }

    @PostMapping("/events/{eventId}/enrollments/{enrollId}/cancel-checkin")
    public String checkinCancel(@CurrentUser Account account, @PathVariable String path,
                                @PathVariable("eventId") Event event, @PathVariable("enrollId") Enrollment enrollment){

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        eventService.cancelCheckin(event, enrollment);

        return "redirect:/study/"+study.getPath(path) + "/events/"+event.getId();
    }

}
