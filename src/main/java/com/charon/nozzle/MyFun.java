package com.charon.nozzle;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 23:22 2019/12/7
 */
public interface MyFun {
    default String getName(){
        return "charon";
    }

    public static void show(){
        System.out.println("这是一个静态方法");
    }
}
