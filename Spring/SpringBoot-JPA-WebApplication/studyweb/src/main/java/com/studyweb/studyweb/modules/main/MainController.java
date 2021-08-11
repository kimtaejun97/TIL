package com.studyweb.studyweb.modules.main;

import com.studyweb.studyweb.modules.account.AccountRepository;
import com.studyweb.studyweb.modules.account.CurrentUser;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.event.EnrollmentRepository;
import com.studyweb.studyweb.modules.event.Event;
import com.studyweb.studyweb.modules.study.Study;
import com.studyweb.studyweb.modules.study.StudyRepository;
import com.studyweb.studyweb.modules.study.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final StudyService studyService;
    private final StudyRepository studyRepository;
    private final AccountRepository accountRepository;
    private final EnrollmentRepository enrollmentRepository;


    @GetMapping("/")
    public String home(@CurrentUser Account account, Model model){
        if(account != null){
            Account user = accountRepository.findAccountWithTagsAndZonesByNickName(account.getNickName());

            model.addAttribute(user);
            model.addAttribute("tags",user.getTags());
            model.addAttribute("zones",user.getZones());

            List<Event> eventList = enrollmentRepository.findByAccountAndAcceptedOrderByEnrolledAtDesc(user, true).stream()
                    .map(enrollment -> enrollment.getEvent())
                    .collect(Collectors.toList());

            model.addAttribute("eventList", eventList);
            model.addAttribute("interestStudyList",studyRepository.findFirst9byTagsAndZones(user.getTags(),user.getZones()));
            model.addAttribute("adminStudyList", studyRepository.findFirst5IsManager(user));
            model.addAttribute("enrollmentStudyList", studyRepository.findFirst5ByMembersContainingAndClosedOrderByPublishedDateTimeAsc(user, false));
        }
        else{
            List<Study> studies = studyRepository.findFirst9ByPublishedAndClosedOrderByPublishedDateTimeDesc(true, false);
            model.addAttribute("studyList",studies);

        }

        return "index";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/search/study") //pageable : size, page, sort
    public String studySearch(@PageableDefault(size = 9, page = 0, sort ="memberCount", direction = Sort.Direction.DESC) Pageable pageable, String keyword, Model model){

//        Iterable<Study> studyList =  studyService.getStudyByKeyword(keyword);
        Page<Study> studyList = studyRepository.findByKeyword(keyword, pageable);

        model.addAttribute("keyword", keyword);
        model.addAttribute("studyPage", studyList);
        model.addAttribute("sortProperty", pageable.getSort().toString());
        model.addAttribute("order", pageable.getSort().toString().split(": ")[1]);


        return "search-view";
    }
}
