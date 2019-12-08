package com.charon.entity;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 15:25 2019/12/8
 */
public class Consumer implements Runnable {

    private Clert clert;

    public Consumer(Clert clert) {
        this.clert = clert;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            clert.sale();
        }
    }
}
