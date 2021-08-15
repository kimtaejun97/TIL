package com.studyweb.studyweb.modules.main;


import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public String handleRuntimeException(@CurrentUser Account account, HttpServletRequest req, RuntimeException e){
        if(account != null){
            log.info("{} Requested {}", account.getNickName(), req.getRequestURI());
        }
        else{
            log.info("Requested {}",  req.getRequestURI());
        }
        log.error("Bad Request : ", e);

        return "error";

    }
}
