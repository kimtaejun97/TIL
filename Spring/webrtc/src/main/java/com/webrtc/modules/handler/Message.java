package com.webrtc.modules.handler;

import lombok.Data;

@Data
public class Message {
    private String sender;
    private String type;
    private String data;
    private Object candidate;
    private Object sdp;

    public Message(final String sender,
                            final String type,
                            final String data,
                            final Object candidate,
                            final Object sdp) {
        this.sender = sender;
        this.type = type;
        this.data = data;
        this.candidate = candidate;
        this.sdp = sdp;
    }

}
