package com.studyweb.studyweb.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyweb.studyweb.account.CurrentUser;
import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.settings.form.TagForm;
import com.studyweb.studyweb.settings.form.ZoneForm;
import com.studyweb.studyweb.study.form.StudyDescriptionForm;
import com.studyweb.studyweb.study.form.UpdatePathForm;
import com.studyweb.studyweb.study.form.UpdateTitleForm;
import com.studyweb.studyweb.study.validator.UpdatePathFormValidator;
import com.studyweb.studyweb.tags.TagService;
import com.studyweb.studyweb.zone.ZoneService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/study/{path}/settings")
@Controller
public class StudySettingsController {
    public static final String SETTINGS_BASE_RETURN_STRING = "study/settings/";


    private final StudyService studyService;
    private final ModelMapper modelMapper;
    private final TagService tagService;
    private final ZoneService zoneService;
    private final ObjectMapper objectMapper;
    private final UpdatePathFormValidator updatePathFormValidator;


    @InitBinder("updatePathForm")
    public void UpdatePathInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(updatePathFormValidator);
    }

    @GetMapping("/description")
    public String ViewStudySetting(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyToUpdate(account,path);
        model.addAttribute(study);
        model.addAttribute(modelMapper.map(study, StudyDescriptionForm.class));

        return SETTINGS_BASE_RETURN_STRING + "description";
    }


    @PostMapping("/description")
    public String UpdateStudyDescription(@CurrentUser Account account, @PathVariable String path, @Valid StudyDescriptionForm studyDescriptionForm, Errors errors
            , Model model, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdate(account, path);

        if(errors.hasErrors()){
            model.addAttribute(study);
            return SETTINGS_BASE_RETURN_STRING + "description";
        }

        studyService.updateStudyDescription(study, studyDescriptionForm);
        attributes.addFlashAttribute("message", "스터디 소개를 수정하였습니다.");
        return "redirect:/study/" +Study.getPath(path) + "/settings/description";

    }

    private String getPath(String path) {
        return URLEncoder.encode(path, StandardCharsets.UTF_8);
    }

    @GetMapping("/banner")
    public String ViewBannerSetting(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(study);
        return SETTINGS_BASE_RETURN_STRING + "banner";
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

        return "redirect:/study/" +Study.getPath(path) + "/settings/banner";
    }

    @PostMapping("/bannerImage")
    public String UpdateBannerImage(@CurrentUser Account account, @PathVariable String path, String bannerImage, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdate(account, path);

        studyService.UpdateBannerImage(study, bannerImage);
        attributes.addFlashAttribute("message", "배너 이미지를 변경하였습니다.");

        return "redirect:/study/" +Study.getPath(path) + "/settings/banner";

    }

    @GetMapping("/tags")
    public String studyTagsForm(@CurrentUser Account account, @PathVariable String path, Model model) throws JsonProcessingException {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(study);

        List<String> tags  = studyService.getStudyTags(study);
        model.addAttribute("tags", tags);

        List<String> allTagTitle = tagService.getAllTagTitle();
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allTagTitle));

        return SETTINGS_BASE_RETURN_STRING + "tags";
    }

    @PostMapping("/tags/add")
    public @ResponseBody ResponseEntity addStudyTags(@CurrentUser Account account, @PathVariable String path, @RequestBody TagForm tagForm){
        Study study = studyService.getStudyToUpdateTags(account, path);

        studyService.addTag(study,tagForm);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/tags/remove")
    public @ResponseBody ResponseEntity removeStudyTags(@CurrentUser Account account, @PathVariable String path, @RequestBody TagForm tagForm){
        Study study = studyService.getStudyToUpdateTags(account, path);

        ResponseEntity responseEntity = studyService.removeTag(study, tagForm);

        return responseEntity;
    }

    @GetMapping("/zones")
    public String studyZonesForm(@CurrentUser Account account, @PathVariable String path, Model model) throws JsonProcessingException {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(study);

        List<String> zones  = studyService.getStudyZones(study);
        model.addAttribute("zones", zones);

        List<String> allZones = zoneService.getAllZones();
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allZones));

        return SETTINGS_BASE_RETURN_STRING + "zones";
    }


    @PostMapping("/zones/add")
    public @ResponseBody ResponseEntity addStudyZones(@CurrentUser Account account, @PathVariable String path, @RequestBody ZoneForm zoneForm){
        Study study = studyService.getStudyToUpdateZones(account, path);

        ResponseEntity responseEntity = studyService.addZone(study, zoneForm);

        return responseEntity;
    }


    @PostMapping("/zones/remove")
    public @ResponseBody ResponseEntity removeStudyZones(@CurrentUser Account account, @PathVariable String path, @RequestBody ZoneForm zoneForm){
        Study study = studyService.getStudyToUpdateZones(account, path);

        ResponseEntity responseEntity = studyService.removeZone(study, zoneForm);

        return responseEntity;
    }

    @GetMapping("/study")
    public String ViewStudySettings(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(study);

        model.addAttribute(new UpdateTitleForm(study.getTitle()));
        model.addAttribute(new UpdatePathForm(study.getPath()));

        return "study/settings/study";
    }

    @PostMapping("/study/publish")
    public String publishStudy(@CurrentUser Account account, @PathVariable String path, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdateWithManager(account, path);

        studyService.studyPublish(study);
        attributes.addFlashAttribute("message","스터디를 공개 하였습니다.");

        return "redirect:/study/"+Study.getPath(path) +"/settings/study";

    }

    @PostMapping("/study/close")
    public String closeStudy(@CurrentUser Account account, @PathVariable String path, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdateWithManager(account, path);

        studyService.studyClose(study);
        attributes.addFlashAttribute("message", "스터디를 종료하였습니다. 데이터는 유지됩니다.");

        return "redirect:/study/"+Study.getPath(path) +"/settings/study";

    }

    @PostMapping("/study/recruiting")
    public String recruiting(@CurrentUser Account account, @PathVariable String path, RedirectAttributes attributes){
        Study study = studyService.getStudyToUpdateWithManager(account, path);
        String state = studyService.studyRecruiting(study);
        attributes.addFlashAttribute("message", state);

        return "redirect:/study/"+Study.getPath(path) +"/settings/study";

    }

    @PostMapping("/study/path")
    public String updatePath(@CurrentUser Account account, @PathVariable String path, @Valid UpdatePathForm updatePathForm, Errors errors,
                             RedirectAttributes attributes, Model model){

        if(errors.hasErrors()){
            Study study = studyService.getStudyToUpdate(account, path);
            model.addAttribute(study);
            model.addAttribute(new UpdateTitleForm(study.getTitle()));

            return "study/settings/study";

        }

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        String newPath = updatePathForm.getNewPath();


        studyService.studyUpdatePath(study, newPath);
        attributes.addFlashAttribute("message", "스터디 경로를 " + newPath +  " 로 변경하였습니다.");

        return "redirect:/study/"+getPath(newPath) +"/settings/study";

    }
    @PostMapping("/study/title")
    public String Updatetitle(@CurrentUser Account account, @PathVariable String path, @Valid UpdateTitleForm updateTitleForm, Errors errors,
                              RedirectAttributes attributes, Model model){

        if(errors.hasErrors()){
            Study study = studyService.getStudyToUpdate(account, path);
            model.addAttribute(study);
            model.addAttribute(modelMapper.map(study, UpdatePathForm.class));


            return "study/settings/study";
        }

        Study study = studyService.getStudyToUpdateWithManager(account, path);
        String newTitle = updateTitleForm.getNewTitle();
        studyService.updateStudyTitle(study, newTitle);
        attributes.addFlashAttribute("message", "스터디 타이틀을 " + newTitle + " 로 변경하였습니다.");

        return "redirect:/study/"+Study.getPath(path) +"/settings/study";

    }

    @PostMapping("/study/remove")
    public String removeStudy(@CurrentUser Account account, @PathVariable String path, RedirectAttributes attributes) throws IllegalAccessException {
        Study study = studyService.getStudyToUpdateWithManager(account, path);

        studyService.studyRemove(study);
        attributes.addFlashAttribute("message", "스터디를 제거하였습니다. 모든 데이터가 삭제됩니다.");

        return "redirect:/";

    }
}

