package com.webrtc.modules.videostream;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class VideoStreamController {

    @MessageMapping("/addPeer")
    @SendTo("/uuid/public")
    public VideoStream addPeer(@Payload VideoStream videoStream, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        simpMessageHeaderAccessor.getSessionAttributes().put("url", videoStream.getUrl());
        simpMessageHeaderAccessor.getSessionAttributes().put("streamId", videoStream.getStreamId());
        simpMessageHeaderAccessor.getSessionAttributes().put("sender", videoStream.getSender());
        simpMessageHeaderAccessor.getSessionAttributes().put("type", videoStream.getType());

        return videoStream;
    }


}
