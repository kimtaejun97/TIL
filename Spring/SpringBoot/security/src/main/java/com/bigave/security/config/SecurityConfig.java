package com.bigave.security.config;

import com.bigave.security.account.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole(Role.ADMIN.name())  // hello URL은 관리자 접근 가능.
                .antMatchers("/my").hasRole(Role.STUDENT.name())  // my URL 은 학생만 접근 가능
                .anyRequest().permitAll() // 나머지 URL은 인증 필요.
                .and()
            .formLogin()// 만들어주는 formLogin template 사용.
                .and()
            .httpBasic() // accept header에 http가 없는 경우 httpBasic 사용.
                .and()
            .logout()
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


