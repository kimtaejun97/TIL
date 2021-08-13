package com.webrtc.modules.videostream.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async // TODO 아직 처리 안함.
@RequiredArgsConstructor
@Component
@Slf4j
public class PeerEventHandler {

    @EventListener
    public void handlePeerConnectedEvent(PeerConnectedEvent peerConnectedEvent){
        log.info(peerConnectedEvent.getSender() + "is Connected");

    }

}
