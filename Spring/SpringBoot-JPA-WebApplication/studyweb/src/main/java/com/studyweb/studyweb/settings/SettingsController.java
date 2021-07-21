package com.studyweb.studyweb.settings;

import com.studyweb.studyweb.account.AccountRepository;
import com.studyweb.studyweb.account.AccountService;
import com.studyweb.studyweb.account.CurrentUser;
import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Tag;
import com.studyweb.studyweb.settings.form.*;
import com.studyweb.studyweb.settings.validator.NickNameValidator;
import com.studyweb.studyweb.settings.validator.PasswordValidator;
import com.studyweb.studyweb.tags.TagRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class SettingsController {

    private final AccountService accountService;
    private final PasswordValidator passwordValidator;
    private final NickNameValidator nickNameValidator;

    private final AccountRepository accountRepository;

    private final TagRepository tagRepository;

    private final ModelMapper modelMapper;



    @InitBinder("password")
    public void passwordInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(passwordValidator);
    }

    @InitBinder("nickNameForm")
    public void nickNameInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(nickNameValidator);
    }

    @GetMapping("/settings/profile")
    public  String profileUpdateForm(@CurrentUser Account account, Model model ){
        model.addAttribute(account);
        model.addAttribute(modelMapper.map(account, Profile.class));

        return "settings/profile";
    }

    @PostMapping("/settings/profile")
    public  String profileUpdate(@CurrentUser Account account, @Valid Profile profile, Errors errors, Model model, RedirectAttributes attributes){
        if(errors.hasErrors()){
            model.addAttribute(account);
            return "settings/profile";
        }

        accountService.updateProfile(account, profile);
        attributes.addFlashAttribute("message", "프로필 수정 완료.");


        return "redirect:/settings/profile";
    }


    @GetMapping("/settings/password")
    public String passwordUpdateForm(Model model, @CurrentUser Account account){
        var password = new Password();
        password.setNickName(account.getNickName());
        model.addAttribute(password);
        return "settings/password";
    }

    @PostMapping("/settings/password")
    public String passwordUpdate(@CurrentUser Account account, @Valid Password password, Errors errors, RedirectAttributes attributes){

        if(errors.hasErrors()){
            return "settings/password";
        }

        accountService.updatePassword(account,password.getNewPassword());
        attributes.addFlashAttribute("message", "비밀번호 변경 완료.");

        return "redirect:/settings/password";

    }


    @GetMapping("settings/notification")
    public String notificationUpdateForm(@CurrentUser Account account, Model model){
        model.addAttribute(modelMapper.map(account, Notification.class));

        return "settings/notification";
    }

    @PostMapping("/settings/notification")
    public String notificationUpdate(@CurrentUser Account account, Notification notification, RedirectAttributes attributes){
        accountService.updateNotification(account, notification);
        attributes.addFlashAttribute("message", "알림 설정이 변경되었습니다.");

        return "redirect:/settings/notification";

    }


    @GetMapping("settings/account")
    public String accountUpdateForm(@CurrentUser Account account, NickNameForm nickNameForm, Model model){
        model.addAttribute(modelMapper.map(account , NickNameForm.class));
        return "settings/account";
    }

    @PostMapping("/settings/account")
    public String accountUpdate(@CurrentUser Account account, @Valid NickNameForm nickNameForm, Errors errors , RedirectAttributes attributes, Model model){
        if(errors.hasErrors()){
            return "/settings/account";
        }
        accountService.updateNickName(account, nickNameForm.getNickName());
        attributes.addFlashAttribute("message", "닉네임이 변경되었습니다.");

        return "redirect:/settings/account";

    }

    @GetMapping("/settings/tags")
    public String updateTags(@CurrentUser Account account, Model model){
        model.addAttribute(account);
        Set<Tag> tags = accountService.getTags(account);

        model.addAttribute("tags", tags.stream().map(Tag::getTitle).collect(Collectors.toList()));

        return "settings/tags";
    }

    @PostMapping("/settings/tags/add")
    public @ResponseBody ResponseEntity addTag(@CurrentUser Account account, @RequestBody TagForm tagForm){
        String title = tagForm.getTagTitle();
        Tag tag = tagRepository.findByTitle(title);

        if(tag ==null){
            tag = tagRepository.save(Tag.builder()
                    .title(title)
                    .build());
        }
        accountService.addTag(account, tag);

        return ResponseEntity.ok().build();
    }

    @PostMapping("settings/tags/remove")
    public @ResponseBody ResponseEntity removeTag(@CurrentUser Account account, @RequestBody TagForm tagForm){
        String title = tagForm.getTagTitle();
        Tag tag = tagRepository.findByTitle(title);
        if(tag == null){
            return ResponseEntity.badRequest().build();
        }
        accountService.removeTag(account, tag);

        return ResponseEntity.ok().build();
    }






}
