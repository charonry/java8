package com.charon.stream;

import com.charon.entity.Employee;
import com.charon.entity.Status;
import org.junit.Test;

import java.util.Optional;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：optional容器
 * @date : 22:42 2019/12/6
 */
public class OptionalTest {

    @Test
    public void test(){
        Optional<Employee> op = Optional.ofNullable(null);
        // 判断容器是否含有值
        if(op.isPresent()){
            System.out.println(op.get());
        }
        // op没有值就返回该值
        /*Employee emp = op.orElse(new Employee("charon", 23, 9000.00,Status.FREE));
        System.out.println(emp);*/

        Employee emp = op.orElseGet(Employee::new);
        System.out.println(emp);
    }

    @Test
    public void test2(){
        Optional<Employee> emp = Optional.ofNullable(new Employee("charon", 23, 9000.00,Status.FREE));
        Optional<String> op = emp.map((e) -> e.getName());
        System.out.println(op.get());
    }

    @Test
    public void test3(){
        Optional<Employee> emp = Optional.ofNullable(new Employee("charon", 23, 9000.00,Status.FREE));
        // 返回对象必须是一个optional
        Optional<String> op = emp.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op.get());
    }
}
