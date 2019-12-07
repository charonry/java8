package com.charon.time;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：对于原先时间线程安全加锁解决
 * @date : 23:55 2019/12/7
 */
public class DateFormatThreadLocal {
    public static final ThreadLocal<DateFormat> df= new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String string) throws ParseException {
        return df.get().parse(string);
    }
}
