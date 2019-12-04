package com.charon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：lanbda四大核心函数式接口
 * @date : 23:20 2019/12/4
 */
public class TestCoreLambda {

    // Consumer<T>:消费性接口
    public void comsumer(String name, Consumer<String> consumer){
        consumer.accept(name);
    }
    @Test
    public void testComsumer(){
        comsumer("charon",(name)-> System.out.println("hello "+name));
    }

    // Supplier<T>:供给型接口
    public List<Integer> supplier(Integer num, Supplier<Integer> supplier){
        List list=new ArrayList();
        for(int i=0;i<num;i++){
            Integer n=supplier.get();
            list.add(n);
        }
        return  list;
    }
    @Test
    public void  tetstSupplier(){
        List<Integer> lists = supplier(10, () -> (int) (Math.random() * 100));
        for(Integer integer:lists){
            System.out.print(integer+"\t");
        }
    }

    // Function<T,R>:函数型接口
    public String function(String string, Function<String,String> function){
        return function.apply(string);
    }
    @Test
    public void testFunction(){
        System.out.println(function("charon",(x)->x.toUpperCase()));
    }

    // Predicate<T>:断言型接口
    public List<String> predicate(List<String> list, Predicate<String> predicate){
        List lists=new ArrayList();
       for(String string:list){
           if( predicate.test(string)){
               lists.add(string);
           }
       }
       return  lists;
    }
    @Test
    public void  testPredicate(){
        List<String> lists = predicate(Arrays.asList("a", "b", "aa", "aaa"), (x) -> x.length() == 1);
        for (String string:lists){
            System.out.println(string);
        }

    }
}
