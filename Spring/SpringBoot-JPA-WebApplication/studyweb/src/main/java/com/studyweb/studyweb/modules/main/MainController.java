package com.studyweb.studyweb.modules.main;

import com.studyweb.studyweb.modules.account.CurrentUser;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.study.Study;
import com.studyweb.studyweb.modules.study.StudyRepository;
import com.studyweb.studyweb.modules.study.StudyRepositoryExtensionImpl;
import com.studyweb.studyweb.modules.study.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@RequiredArgsConstructor
@Controller
public class MainController {

    private final StudyService studyService;
    private final StudyRepository studyRepository;


    @GetMapping("/")
    public String home(@CurrentUser Account account, Model model){
        if(account != null){
            model.addAttribute(account);
        }

        return "index";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/search/study")
    public String studySearch(String keyword, Model model){

//        Iterable<Study> studyList =  studyService.getStudyByKeyword(keyword);
        List<Study> studyList = studyRepository.findByKeyword(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("studyList", studyList);

        return "search-view";
    }
}
