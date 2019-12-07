package com.charon.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：新时间的学习
 * @date : 0:12 2019/12/8
 */
public class LocalDateTimeTest {

    // 1.LocalDate LocalTime localDateTime
    @Test
    public void test(){
        LocalDateTime ldt= LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime myLdt = LocalDateTime.of(2019, 12, 07, 12, 23, 34);
        System.out.println(myLdt);
        // 加两年  减2年ldt.minusYears(2);
        LocalDateTime ldtYear = ldt.plusYears(2);
        System.out.println(ldtYear);
    }

    //2.Instance:时间戳（以Unix元年:19700101010000000-时间之间的毫秒值）
    @Test
    public void test2(){
        // 默认是UTC时区
        Instant instant = Instant.now();
        System.out.println(instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // 时间戳
        System.out.println(instant.toEpochMilli());

        // 从开始时间的时间戳偏移
        Instant inst = Instant.ofEpochSecond(1);
        System.out.println(inst);
    }

    // 3.Duration 时间之间的间隔 Period:日期之间间隔
    @Test
    public void test3(){
        LocalDateTime start = LocalDateTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());

        LocalDate startDate = LocalDate.of(2019, 11, 04);
        LocalDate endDate = LocalDate.now();
        Period period = Period.between(startDate, endDate);
        System.out.println(period.getMonths());
    }

    //TemporalAdjuster 时间校正器
    @Test
    public void test4(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime time = localDateTime.withDayOfMonth(16);
        System.out.println(time);
        LocalDateTime dateTime = localDateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(dateTime);

        LocalDateTime workDay = localDateTime.with((l) -> {
            LocalDateTime ldt = (LocalDateTime) l;
            DayOfWeek dayOfWeek = ldt.getDayOfWeek();
            if (DayOfWeek.FRIDAY.equals(dayOfWeek)) {
                return ldt.plusDays(3);
            } else {
                return ldt.plusDays(6);
            }
        });

        System.out.println(workDay);
    }

    // DateTimeFormatter:格式化时间
    @Test
    public void test5(){
        DateTimeFormatter dft=DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt=LocalDateTime.now();
        String strDate = ldt.format(dft);
        System.out.println(strDate);

        System.out.println("-----------------------------");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dateTimeFormatter.format(ldt);
        System.out.println(strDate2);

        System.out.println("-----------------------------");

        LocalDateTime dateTime = ldt.parse(strDate2, dateTimeFormatter);
        System.out.println(dateTime);
    }

    // 时区处理：Asia/Shanghai
    @Test
    public void test6(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for(String string:availableZoneIds){
            if(string.contains("Shanghai")){
                System.out.println(string);
            }
        }
    }

    @Test
    public void test7(){
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(localDateTime);
    }
}
