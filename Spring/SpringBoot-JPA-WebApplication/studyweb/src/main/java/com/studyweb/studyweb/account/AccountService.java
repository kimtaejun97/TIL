package com.studyweb.studyweb.account;

import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.settings.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Transactional
@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;


    public Account processNewAccount(SignUpForm signUpForm) {
        Account newAccount = saveNewAccount(signUpForm);
        newAccount.generateEmailCheckToken();
        sendSignUpConfirmEmail(newAccount);
        return newAccount;
    }

    private Account saveNewAccount(SignUpForm signUpForm) {
        Account account = Account.builder()
                .email((signUpForm.getEmail()))
                .nickName(signUpForm.getNickName())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .studyCreatedByWeb(true)
                .studyEnrollmentResultByWeb(true)
                .studyUpdatedByWeb(true)
                .build();

        Account newAccount = accountRepository.save(account);
        return newAccount;
    }

    private void sendSignUpConfirmEmail(Account newAccount) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setSubject("스터디 웹 회원 인증");
        //check-email-token 에서 token이 유효한지 확인.
        mailMessage.setText("/check-email-token?token="+ newAccount.getEmailCheckToken()
                + "&email=" + newAccount.getEmail());

        javaMailSender.send(mailMessage);
    }

    public void login(Account account) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
    }
    public void resendConfirmEmail(String nickName) {
        Account account = accountRepository.findByNickName(nickName);
        account.generateEmailCheckToken();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(account.getEmail());
        simpleMailMessage.setSubject("스터디웹 회원 인증");
        simpleMailMessage.setText("/check-email-token?token="+account.getEmailCheckToken()
                +"&email="+account.getEmail());

        javaMailSender.send(simpleMailMessage);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String emailOrNickName) throws UsernameNotFoundException {
        Account account =  accountRepository.findByEmail(emailOrNickName);
        if(account == null){
            account = accountRepository.findByNickName(emailOrNickName);
        }
        if(account == null){
            throw new UsernameNotFoundException(emailOrNickName);
        }

        return new UserAccount(account);

    }

    public void completeSignUp(Account account) {
        account.completeSignUp();
        login(account);
    }

    public void updateProfile(Account account, Profile profile) {
        account.setBio(profile.getBio());
        account.setLocation(profile.getLocation());
        account.setOccupation(profile.getOccupation());
        account.setUrl(profile.getUrl());
        account.setProfileImage(profile.getProfileImage());
        accountRepository.save(account);
    }
}
