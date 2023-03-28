package com.oik.util.time;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 15093
 * @description TODO
 * @date 2023/3/23 14:07
 */
public class TimeUtil {

    /**
     * 获取明天日期
     *
     * @return
     */
    public static Date getTomorrow(Date date) {
        Calendar calendar = Calendar.getInstance();//new一个Calendar类,把Date放进去
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取昨天日期
     *
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();//new一个Calendar类,把Date放进去
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 计算几天前或者几天后的日期
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getAddDay(Date date, int num) {
        Calendar calendar = Calendar.getInstance();//new一个Calendar类,把Date放进去
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    /**
     * 获取某天开始时间
     *
     * @return
     */
    private static Date getStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取某天结束时间
     *
     * @return
     */
    private static Date getEndTime(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取某年某月开始时间
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取某年某月结束时间
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取指定月份天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.getActualMaximum(Calendar.DATE);
    }

}
