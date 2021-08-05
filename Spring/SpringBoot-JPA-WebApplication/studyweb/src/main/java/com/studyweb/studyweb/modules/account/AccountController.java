package com.studyweb.studyweb.modules.account;

import com.studyweb.studyweb.modules.account.form.SignUpForm;
import com.studyweb.studyweb.modules.account.validator.SignUpFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;


    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute(new SignUpForm());
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/sign-up";
        }
        Account account = accountService.processNewAccount(signUpForm);
        accountService.login(account);

        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model) {
        Account account = accountRepository.findByEmail(email);
        String view = "account/checked-email";

        if (account == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }

        if (!account.isValidToken(token)) {
            model.addAttribute("error", "wrong.token");
            return view;
        }

        accountService.completeSignUp(account);
        model.addAttribute("numberOfUser", accountRepository.count());
        model.addAttribute("nickName", account.getNickName());

        return view;
    }

    @GetMapping("/check-email")
    public String checkEmail(@CurrentUser Account account, Model model) {
        model.addAttribute("email", account.getEmail());
        return "account/check-email";
    }

    @GetMapping("/resend-confirm-email")
    public String resendPage(@CurrentUser Account account, Model model) {

        if (!account.canConfirmEmail()) {
            model.addAttribute("error", "인증 이메일은 10분에 한번만 전송할 수 있습니다.");
            model.addAttribute("email", account.getEmail());
            return "account/check-email";
        }

        accountService.resendConfirmEmail(account.getNickName());
        return "redirect:/";
    }

    @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, @CurrentUser Account account, Model model) {
        Account byNickName = accountRepository.findByNickName(nickname);

        if (byNickName == null) {
            throw new IllegalArgumentException(nickname + "에 해당하는 사용자가 없습니다");
        }

        //account
        model.addAttribute(byNickName);
        model.addAttribute("isOwner", byNickName.equals(account));

        return "account/profile";

    }

    @GetMapping("/email-login")
    public String emailLoginForm() {
        return "account/email-login";

    }

    @PostMapping("/email-login")
    public String emailLogin(String email, Model model, RedirectAttributes attributes) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            model.addAttribute("error", "유효하지 않은 이메일 주소 입니다.");
            return "account/email-login";
        }

        if (!account.canConfirmEmail()) {
            model.addAttribute("error", "아직 이메일을 전송할 수 없습니다. 잠시 후에 다시 시도해주세요.");
            return "account/email-login";
        }

        accountService.sendEmailLoginLink(account);
        attributes.addFlashAttribute("message", account.getEmail() + " 로 이메일을 전송했습니다.");
        return "redirect:/email-login";
    }

    @GetMapping("/login-by-email")
    public String loginByEmail(String token, String email, Model model, RedirectAttributes attributes) {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            model.addAttribute("error", "유효하지 않은 주소입니다.");
            return "account/login-by-email";
        }

        if (!token.equals(account.getEmailCheckToken())) {
            model.addAttribute("error", "유효하지 않은 주소입니다.");
            return "account/login-by-email";
        }

        accountService.login(account);
        return "account/login-by-email";

    }
}