package com.bigave.autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import outpackage.MyService;

@SpringBootApplication
public class AutowiredApplication {

    public static void main(String[] args) {
//        Static 메서드로 애플리케이션 구동.
        //        SpringApplication.run(AutowiredApplication.class, args);

        // 인스턴스 생성으로 애플리케이션 구동.
        var app = new SpringApplication(AutowiredApplication.class);
        app.addInitializers(new ApplicationContextInitializer<GenericApplicationContext>() {
            @Override
            public void initialize(GenericApplicationContext ctx) {
                // function을 이용한 외부 패키지 클래스 Bean 등록.
                ctx.registerBean(MyService.class);
            }
        });
        app.run(args);
    }

}
