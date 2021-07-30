package com.studyweb.studyweb.study;

import com.studyweb.studyweb.account.AccountController;
import com.studyweb.studyweb.account.CurrentUser;
import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.study.form.StudyDescriptionForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RequestMapping("/study/{path}/settings")
@Controller
public class StudySettingsController {

    private final StudyService studyService;
    private final ModelMapper modelMapper;

    @GetMapping("/description")
    public String ViewStudySetting(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyToUpdate(account,path);
        model.addAttribute(study);
        model.addAttribute(modelMapper.map(study, StudyDescriptionForm.class));

        return "study/settings/description";
    }


    @PostMapping("/description")
    public String UpdateStudyDescription(@CurrentUser Account account, @PathVariable String path, @Valid StudyDescriptionForm studyDescriptionForm, Errors errors
            , Model model, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdate(account, path);

        if(errors.hasErrors()){
            model.addAttribute(study);
            return "study/settings/description";
        }

        studyService.updateStudyDescription(study, studyDescriptionForm);
        attributes.addFlashAttribute("message", "스터디 소개를 수정하였습니다.");
        return "redirect:/study/" +getPath(path) + "/settings/description";

    }

    private String getPath(String path) {
        return URLEncoder.encode(path, StandardCharsets.UTF_8);
    }

    @GetMapping("/banner")
    public String ViewBannerSetting(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(study);
        return "study/settings/banner";
    }

    @PostMapping("/banner")
    public String useBanner(@CurrentUser Account account, @PathVariable String path, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdate(account, path);

        boolean isUsebanner = studyService.setUsebanner(study);
        if(isUsebanner){
            attributes.addFlashAttribute("message","배너를 사용하도록 설정 하였습니다.");
        }
        else{
            attributes.addFlashAttribute("message", "배너를 사용하지 않도록 설정 하였습니다.");
        }

        return "redirect:/study/" +getPath(path) + "/settings/banner";
    }

    @PostMapping("/bannerImage")
    public String UpdateBannerImage(@CurrentUser Account account, @PathVariable String path, String bannerImage, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdate(account, path);

        studyService.UpdateBannerImage(study, bannerImage);
        attributes.addFlashAttribute("message", "배너 이미지를 변경하였습니다.");

        return "redirect:/study/" +getPath(path) + "/settings/banner";

    }
}

