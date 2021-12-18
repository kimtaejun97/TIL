package com.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        log.trace("Trace log = {}", name);
        log.debug("Debug log = {}", name ); // 개발 서버에서 디버그용으로 남길 정보.
        log.info("Info log = {}",name); // 운영 서버에도 남길 정보. default 로깅 레벨.
        log.warn("Warn log = {}", name );
        log.error("Error log = {}", name );


        return "OK";
    }
}
