package com.charon.stream;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 21:59 2019/12/6
 */
public class ForkJoinCaculate extends RecursiveTask<Long> {
    private static final long serialVersionUID = 3699726560027687027L;

    private long start;
    private long end;
    private static final long THRESHOLD=10000;

    public ForkJoinCaculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length=end-start;
        if(length<THRESHOLD){
            long sum=0;
            for(long i = start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else {
            long middle=(start+end)/2;
            ForkJoinCaculate left=new ForkJoinCaculate(start,middle);
            left.fork();
            ForkJoinCaculate right=new ForkJoinCaculate(middle+1,end);
            right.fork();
            return  left.join()+right.join();
        }
    }


}
