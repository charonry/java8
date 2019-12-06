package com.charon.stream;

import com.charon.entity.Employee;
import com.charon.entity.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ： 对于Stream流的中断操作学习
 * @date : 23:11 2019/12/5
 */
public class StreamAPIStopTest {
    List<Employee> emps= Arrays.asList(
            new Employee("Charon",22,9000.00, Status.FREE),
            new Employee("chane",23,8000.00,Status.BUZY),
            new Employee("Wendy",15,3000.00,Status.VOCATION),
            new Employee("poppy",32,20000.00,Status.BUZY),
            new Employee("poppy",32,20000.00,Status.FREE)

    );

    // 查找与匹配
    @Test
    public void  test(){
        boolean b1 = emps.stream().allMatch((e) -> Status.BUZY.equals(e.getStatus()));
        System.out.println(b1);

        boolean b2 = emps.stream().anyMatch((e) -> Status.BUZY.equals(e.getStatus()));
        System.out.println(b2);

        boolean b3 = emps.stream().noneMatch((e) -> Status.BUZY.equals(e.getStatus()));
        System.out.println(b3);

        Optional<Employee> op = emps.stream().sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println(op.get());

        Optional<Employee> optional = emps.parallelStream().filter((e) -> Status.FREE.equals(e.getStatus())).findAny();
        System.out.println(optional.get());
    }

    @Test
    public void test2(){
        long count = emps.stream().count();
        System.out.println(count);

        Optional<Employee> op = emps.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op);

        Optional<Double> optional = emps.stream().map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(optional);
    }

    // 归约：将六月中值反复结合得到一个值
    @Test
    public void test3(){
        List<Integer> lists=Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer in = lists.stream().reduce(0, (x, y) -> x + y);
        System.out.println(in);

        Optional<Double> op = emps.stream().filter((e)->e.getAge()<23).map(Employee::getSalary).reduce(Double::sum);
        System.out.println(op.get());
    }

    // 收集
    @Test
    public void test4(){
        emps.stream().map(Employee::getName).collect(Collectors.toList()).forEach(System.out::print);
        System.out.println();
        emps.stream().map(Employee::getName).collect(Collectors.toSet()).forEach(System.out::print);
        System.out.println();
        emps.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new)).forEach(System.out::print);
    }

    @Test
    public void test5(){
        // 总数
        Long along = emps.stream().collect(Collectors.counting());
        System.out.println(along);
        // 平均值
        Double aDouble = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(aDouble);
        // 总和
        Double aDoubleSum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(aDoubleSum);
       // 最大值
        Optional<Employee> op = emps.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(op.get());
    }

    // 分组
    @Test
    public void test6(){
        Map<Status, List<Employee>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    // d多级分组
    @Test
    public void test7(){
        Map<Status, Map<String, List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() < 20) {
                        return "少年";
                    } else if (((Employee) e).getAge() < 30) {
                        return "青少年";
                    } else {
                        return "青年";
                    }
                })));
        System.out.println(map);
    }

    // 分区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> map = emps.stream().collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(map);
    }

    @Test
    public void test9(){
        DoubleSummaryStatistics collect = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getSum());
        System.out.println(collect.getAverage());
    }

    @Test
    public void test10(){
        String collect = emps.stream().map(Employee::getName).collect(Collectors.joining(",","开始","结束"));
        System.out.println(collect);
    }
}
