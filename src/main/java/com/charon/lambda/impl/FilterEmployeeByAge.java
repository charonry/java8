package com.charon.lambda.impl;

import com.charon.entity.Employee;
import com.charon.lambda.MyPredicate;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 22:43 2019/12/3
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>20;
    }
}
