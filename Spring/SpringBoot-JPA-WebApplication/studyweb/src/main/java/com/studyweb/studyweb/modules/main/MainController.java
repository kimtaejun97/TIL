package com.studyweb.studyweb.modules.main;

import com.studyweb.studyweb.modules.account.CurrentUser;
import com.studyweb.studyweb.modules.account.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentUser Account account, Model model){
        if(account != null){
            model.addAttribute(account);
        }

        return "index";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
