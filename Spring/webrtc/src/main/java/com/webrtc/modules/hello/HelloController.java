package com.webrtc.modules.hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class HelloController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        // 추가
        simpMessageHeaderAccessor.getSessionAttributes().put("sender", message.getName());

        return new Greeting("Hello " + HtmlUtils.htmlEscape(message.getName()) +"!");

    }
    @MessageMapping("/hello2")
    public void greeting2(HelloMessage message, SimpMessageSendingOperations simpMessageSendingOperations){
        simpMessageSendingOperations.convertAndSendToUser(message.getName(), "/topic/public",message);

    }
}
