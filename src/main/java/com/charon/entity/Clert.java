package com.charon.entity;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 15:13 2019/12/8
 */
public class Clert {
    private int product=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public  void get(){
        lock.lock();
        try {
            while (product>=1){
                System.out.println("产品已满");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ ++product);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void sale(){
        lock.lock();
        try {
            while (product<=0){
                System.out.println("缺货");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ --product);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
