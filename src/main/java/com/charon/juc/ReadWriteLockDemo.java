package com.charon.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 16:27 2019/12/8
 */
public class ReadWriteLockDemo {
    private int num=0;

    private ReadWriteLock readWriteLock =new ReentrantReadWriteLock();

    public void get(){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+":"+num);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void set(int num){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
            this.num=num;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
