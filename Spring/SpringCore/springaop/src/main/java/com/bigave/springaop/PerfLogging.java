package com.bigave.springaop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) //Default : CLASS파일까지 유지. SOURCE : 컴파일 까지 유지. RUNTIME: 런타임 까지.
@Target(ElementType.METHOD)
public @interface PerfLogging {
}
