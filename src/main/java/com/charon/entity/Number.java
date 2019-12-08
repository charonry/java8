package com.charon.entity;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 16:36 2019/12/8
 */
public class Number {

    public synchronized void getOne(){
        System.out.println("one");
    }

    public synchronized void getTwo(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("two");
    }
    public  void getThree(){
        System.out.println("Three");
    }
}
