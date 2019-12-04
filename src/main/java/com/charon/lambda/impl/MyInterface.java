package com.charon.lambda.impl;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 22:25 2019/12/4
 */
public interface MyInterface<T,R> {
    public R getValue(T t1,T t2);
}
