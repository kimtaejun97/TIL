package com.webrtc.modules.videostream;

import com.webrtc.modules.videostream.event.PeerConnectedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class VideoStreamController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @MessageMapping("/addPeer")
    @SendTo("/uuid/public")
    public VideoStream addPeer(@Payload VideoStream videoStream, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        simpMessageHeaderAccessor.getSessionAttributes().put("url", videoStream.getUrl());
        simpMessageHeaderAccessor.getSessionAttributes().put("streamId", videoStream.getStreamId());
        simpMessageHeaderAccessor.getSessionAttributes().put("sender", videoStream.getSender());
        simpMessageHeaderAccessor.getSessionAttributes().put("type", videoStream.getType());

        applicationEventPublisher.publishEvent(new PeerConnectedEvent(videoStream.getSender()));

        return videoStream;
    }



}
