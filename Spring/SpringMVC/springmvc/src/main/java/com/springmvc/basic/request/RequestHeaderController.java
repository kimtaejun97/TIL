package com.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @GetMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headers, /* MultiValueMap: 하나의 키에 여러 값을 받을 수 있다(배열)*/
                          @RequestHeader("host") String host,
                          @CookieValue(value = "cookieName", required = false) String cookie){

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale); // 우선순위가 가장 높은 것.
        log.info("headerMap={}", headers);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "OK";
    }
}
