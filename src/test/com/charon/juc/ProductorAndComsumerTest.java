package com.charon.juc;

import com.charon.entity.Clert;
import com.charon.entity.Consumer;
import com.charon.entity.Productor;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：生产者和消费者
 * @date : 15:28 2019/12/8
 */
public class ProductorAndComsumerTest {
    public static void main(String[] args) {
        Clert clert=new Clert();
        Productor productor=new Productor(clert);
        Consumer consumer=new Consumer(clert);
        new Thread(productor,"生产者").start();
        new Thread(consumer,"消费者").start();
        new Thread(productor,"生产者A").start();
        new Thread(consumer,"消费者B").start();
    }
}
