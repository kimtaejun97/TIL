package com.webrtc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webrtc.modules.handler.SignalHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ObjectMapper objectMapper;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SignalHandler(objectMapper), "/signal")
                .setAllowedOriginPatterns("*");
    }
}
