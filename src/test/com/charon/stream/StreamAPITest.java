package com.charon.stream;

import com.charon.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：创建流
 * @date : 21:34 2019/12/5
 */
public class StreamAPITest {

    @Test
    public void getStream(){
        // 1.可以通过collection系列集合提供的stream()[串行流]或者paralleStream()[并行流]
        List list=new ArrayList();
        Stream stream=list.stream();

        // 2.通过Arrays中静态方法stream（）获取数据流
        Employee[] emps=new Employee[10];
        Stream<Employee> empsStream= Arrays.stream(emps);

        // 3.通过Stream类中的静态方法of()
        Stream<String> streamOf=Stream.of("AA","BB","CC");

        // 4.创建无限流
        Stream stream4=Stream.iterate(0,(x)->x+1);
        stream4.limit(10)
               .forEach(System.out::println);

        //生成
        Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);
    }
}
