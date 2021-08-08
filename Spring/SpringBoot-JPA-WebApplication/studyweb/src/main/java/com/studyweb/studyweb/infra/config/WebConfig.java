package com.studyweb.studyweb.infra.config;

import com.studyweb.studyweb.modules.notification.NotificationHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final NotificationHandlerInterceptor notificationHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> staticResources = Arrays.stream(StaticResourceLocation.values())
                .flatMap(StaticResourceLocation::getPatterns)
                .collect(Collectors.toList());
        staticResources.add("/code_modules/**");

        registry.addInterceptor(notificationHandlerInterceptor)
                .excludePathPatterns(staticResources);

    }
}
