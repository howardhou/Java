package com.example.javasyntax.annotation;

import java.lang.reflect.Method;

/**
 * @author ：howard
 * @description：测试注解的反射
 * @date ：Created in 2020/3/14 11:27
 */
public class AnnotationTest {
    @Hello("hello world")
    public static void main(String[] args) throws NoSuchMethodException {
        Class cls = AnnotationTest.class;
        Method method = cls.getMethod("main", String[].class);

        Hello hello = method.getAnnotation(Hello.class);


        System.out.println(hello.value());
    }
}
