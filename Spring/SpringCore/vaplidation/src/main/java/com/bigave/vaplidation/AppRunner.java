package com.bigave.vaplidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {

    @Qualifier("defaultValidator")
    @Autowired
    Validator validator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(validator.getClass());
        Event event = new Event();
        event.setLimit(-1);
        event.setEmail("Asd");
        // Spring MVC 가 자동으로 생성해서 전달해 줌. 실제로 잘 사용할 일이 없다.
        Errors errors = new BeanPropertyBindingResult(event,"event");

        // event 객체를 검증해 errors 객체에 담아준다.
        validator.validate(event,errors);

        System.out.println(errors.hasErrors());

        errors.getAllErrors().forEach(e ->{
            System.out.println("=== error code ===");
            Arrays.stream(e.getCodes()).forEach(System.out::println);
            System.out.println(e.getDefaultMessage());
        });
    }
}
