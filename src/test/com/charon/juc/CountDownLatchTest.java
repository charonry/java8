package com.charon.juc;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：闭锁
 * @date : 13:11 2019/12/8
 */
public class CountDownLatchTest {

    @Test
    public void test(){
        final CountDownLatch latch=new CountDownLatch(5);
        LatchDemo latchDemo=new LatchDemo(latch);

        long start=System.currentTimeMillis();

        for(int i=0;i<5;i++){
            new Thread(latchDemo).start();
        }

        // 等待
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end=System.currentTimeMillis();

        System.out.println("耗费时间"+(end=start));
    }
}
