package com.charon.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 17:32 2019/12/8
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService pool= Executors.newScheduledThreadPool(5);

        for (int i=0;i<5;i++){
            Future<Integer> result = pool.schedule(() -> {
                int num = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + ":" + num);
                return num;
            }, 3L, TimeUnit.SECONDS);

            System.out.println(result.get());
        }

        pool.shutdown();
    }
}
