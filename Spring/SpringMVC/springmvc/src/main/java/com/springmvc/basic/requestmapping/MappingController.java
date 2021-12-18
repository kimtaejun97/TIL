package com.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Slf4j
@RestController
public class MappingController {

    // 기본적인 RequestMapping
    @RequestMapping(value = {"/hello-basic", "/hello-basic2"})
    public String helloBasic(){
        log.info("hello basic");

        return "OK";
    }

    // Request Method 설정
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("Mapping Get V1");

        return "OK";
    }

    /**
     * 편의 애노테이션
     * Get, Post, Put, Delete, Patch
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("Mapping Get V2");

        return "OK";
    }

    // PathVariable
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId /*@PathVariable("userId") String id */){
        log.info("User Id = {}", userId);

        return "OK";
    }

    /***
     * params 조건을 만족해야 함.
     * {"param1=value1", "param2=value2"}
     * "mode"  -- mode 파라미터 이름이 있어야한다.
     * '!', '=', '!='
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(@RequestParam("mode") String mode){
        log.info("mode = {}", mode);

        return "OK";
    }

    // headers에 명시된 해더가 일치해야 함. 사용은 params 와 동일.
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(@RequestHeader("mode") String mode){
        log.info("mode = {}", mode);

        return "OK";
    }

    // consumes: Content-Type 이 일치해야 호출 가능, 일치하지 않으면 415 Unsupported Media Type
    // produces: Accept 헤더와 일치해야 함. 일치하지 않으면 406 Not Acceptable
    // 마찬가지로 {}로 배열로 지정도 가능하다.
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public String mappingConsumes(){
        return "OK";
    }




}
