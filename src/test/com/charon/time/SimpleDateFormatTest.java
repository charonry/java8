package com.charon.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：原先时间多线程安全问题
 * @date : 23:44 2019/12/7
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 存在线程安全问题
        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task=() ->sdf.parse("20191207");

        ExecutorService pool= Executors.newFixedThreadPool(10);

        List<Future<Date>> list=new ArrayList<>();

        for(int i=0;i<10;i++){
            list.add(pool.submit(task));
        }

        for (Future future:list){
            System.out.println(future.get());
        }*/

        // 原先解决存在线程安全问题
        /*Callable<Date> task=() ->DateFormatThreadLocal.convert("20191207");

        ExecutorService pool= Executors.newFixedThreadPool(10);

        List<Future<Date>> list=new ArrayList<>();

        for(int i=0;i<10;i++){
            list.add(pool.submit(task));
        }

        for (Future future:list){
            System.out.println(future.get());
        }

        pool.shutdown();*/

        // 新时间解决存在线程安全问题
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task=()->LocalDate.parse("20191208",dtf);

        ExecutorService pool= Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> list=new ArrayList<>();

        for(int i=0;i<10;i++){
            list.add(pool.submit(task));
        }

        for (Future future:list){
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
