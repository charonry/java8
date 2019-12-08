package com.charon.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 16:03 2019/12/8
 */
public class AlternateDemo {
    // 当前正在执行线程的标记
    private int number=1;
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    public void loopA(int totalLoop){
        lock.lock();

        try {
            if(number!=1){
                condition1.await();
            }
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            number=2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void loopB(int totalLoop){
        lock.lock();

        try {
            if(number!=2){
                condition2.await();
            }
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            number=3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void loopC(int totalLoop){
        lock.lock();

        try {
            if(number!=3){
                condition3.await();
            }
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            number=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
