package com.springmvc.basic.request;

import com.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // 요청 파라미터(쿼리 파라미터, HTML Form) HttpServletRequest 이용하여 처리.
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("OK");;
    }

    // 요청 파라미터 @RequestParam 이용. 한개의 이름에 여러 값이 들어오면 kim1,kim2 와 같이 들어온다.
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String name, @RequestParam("age") int age){
        log.info("username = {}, age = {}", name, age);
        return "OK";
    }

    // 요청 파라미터 이름과 동일하게 파라미터 이름을 설정하면 요청 파라미터 이름 생략가능.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age){
        log.info("username = {}, age = {}", username, age);
        return "OK";
    }


    // 단순타입(String, int, Integer ..) 이면 @RequestParam 도 생략 가능.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username = {}, age = {}", username, age);
        return "OK";
    }


    // required 속성. default 는 true, 400 BadRequest 발생, 빈문자("")가 들어가면 값이 들어온 것으로 간주하기 때문에 주의.
    // false 일 경우에 해당 값이 들어오지 않으면 null 로 채워준다. 자바에서는 원시형에 null을 넣을 수 없기 때문에 서버 에러가 발생한다.
    // 때문에 원시형이 아닌 Wrapper 클래스로 선언.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age){

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }


    // 값이 들어오지 않았을 때의 기본값, 빈 문자의 경우에도 기본값을 넣어준다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefaultValue(@RequestParam(defaultValue = "GUEST") String username,
                                       @RequestParam(defaultValue = "-1") int age){

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    // Map, MultiValueMap 사용.
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> params){

        log.info("username = {}, age = {}", params.get("username"), params.get("age"));
        return "OK";
    }

    // 객체를 생성하고 setter로 주입, Model 객체에도 자동으로 넣어준다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String requestParamMap(@ModelAttribute("data") HelloData helloData, Model model){
        HelloData helloData1 = (HelloData)model.getAttribute("data");
        log.info(helloData1.toString());

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String requestParamMapV2(HelloData helloData){

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }



}
