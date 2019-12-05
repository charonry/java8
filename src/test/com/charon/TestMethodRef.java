package com.charon;

import com.charon.entity.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：方法引用和构造器引用
 * @date : 23:53 2019/12/4
 */
public class TestMethodRef {

    // 对象::实例方法名
    @Test
    public void test1(){
        // 方法引用
        Consumer<String> consumer=(x)-> System.out.println(x);
        consumer.accept("hello");
        Consumer<String> con=System.out::println;
        con.accept("charon");

        // 方法引用
        Employee emp=new Employee("charon",23,9000.00);
        Supplier<Integer> supplier=emp::getAge;
        System.out.println(supplier.get());


    }

    // 类::静态方法名
    @Test
    public void test2(){
        Comparator<Integer> comparator=(x,y)->Integer.compare(x,y);

        Comparator<Integer> com=Integer::compare;

    }

    // 类::实例方法名
    @Test
    public void test3(){
        BiPredicate<String,String> biPredicate=(x,y)->x.equals(y);
        BiPredicate<String,String> bp=String::equals;
    }

     // 构造器引用方式
    @Test
    public void test4(){
        // 构造器引用
        Supplier<Employee> supplier=Employee::new;
        supplier.get();

        Function<Integer,Employee> function=Employee::new;
        System.out.println(function.apply(23));
    }
     // 数组引用
    @Test
    public void test5(){
        Function<Integer,String[]> function=String[]::new;
        System.out.println(function.apply(10).length);
    }
}
