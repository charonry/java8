package com.charon.juc;

import org.junit.Test;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：同步锁
 * @date : 14:38 2019/12/8
 */
public class LockTest {

    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(ticket,"一号").start();
        new Thread(ticket,"二号").start();
        new Thread(ticket,"三号").start();
    }
}
