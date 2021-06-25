package com.bigave.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.format.Parser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class AppRunner implements ApplicationRunner {

    // 표현식
    @Value("#{1+1}")
    int value;

    @Value("#{'hello' + ' world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    // 프로퍼티
    @Value("${my.value}")
    String myValue;

    // 표현식{프로퍼}
    @Value("#{${my.value} + 'aaa'}")
    String myValueAAA;

    // Bean의 필드값
    @Value("#{sampleBean.data}")
    int sampleData;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExpressionParser parser = new SpelExpressionParser();

        // List
        List<String> l = parser.parseExpression("{'a','b','c','d'}").getValue(List.class);
        // Method
        String bc = parser.parseExpression("'abc'.substring(1,3)").getValue(String.class);

        System.out.println(value);
        System.out.println(greeting);
        System.out.println(trueOrFalse);
        System.out.println(myValue);
        System.out.println(myValueAAA);
        System.out.println(sampleData);
        System.out.println(l.toString());
        System.out.println(bc);



    }
}
