package com.webrtc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket을 사용할 수 없는 경우 FallBack 옵션 활성화.
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //message handling methods로 라우팅
        registry.setApplicationDestinationPrefixes("/app");

        //간단한 인메모리 기반. message broker로 라우팅.
        registry.enableSimpleBroker("/topic");
    }
}
