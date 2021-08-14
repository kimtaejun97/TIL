package com.webrtc.modules.room;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Data
public class Room {
    private String roomId;

    //session id, webSocketSession
    Map<String, WebSocketSession> client = new HashMap<>();
}
