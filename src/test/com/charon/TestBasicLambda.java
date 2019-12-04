package com.charon;

import com.charon.entity.Employee;
import com.charon.lambda.impl.MyInterface;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：lambda表达式的基础语法
 * @date : 21:33 2019/12/4
 */
public class TestBasicLambda {

    // 语法格式一:无参无返回
    @Test
    public void study(){
        String name="charon";
        Runnable runnable=()-> System.out.println("Hello Lambda"+"\t"+name);
        runnable.run();
    }

    // 语法格式二:有参数无返回值（只有一个参数，小括号可以不写）
    @Test
    public void study2(){
        Consumer consumer=(x)-> System.out.println(x);
        consumer.accept("hello charon this is lambda");
    }


    // 语法格式三: 多个参数，多条语句，有返回值
    @Test
    public void study3(){
        Comparator<Integer> comparator=(x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };

        Comparator<Integer> com=(x,y)->Integer.compare(x,y);;
    }

    List<Employee> list= Arrays.asList(
            new Employee("Charon",22,9000.00),
            new Employee("chane",23,8000.00),
            new Employee("Wendy",15,300.00),
            new Employee("poppy",32,2000.00)
    );

    @Test
    public void test(){
        Collections.sort(list,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return e1.getAge().compareTo(e2.getAge());
            }
        });
        for (Employee employee:list){
            System.out.println(employee);
        }
    }

    public Long op(Long l1, Long l2, MyInterface<Long,Long> myInterface){
        return  myInterface.getValue(l1,l2);
    }
    @Test
    public void  getOp(){
        Long result = op(100L, 200L, (x, y) -> x + y);
        System.out.println(result);
    }
}
