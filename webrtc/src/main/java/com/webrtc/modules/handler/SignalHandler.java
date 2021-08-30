package com.webrtc.modules.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webrtc.modules.room.Room;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiredArgsConstructor
@Slf4j
@Component
public class SignalHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private Map<String, Room> rooms = new HashMap<>();

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        Message message = objectMapper.readValue(textMessage.getPayload(), Message.class);
        String peerId = message.getPeerId();
        String streamId = message.getStreamId();

        switch (message.getType()){
            case "join" :
                Room room = rooms.get(message.getRoomId());
                if(room == null){
                    log.info("Create Room! " + message.getRoomId());
                    room = new Room();
                    room.setRoomId(message.getRoomId());
                    room.getClient().put(message.getUserSessionId(), session);
                    rooms.put(message.getRoomId(), room);
                }
                else{
                    log.info("Join Room! " + message.getRoomId());
                    room.getClient().put(message.getUserSessionId(), session);
                }
                for(WebSocketSession client : room.getClient().values()){
                    if(!client.getId().equals(session.getId())){
                        message.setType("user-connected");
                        TextMessage connectedMessage = new TextMessage(objectMapper.writeValueAsString(message));

                        client.sendMessage(connectedMessage);
                        log.info("send User Connected Message");
                    }
                }
                break;
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connect : " + session.getId());
        sessions.add(session);
        Message message = new Message();
        message.setType("connect");
        message.setUserSessionId(session.getId());

        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(message));
        session.sendMessage(textMessage);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
