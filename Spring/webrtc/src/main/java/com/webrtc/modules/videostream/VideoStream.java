package com.webrtc.modules.videostream;

import lombok.Data;

@Data
public class VideoStream {

    private String url;
    private MessageType type;
    private String sender;
    private String streamId;
}
