package com.zn.jdk8;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by zhoun on 2018/3/2.
 **/
public class NewFeturesTest6 {

    /**
     * java8在java.time包内引入了新的api
     * local:简化的date/time api,没有时区处理的特点
     * zoned:定制的date/time api,用于处理多时区的情况
     *
     * @param args
     */
    public static void main(String[] args) {
        NewFeturesTest6 feturesTest6 = new NewFeturesTest6();
        //feturesTest6.testLocalDateTime();
        feturesTest6.testZoneDateTime();
    }

    public void testLocalDateTime() {
        //获得当前的日期和时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("current date and time: " + currentTime);

        //输出当前时间的本地值
        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("local date: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        //由当前时间对象获得各字段，输出结果
        System.out.println("month:" + month + " day: " + day + " seconds: " + seconds);

        //由当前时间附带月份和年份输出
        LocalDateTime date2 = currentTime.withDayOfMonth(3).withYear(2018);
        System.out.println("date2: " + date2);

        //输出2016年圣诞节的日期
        LocalDate date3 = LocalDate.of(2016, Month.DECEMBER, 25);
        System.out.println("date3: " + date3);

        //输出新闻联播的开始时间
        LocalTime date4 = LocalTime.of(19, 00);
        System.out.println("date4: " + date4);

        //转换为字符串再输出
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

    }


    public void testZoneDateTime() {
        //将字符串代表的时区信息转化
        ZonedDateTime date1 = ZonedDateTime.parse("2016-04-20T19:22:15+01:30[Europe/Paris]");
        System.out.println("date1:" + date1);

        //设定地区为亚洲的加尔各答，并输出
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        System.out.println("zoneId:" + zoneId);

        //获得系统当前的默认地区并输出
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);


    }

    //计时单位的
    public void testChoromUnits() {
        LocalDate today = LocalDate.now();
        System.out.println("current day:" + today);

        //增加两周
        LocalDate nextWeek = today.plus(2, ChronoUnit.WEEKS);
        System.out.println("next week :" + nextWeek);

        //增加6个月
        LocalDate nextmonth = today.plus(6, ChronoUnit.MONTHS);
        System.out.println("next month:" + nextmonth);

        //增加5年
        LocalDate nextYear = today.plus(5, ChronoUnit.YEARS);
        System.out.println("next year:" + nextYear);

        //增加20年
        LocalDate nextDecade = today.plus(2, ChronoUnit.DECADES);
        System.out.println("next decades:" + nextDecade);

    }

    //时间段
    public void showPeriod() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.plus(1, ChronoUnit.MONTHS);
        Period period = Period.between(date2, date1);
        System.out.println("period:" + period);
    }

    public void showDuration() {
        LocalTime time1 = LocalTime.now();
        Duration fiveHours = Duration.ofHours(5);
        LocalTime time2 = time1.plus(fiveHours);

        Duration duration = Duration.between(time1, time2);
        System.out.println("Duration； " + duration);
    }

    public void applyAdjusters(){
        LocalDate date1=LocalDate.now();

        LocalDate date2=date1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        //获得下个月第二个周期的日期并输出
        LocalDate firstInMonth=LocalDate.of(date2.getYear(),date2.getMonth(),1);


    }


}
