package com.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlet.basic.HelloData;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println(messageBody);

        resp.getWriter().write("OK");
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("=== Convert ===");
        System.out.println(helloData);

    }
}
