package com.charon.juc;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：读写锁：
 * @date : 16:23 2019/12/8
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReadWriteLockDemo rw=new ReadWriteLockDemo();
        new Thread(() -> {
            rw.set((int)(Math.random()*100));
        },"Wirte").start();

        for(int i =0;i<100;i++){
            new Thread(() -> {
                rw.get();
            },"Reader").start();
        }
    }
}
