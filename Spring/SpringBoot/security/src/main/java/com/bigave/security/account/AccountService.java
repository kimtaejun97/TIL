package com.bigave.security.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    public Account createAccount(String username, String password, Role role){
        Account account = new Account();
        account.setUserName(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setRole(role);

        Account saveAccount = accountRepository.save(account);
//        httpSession.setAttribute("user", new SessionAccount(saveAccount));

        return saveAccount;

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Account> byUserName= accountRepository.findByUserName(userName);
        Account account =  byUserName.orElseThrow(()-> new UsernameNotFoundException(userName)); // 없으면 예외를 던지고, 있으면 Account 객체 return

        return new User(account.getUserName(), account.getPassword(), authorities(account));
    }

    private Collection<? extends GrantedAuthority> authorities(Account account) {
        return Arrays.asList(new SimpleGrantedAuthority(account.getRole().getKey()));
    }
}
