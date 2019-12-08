package com.charon.juc;

import java.util.concurrent.Callable;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 14:27 2019/12/8
 */
public class ThreadDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum =0;
        for(int i=0;i<=100;i++){
            sum+=i;
        }
        return sum;
    }
}
