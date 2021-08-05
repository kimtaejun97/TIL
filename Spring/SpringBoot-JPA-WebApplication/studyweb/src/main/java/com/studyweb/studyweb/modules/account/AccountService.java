package com.studyweb.studyweb.modules.account;

import com.studyweb.studyweb.infra.config.AppProperties;
import com.studyweb.studyweb.modules.account.form.SignUpForm;
import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.zone.Zone;
import com.studyweb.studyweb.infra.mail.EmailMessage;
import com.studyweb.studyweb.infra.mail.EmailService;
import com.studyweb.studyweb.modules.account.form.Notification;
import com.studyweb.studyweb.modules.account.form.Profile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final ModelMapper modelMapper;

    private final TemplateEngine templateEngine;
    private final AppProperties appProperties;


    public Account processNewAccount(SignUpForm signUpForm) {
        Account newAccount = saveNewAccount(signUpForm);
        sendSignUpConfirmEmail(newAccount);
        return newAccount;
    }

    private Account saveNewAccount(SignUpForm signUpForm) {
        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Account account = modelMapper.map(signUpForm, Account.class);
        account.generateEmailCheckToken();

       return accountRepository.save(account);
    }

    private void sendSignUpConfirmEmail(Account account) {
        Context context = new Context();
        context.setVariable("nickName",account.getNickName() );
        context.setVariable("link" ,"/check-email-token?token="+ account.getEmailCheckToken()
                + "&email=" + account.getEmail());
        context.setVariable("linkName","이메일 인증하기.");
        context.setVariable("message","이메일 인증을 완료하려면 아래 링크를 클릭하세요.");
        context.setVariable("host",appProperties.getHost());

        String message = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .to(account.getEmail())
                .subject("스터디 웹 회원 인증")
                .text(message)
                .build();

        emailService.send(emailMessage);
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

        sendSignUpConfirmEmail(account);

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
        // source, destination
        modelMapper.map(profile, account);
        accountRepository.save(account);
    }

    public void updatePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }

    public void updateNotification(Account account, Notification notification) {

        modelMapper.map(notification, account);
        accountRepository.save(account);
    }

    public void updateNickName(Account account, String nickName) {
        account.setNickName(nickName);
        accountRepository.save(account);
        login(account);
    }

    public void sendEmailLoginLink(Account account) {
        Context context = new Context();
        context.setVariable("nickName",account.getNickName() );
        context.setVariable("link" ,"/login-by-email?token="
                +account.getEmailCheckToken()
                + "&email="+account.getEmail());
        context.setVariable("linkName","이메일 로그인");
        context.setVariable("message","이메일로 로그인 하려면 아래 링크를 클릭하세요.");
        context.setVariable("host",appProperties.getHost());

        String message = templateEngine.process("mail/simple-link", context);


        account.generateEmailCheckToken();
        EmailMessage emailMessage = EmailMessage.builder()
                .to(account.getEmail())
                .subject("스터디 웹 이메일 로그인")
                .text(message)
                .build();

        emailService.send(emailMessage);
    }

    public void addTag(Account account, Tag tag) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        byId.ifPresent(a -> a.getTags().add(tag));
    }

    public Set<Tag> getTags(Account account) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        return byId.orElseThrow().getTags();

    }

    public void removeTag(Account account, Tag tag) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        byId.ifPresent(a->a.getTags().remove(tag));


    }

    public Set<Zone> getZones(Account account) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        return byId.orElseThrow().getZones();
    }

    public void addZone(Account account, Zone zone) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        byId.ifPresent(a->a.getZones().add(zone));

    }

    public void removeZone(Account account, Zone zone) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        byId.ifPresent(a->a.getZones().remove(zone));

    }
}
