package com.charon.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：线程池
 * @date : 16:56 2019/12/8
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService pool= Executors.newFixedThreadPool(5);
        List<Future<Integer>> list =new ArrayList<>();
        for(int i=0;i<=10;i++){
            Future<Integer> future = pool.submit(() -> {
                int sum = 0;
                for (int j = 0; j <= 100; j++) {
                    sum += j;
                }
                return sum;
            });
            list.add(future);
        }

        pool.shutdown();

        for(Future future:list){
            System.out.println(future.get());
        }
       /*
       ThreadPoolDemo threadPoolDemo=new ThreadPoolDemo();
       // 线程池分配任务
        for(int i=0;i<10;i++){
            pool.submit(threadPoolDemo);
        }

        // 关闭线程池
        pool.shutdown();*/

    }
}
