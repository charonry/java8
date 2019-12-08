package com.charon.juc;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 16:57 2019/12/8
 */
public class ThreadPoolDemo implements Runnable {
    private int i=0;

    @Override
    public void run() {
        while (i<100){
            System.out.println(Thread.currentThread().getName()+":"+ i++);
        }
    }
}
