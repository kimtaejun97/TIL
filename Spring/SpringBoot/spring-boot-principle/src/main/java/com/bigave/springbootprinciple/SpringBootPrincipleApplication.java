package com.bigave.springbootprinciple;

import com.bigave.bigavespringbootstarter.Holoman;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootPrincipleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPrincipleApplication.class, args);
    }

//    @Bean
//    public Holoman holoman(){
//        Holoman holoman = new Holoman();
//        holoman.setName("Taejun");
//        holoman.setHowLong(1);
//        return holoman;
//
//    }

}
