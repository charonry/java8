package com.charon.juc;

import com.charon.entity.Number;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：8锁
 * @date : 16:35 2019/12/8
 */
public class ThreadMonitorTest {
    /**
     * ①正常输出 one two
     * ②在getOne()中增加Thrad.sleep  one ,two
     * ③新增普通方法getThree   three,one,two
     * ④两个同步方法 两个Number对象  two,one
     * ⑤修改getOne是静态同步方法   two,one
     * ⑥修改两个都为静态方法  one，two
     * ⑦一个静态同步方法，另一个非静态，两个Number  two one
     * ⑧两个静态同步方法，两个number one two
     * @param args
     */
    public static void main(String[] args) {
        Number number=new Number();
        Number num=new Number();
        new Thread(() -> {
            number.getOne();
        }).start();

        new Thread(() -> {
            number.getTwo();
        }).start();

        new Thread(() -> {
            number.getThree();
        }).start();
    }
}
