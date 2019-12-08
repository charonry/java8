package com.charon.juc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：模拟cas算法
 * @date : 12:29 2019/12/8
 */
public class CompareAndSwapTest {

    @Test
    public void  test(){
        final CompareAndSwap cas=new CompareAndSwap();
        for(int i=0;i<20;i++){
            new Thread(()->{
                int expecteValue=cas.get();
                boolean b = cas.compareAndSet(expecteValue, (int) (Math.random() * 100));
                System.out.println(b);
            }).start();
        }
    }
}
