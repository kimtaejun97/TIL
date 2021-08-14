package com.webrtc.modules.handler;

import lombok.Data;

@Data
public class Message {
    private String type;
    private String roomId;
    private String peerId;
    private String userSessionId;
    private String streamId;
    private Object sdp;




}
