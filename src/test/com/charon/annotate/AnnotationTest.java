package com.charon.annotate;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：重复注解和注解类型
 * @date : 1:20 2019/12/8
 */
public class AnnotationTest {

    @Test
    public void test() throws Exception {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Method method = clazz.getMethod("show");
        MyAnnotation[] myAnnotations = method.getAnnotationsByType(MyAnnotation.class);
        for(MyAnnotation myAnnotation:myAnnotations){
            System.out.println(myAnnotation.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("charon")
    public void show(){};
}
