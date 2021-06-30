package com.bigave.embeddedwebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@SpringBootApplication
public class EmbeddedwebserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedwebserverApplication.class, args);
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8080);
//
//        Context context = tomcat.addContext("/", "/");
//
//
//        HttpServlet servlet = new HttpServlet() {
//            @Override
//            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                PrintWriter writer = resp.getWriter();
//                writer.println("<html><head><title>");
//                writer.println("hello tomcat");
//                writer.println("</title></head/>");
//                writer.println("</html>");
//            }
//        };
//        String servletName = "helloServlet";
//        tomcat.addServlet("/",servletName, servlet);
//        context.addServletMappingDecoded("/hello",servletName);
//
//        tomcat.start();
//        tomcat.getServer().await();

    }

}
