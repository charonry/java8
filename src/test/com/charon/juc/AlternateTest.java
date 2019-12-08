package com.charon.juc;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：线程交替运行
 * @date : 16:03 2019/12/8
 */
public class AlternateTest {
    public static void main(String[] args) {
        AlternateDemo alternateDemo=new AlternateDemo();
        new Thread(() -> {
            for(int i=0;i<20;i++){
                alternateDemo.loopA(i);
            }
        },"A").start();

        new Thread(() -> {
            for(int i=0;i<20;i++){
                alternateDemo.loopB(i);
            }
        },"B").start();

        new Thread(() -> {
            for(int i=0;i<20;i++){
                alternateDemo.loopC(i);
                System.out.println("-----------------");
            }
        },"C").start();
    }
}
