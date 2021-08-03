package com.studyweb.studyweb.event;

import com.studyweb.studyweb.account.CurrentUser;
import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Event;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.event.form.EventForm;
import com.studyweb.studyweb.event.validator.EventFormValidator;
import com.studyweb.studyweb.study.StudyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/study/{path}")
public class EventController {

    private final StudyService studyService;
    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final EventFormValidator eventFormValidator;
    private final EventRepository eventRepository;

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

    @GetMapping("/events/{id}")
    public String getEvent(@CurrentUser Account account, @PathVariable String path, @PathVariable Long id, Model model){

        model.addAttribute(account);
        model.addAttribute(studyService.getStudy(path));
        model.addAttribute(eventRepository.findById(id).orElseThrow());

        return"/event/view";

    }

    @GetMapping("/events/{eventId}/edit")
    public String eventEditForm(@CurrentUser Account account, @PathVariable String path,@PathVariable Long eventId ,Model model){
        Study study = studyService.getStudyToUpdateWithManager(account, path);
        Event event = eventService.getEventById(eventId);

        model.addAttribute(account);
        model.addAttribute(study);
        model.addAttribute(event);
        model.addAttribute(modelMapper.map(event, EventForm.class));

        return "/event/update-form";
    }

    @PostMapping("/events/{eventId}/edit")
    public String eventEditSubmit(@CurrentUser Account account, @PathVariable String path,@PathVariable Long eventId, Model model,
                                  @Valid EventForm eventForm, Errors errors){

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        Event event = eventService.getEventById(eventId);
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
    public String CancelEvent(@CurrentUser Account account, @PathVariable String path,@PathVariable Long eventId){
        Study study = studyService.getStudyToUpdateWithManager(account, path);
        eventRepository.delete(eventRepository.findById(eventId).orElseThrow());

        return "redirect:/study/"+study.getPath(path)+"/events";
    }

}
