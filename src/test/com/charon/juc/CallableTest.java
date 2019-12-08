package com.charon.juc;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 14:27 2019/12/8
 */
public class CallableTest {

    @Test
    public void test(){
        ThreadDemo threadDemo=new ThreadDemo();

        //实现Callable需要FutureTask实现类的支持接受运算结果
        FutureTask<Integer> task=new FutureTask<>(threadDemo);

        new Thread(task).start();

        // 接受返回的结果
        try {
            // FutureTask也可以用于闭锁
            Integer sum=task.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
