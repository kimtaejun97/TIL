package com.studyweb.studyweb.account;

import com.studyweb.studyweb.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;


    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute(new SignUpForm());
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors){
        if(errors.hasErrors()){
            return "account/sign-up";
        }
        Account account = accountService.processNewAccount(signUpForm);
        accountService.login(account);

        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model){
        Account account = accountRepository.findByEmail(email);
        String view = "account/checked-email";

        if(account == null){
            model.addAttribute("error", "wrong.email");
            return view;
        }

        if (!account.isValidToken(token)){
            model.addAttribute("error", "wrong.token");
            return view;
        }

        accountService.completeSignUp(account);
        model.addAttribute("numberOfUser", accountRepository.count());
        model.addAttribute("nickName", account.getNickName());

        return view;
    }

    @GetMapping("/check-email")
    public String checkEmail(@CurrentUser Account account,Model model){
        model.addAttribute("email",account.getEmail());
        return "account/check-email";
    }

    @GetMapping("/resend-confirm-email")
    public String resendPage(@CurrentUser Account account, Model model){

        if(!account.canConfirmEmail()){
            model.addAttribute("error", "인증 이메일은 10분에 한번만 전송할 수 있습니다.");
            model.addAttribute("email",account.getEmail());
            return "account/check-email";
        }

        accountService.resendConfirmEmail(account.getNickName());
        return "redirect:/";
    }

    @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, @CurrentUser Account account, Model model){
        Account byNickName = accountRepository.findByNickName(nickname);

        if(byNickName == null){
            throw new IllegalArgumentException(nickname+"에 해당하는 사용자가 없습니다");
        }

        //account
        model.addAttribute(byNickName);
        model.addAttribute("isOwner", byNickName.equals(account));

        return "account/profile";

    }





}
