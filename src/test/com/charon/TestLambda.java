package com.charon;

import com.charon.entity.Employee;
import com.charon.lambda.MyPredicate;
import com.charon.lambda.impl.FilterEmployeeByAge;
import org.junit.Test;

import java.util.*;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 21:57 2019/12/3
 */
public class TestLambda {

    @Test
    public void test(){
        /*Comparator<Integer> comparator=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1,Integer o2) {
                return Integer.compare(o1,o2);
            }
        };*/
        // lambda表达式
        Comparator<Integer> comparator= (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet=new TreeSet(comparator);
    }

    List<Employee> list= Arrays.asList(
            new Employee("Charon",22,9000.00),
            new Employee("chane",23,8000.00),
            new Employee("Wendy",15,300.00),
            new Employee("poppy",32,2000.00)
    );

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> myPredicate){
        List<Employee> emps=new ArrayList();
        for(Employee employee:list){
            if(myPredicate.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

    // 策略设计模式
    @Test
    public void test2(){
        List<Employee> employees = filterEmployee(list, new FilterEmployeeByAge());
        for (Employee employee:employees){
            System.out.println(employee);
        }
    }

    // lambda表达式优化
    @Test
    public void test3(){
        List<Employee> employees = filterEmployee(list, (employee) ->employee.getAge()>=23);
        employees.forEach(System.out::println);
    }

    // stream API流优化
    @Test
    public void  test4(){
        list.stream()
             .filter((e)->e.getAge()<=22)
             .limit(2)
             .forEach(System.out::println);
        System.out.println("----------------------------");
        list.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
