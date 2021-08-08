package com.studyweb.studyweb.modules.notification;

import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpClient;
import java.security.Principal;

@RequiredArgsConstructor
@Component
public class NotificationHandlerInterceptor implements HandlerInterceptor {

    private final NotificationRepository notificationRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(modelAndView !=null && !isRedirectview(modelAndView) && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserAccount){
            Account account = ((UserAccount) authentication.getPrincipal()).getAccount();
            Long count = notificationRepository.countNotificationByAccountAndChecked(account, false);

            modelAndView.addObject("hasNotification", count>0);
            modelAndView.addObject("numberOfNotification", count);


        }

    }

    private boolean isRedirectview(ModelAndView modelAndView) {
        return modelAndView.getViewName().startsWith("redirect:") || modelAndView.getView() instanceof RedirectView;

    }
}
