package com.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.ClientEndpointHolder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Controller
public class RequestBodyStringController {

    // HttpServletRequest
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // Stream 은 바이트이기 때문에 항상 인코딩을 지정해 주는 것이 좋다.

        log.info("message Body = {}", messageBody);

        response.getWriter().write("OK");
    }


    // Stream
    @PostMapping("/request-body-string-v2")
    public void requestBodyStream(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message Body = {}", messageBody);
        writer.write("OK");
    }

    // HttpEntity, HttpMessageConverter 가 변환해준다.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyEntityV1(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();

        log.info("message Body = {}", messageBody);
        log.info("headers = {}", headers);

        return new HttpEntity<>("OK");
    }

    // RequestEntity, ResponseEntity. HttpEntity 를 상속.
    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyEntityV2(RequestEntity<String> requestEntity) throws IOException {

        String messageBody = requestEntity.getBody();
        HttpHeaders headers = requestEntity.getHeaders();
        requestEntity.getMethod();
        requestEntity.getUrl();

        log.info("message Body = {}", messageBody);
        log.info("headers = {}", headers);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    // 애노테이션
    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public String requestBodyAnnotation(@RequestBody String messageBody, @RequestHeader Map<String, Object> headers) throws IOException {

        log.info("message Body = {}", messageBody);
        log.info("headers = {}", headers);

        return "OK";
    }

}
