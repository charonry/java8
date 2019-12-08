package com.charon.juc;

import java.util.concurrent.CountDownLatch;


/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 14:11 2019/12/8
 */
public class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 完成一次,闭锁减一
                latch.countDown();
            }
        }
    }
}
