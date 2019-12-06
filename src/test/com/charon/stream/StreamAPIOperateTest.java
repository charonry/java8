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
 * @Describe ：中间操作
 * @date : 21:55 2019/12/5
 */
public class StreamAPIOperateTest {
    List<Employee> list= Arrays.asList(
            new Employee("Charon",22,9000.00),
            new Employee("chane",23,8000.00),
            new Employee("Wendy",15,3000.00),
            new Employee("poppy",32,20000.00),
            new Employee("poppy",32,20000.00),
            new Employee("poppy",32,20000.00)
    );

    @Test
    public void test(){
        Stream<Employee> stream = list.stream()
                                        .filter((e) -> {
                                             System.out.println("Stream API 中间操作");
                                             return e.getAge() < 25;
                                         })
                                        .limit(2);
        stream.forEach(System.out::println);

        list.stream()
                .skip(2)
                .filter((e)->{
                    return e.getAge()>20;
                })
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<String> lists=Arrays.asList("aa","bb","cc","dd");
        lists.stream().map((x)->x.toUpperCase())
                        .forEach(System.out::println);

        System.out.println("-----------------------");

        list.stream().map(Employee::getName)
                        .forEach(System.out::println);

        System.out.println("-----------------------");

        Stream<Stream<Character>> stream = lists.stream().map(StreamAPIOperateTest::filterCharacter);
        stream.forEach((sm)->{
            sm.forEach(System.out::println);
        });

        System.out.println("-----------------------");
        // 将多个流整合成一个流
        Stream<Character> sm = lists.stream().flatMap(StreamAPIOperateTest::filterCharacter);
        sm.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String string){
        List list=new ArrayList();
        for(Character character:string.toCharArray()){
            list.add(character);
        }
        return  list.stream();
    }

    // 自然排序
    @Test
    public void test3(){
        List<String> lists=Arrays.asList("aa","bb","cc","dd");
        lists.stream().sorted().forEach(System.out::println);

        list.stream().sorted((e1,e2)->{
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }
}
