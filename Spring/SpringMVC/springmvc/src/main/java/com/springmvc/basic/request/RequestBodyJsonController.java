package com.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    private String requestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        return "OK";
    }

    // MappingJackson2HttpMessageConverter 가 동작. HttpEntity<HelloData> 도 가능.
    // @RequestBody 를 생략하면 @ModelAttribute가 되기 때문에, messageBody의 값을 읽어올 수 없다 따라서 null, 0 이 채워짐.(필드의 기본값)
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    private HelloData requestBodyJsonV3(@RequestBody HelloData helloData) throws JsonProcessingException {
        log.info("helloData = {}", helloData);

        return helloData;
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    private String requestBodyJsonV4(HttpEntity<HelloData> entity) throws JsonProcessingException {
        log.info("helloData = {}", entity.getBody());
        log.info("headers = {}", entity.getHeaders());

        return "OK";
    }
}
