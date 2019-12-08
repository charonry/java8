package com.charon.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 14:43 2019/12/8
 */
public class Ticket implements Runnable{
    private int tick=100;

    private Lock lock=new ReentrantLock();

    @Override
    public void run() {
        while (true){
            // 上锁
            lock.lock();
            try {
                if (tick>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName()+"完成售票，余票为"+(--tick));
                }
            } catch (Exception e) {
            }finally {
                // 释放锁
                lock.unlock();
            }
        }
    }
}
