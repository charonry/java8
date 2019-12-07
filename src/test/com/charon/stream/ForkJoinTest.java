package com.charon.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 22:16 2019/12/6
 */
public class ForkJoinTest {
    @Test
    public void test(){
        Instant start = Instant.now();

        ForkJoinPool pool=new ForkJoinPool();
        ForkJoinCaculate forkJoinCaculate=new ForkJoinCaculate(0,100000000L);
        Long sum = pool.invoke(forkJoinCaculate);
        System.out.println(sum);
        Instant end = Instant.now();

        System.out.println("消费时间:"+ Duration.between(start,end).toMillis());
    }

    @Test
    public void testCompare(){
        Instant start = Instant.now();

        Long sum=0L;
        for(long i=0;i<100000000L;i++){
            sum+=i;
        }
        System.out.println(sum);
        Instant end = Instant.now();

        System.out.println("消费时间:"+ Duration.between(start,end).toMillis());
    }

    // java8并行流
    @Test
    public void test3(){
        Instant start = Instant.now();
        LongStream.rangeClosed(0,100000000L).parallel().reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("消费时间:"+ Duration.between(start,end).toMillis());
    }
}
