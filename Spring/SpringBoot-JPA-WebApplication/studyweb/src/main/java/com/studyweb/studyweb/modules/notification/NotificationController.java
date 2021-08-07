package com.studyweb.studyweb.modules.notification;

import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    @GetMapping("")
    public String ViewNewNotifications(@CurrentUser Account account, Model model){
        
        List<Notification> newNotifications = notificationRepository.findByAccountAndCheckedOrderByCreatedLocalDateTimeDesc(account, false);
        long numberOfChecked = notificationRepository.countNotificationByAccountAndChecked(account, true);

        putCateforizedNotificationsToModel(model, newNotifications);

        model.addAttribute("numberOfChecked", numberOfChecked);
        model.addAttribute("numberOfNotChecked",newNotifications.size());
        model.addAttribute("isNew",true);


        return "notification/list";
    }

    @GetMapping("/old")
    public String ViewOldNotifications(@CurrentUser Account account, Model model){
        List<Notification> oldNotifications = notificationRepository.findByAccountAndCheckedOrderByCreatedLocalDateTimeDesc(account, true);

        long numberOfNotChecked = notificationRepository.countNotificationByAccountAndChecked(account, false);

        putCateforizedNotificationsToModel(model, oldNotifications);

        model.addAttribute("numberOfChecked", oldNotifications.size());
        model.addAttribute("numberOfNotChecked",numberOfNotChecked);
        model.addAttribute("isNew",false);


        return "notification/list";


    }

    private void putCateforizedNotificationsToModel(Model model, List<Notification> notifications) {
        List<Notification> StudyNotifications = notifications.stream()
                .filter(notification -> notification.getNotificationType().equals(NotificationType.STUDY_CREATED))
                .collect(Collectors.toList());

        List<Notification> eventEnrollmentNotifications = notifications.stream()
                .filter(notification -> notification.getNotificationType().equals(NotificationType.EVENT_ENROLLMENT))
                .collect(Collectors.toList());

        List<Notification> watchingStudyNotifications = notifications.stream()
                .filter(notification -> notification.getNotificationType().equals(NotificationType.STUDY_UPDATED))
                .collect(Collectors.toList());

        model.addAttribute("notifications", notifications);
        model.addAttribute("newStudyNotifications",StudyNotifications);
        model.addAttribute("eventEnrollmentNotifications",eventEnrollmentNotifications);
        model.addAttribute("watchingStudyNotifications",watchingStudyNotifications);
    }

    @DeleteMapping("")
    public String removeOldNotifications(@CurrentUser Account account){
        notificationService.removeNotifications(account);

        return "redirect:/notifications";
    }

    @PostMapping("/checked/{notificationId}")
    public String checkedNotification(@CurrentUser Account account, @PathVariable Long notificationId) throws IllegalAccessException {
        Notification notification = notificationService.getNotification(account, notificationId);
        notificationService.checkedNotification(notification);

        return "redirect:/notifications";
    }

    @PostMapping("/checked/all")
    public String chekedAllNotifications(@CurrentUser Account account){
        List<Notification> notifications = notificationRepository.findByAccountAndChecked(account, false);
        notificationService.checkedAllNotifications(notifications);

        return "redirect:/notifications";

    }


}
