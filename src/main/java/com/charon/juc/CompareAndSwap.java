package com.charon.juc;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 12:30 2019/12/8
 */
public class CompareAndSwap {

    private int value;
    // 获取内存值
    public synchronized int get(){
        return  value;
    }

    // 比较
    public  synchronized int compareAndSwap(int expecteValue,int newValue){
        int oldValue=value;
        if(expecteValue==oldValue){
            this.value=newValue;
        }
        return  oldValue;
    }

    // 比较
    public synchronized boolean compareAndSet(int expecteValue,int newValue){
        return expecteValue==compareAndSwap(expecteValue, newValue);
    }
}
