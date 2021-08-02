package com.studyweb.studyweb.study;

import com.studyweb.studyweb.account.AccountController;
import com.studyweb.studyweb.account.CurrentUser;
import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Event;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.study.form.StudyForm;
import com.studyweb.studyweb.study.validator.StudyFormValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class StudyController {


    private final StudyService studyService;
    private final ModelMapper modelMapper;
    private final StudyFormValidator studyFormValidator;
    private final StudyRepository studyRepository;

    @InitBinder("studyForm")
    public void studyFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(studyFormValidator);

    }


    @GetMapping("/new-study")
    public String newStudyForm(@CurrentUser Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new StudyForm());

        return "study/form";
    }

    @PostMapping("/new-study")
    public String newStudySubmit(@CurrentUser Account account, @Valid StudyForm studyForm, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute(account);
            return "study/form";
        }

        Study study =  studyService.createNewStudy(modelMapper.map(studyForm, Study.class), account);
        return "redirect:/study/" + URLEncoder.encode(study.getPath(), StandardCharsets.UTF_8);
    }

    @GetMapping("/study/{path}")
    public String studyView(@PathVariable String path, @CurrentUser Account account, Model model){
        Study study =  studyRepository.findByPath(path);

        model.addAttribute(account);
        model.addAttribute(study);


        return "study/study-view";
    }

    @GetMapping("/study/{path}/members")
    public String viewStudyMembers(@CurrentUser Account account, @PathVariable String path, Model model){
        model.addAttribute(account);
        model.addAttribute(studyRepository.findByPath(path));

        return "study/members";
    }

    @PostMapping("/study/{path}/join")
    public String joinStudy(@CurrentUser Account account, @PathVariable String path){
         studyService.joinStudy(account,path);

         return "redirect:/study/"+Study.getPath(path);
    }

    @PostMapping("/study/{path}/leave")
    public String leaveStudy(@CurrentUser Account account, @PathVariable String path){
        studyService.leaveStudy(account,path);

        return "redirect:/study/"+Study.getPath(path);
    }

    @GetMapping("/study/{path}/events")
    public String ViewEventList(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudy(path);
        model.addAttribute(account);
        model.addAttribute(study);

        List<Event> events =  studyService.getEvents(study);
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
}
