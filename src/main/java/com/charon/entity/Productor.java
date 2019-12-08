package com.charon.entity;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 15:24 2019/12/8
 */
public class Productor implements Runnable{
    private Clert clert;

    public Productor(Clert clert) {
        this.clert = clert;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clert.get();
        }
    }
}
