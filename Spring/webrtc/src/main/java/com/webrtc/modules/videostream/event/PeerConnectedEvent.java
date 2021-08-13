package com.webrtc.modules.videostream.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PeerConnectedEvent{

    private String sender;
    public PeerConnectedEvent(String sender) {
        this.sender =sender;
    }
}
