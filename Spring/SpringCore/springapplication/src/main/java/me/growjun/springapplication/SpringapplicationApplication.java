package me.growjun.springapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringapplicationApplication {

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        String[] beanDefinitionNames =  context.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
//
//        BookService bookService = context.getBean("bookService",BookService.class);
//        System.out.println(bookService.bookRepository);
    }

}
