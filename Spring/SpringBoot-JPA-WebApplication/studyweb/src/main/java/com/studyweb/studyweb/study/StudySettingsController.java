package com.studyweb.studyweb.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyweb.studyweb.account.AccountController;
import com.studyweb.studyweb.account.CurrentUser;
import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.domain.Tag;
import com.studyweb.studyweb.domain.Zone;
import com.studyweb.studyweb.settings.form.TagForm;
import com.studyweb.studyweb.settings.form.ZoneForm;
import com.studyweb.studyweb.study.form.StudyDescriptionForm;
import com.studyweb.studyweb.tags.TagService;
import com.studyweb.studyweb.zone.ZoneService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/study/{path}/settings")
@Controller
public class StudySettingsController {

    private final StudyService studyService;
    private final ModelMapper modelMapper;
    private final TagService tagService;
    private final ZoneService zoneService;
    private final ObjectMapper objectMapper;

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

    @GetMapping("/tags")
    public String studyTagsForm(@CurrentUser Account account, @PathVariable String path, Model model) throws JsonProcessingException {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(study);

        List<String> tags  = studyService.getStudyTags(study);
        model.addAttribute("tags", tags);

        List<String> allTagTitle = tagService.getAllTagTitle();
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allTagTitle));

        return "study/settings/tags";
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

        return "study/settings/zones";
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
}

