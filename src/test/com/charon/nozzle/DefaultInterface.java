package com.charon.nozzle;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：默认方法和静态方法
 * @date : 23:26 2019/12/7
 */
public class DefaultInterface {
    public static void main(String[] args) {
        SubClass subClass=new SubClass();
        System.out.println(subClass.getName());

        MyFun.show();
    }
}
