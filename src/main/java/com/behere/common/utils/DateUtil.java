package com.behere.common.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
public class DateUtil {
    private static final SimpleDateFormat dateFormatYYMMDD = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat dateFormatYY_MM_DD = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat dateFormatYYMMDDHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormatmmss = new SimpleDateFormat("mm:ss");
    private static final SimpleDateFormat dateFormatHHmmss = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dateFormatDDHHmmss = new SimpleDateFormat("dd HH:mm:ss");
    private static final SimpleDateFormat dateFormatMMDDHHmmss = new SimpleDateFormat("MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormatYYYYMMDDHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * 获取时间字符串（yyyyMMdd）
     * @param date
     * @return
     */
    public static String getTime() {
    	 Date date = new Date();
         long ts = date.getTime();
         return String.valueOf(ts);
    }
    
    public static void main(String[] args) {
		System.out.println(getTime());
	}
    
    /**
     * 获取时间字符串（yyyyMMdd）
     * @param date
     * @return
     */
    public static String formatDateYYMMDD(Date date) {
        return dateFormatYYMMDD.format(date);
    }
    
    /**
     * 获取时间字符串（yyyy-MM-dd HH:mm:ss）
     * @param date
     * @return
     */
    public static String formatDateYYMMDDHHmmss(Date date) {
        return dateFormatYYMMDDHHmmss.format(date);
    }
    
    /**
     * 获取时间字符串（yyyy/MM/dd）
     * @param date
     * @return
     */
    public static String formatDateYY_MM_DD(Date date) {
        return dateFormatYY_MM_DD.format(date);
    }
    
    /**
     * 获取时间字符串（yyyyMMddHHmmss）
     * @param date
     * @return
     */
    public static String formatDateYYYYMMDDHHmmss(Date date) {
        return dateFormatYYYYMMDDHHmmss.format(date);
    }
    
    /**
     * 字符串转date
     * @param datetime yyyyMMdd
     * @return
     */
    public static Date convertStrToDate(String datetime) {
        try {
            return dateFormatYYMMDD.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转date
     * @param datetime yyyy/MM/dd
     * @return
     */
    public static Date convertStrToDate_YY_MM_DD(String datetime) {
        try {
            return dateFormatYY_MM_DD.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转date
     * @param datetime YY-MM-DD HH:mm:ss
     * @return
     */
    public static Date convertStrToDate_YYMMDDHHmmss(String datetime) {
        try {
            return dateFormatYYMMDDHHmmss.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转date
     * @param datetime mm:ss
     * @return
     */
    public static Date convertStrToDate_mmss(String datetime) {
        try {
            return dateFormatmmss.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转date
     * @param datetime HH:mm:ss
     * @return
     */
    public static Date convertStrToDate_HHmmss(String datetime) {
        try {
            return dateFormatHHmmss.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转date
     * @param datetime DD HH:mm:ss
     * @return
     */
    public static Date convertStrToDate_DDHHmmss(String datetime) {
        try {
            return dateFormatDDHHmmss.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转date
     * @param datetime MM-DD HH:mm:ss
     * @return
     */
    public static Date convertStrToDate_MMDDHHmmss(String datetime) {
        try {
            return dateFormatMMDDHHmmss.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转date
     * @param datetime yyyyMMdd
     * @return
     */
    public static Date convertStrToDate(String formatStr,String datetime) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取时间字符串
     * @param date
     * @return
     */
    public static String formatDateStr(String formatStr,Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }
    
    /**
     * 给日期增加天数
     * 
     * @param date
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }
    
    
    /**
     * 给日期增加小时
     * 
     * @param date
     * @return
     */
    public static Date addHours(Date date, int hours) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);// 把日期往后增加一小时.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 给日期增加分钟
     * 
     * @param date
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 给日期增加分钟
     * 
     * @param date
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 给日期增加分钟
     * 
     * @param date
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 根据数据日期获取日期字符串
     * @param date
     * @param dateType
     * @return
     */
    public static String getDateStr(Date date,String dateType){
        StringBuffer dateStr = new StringBuffer();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        if("year".equals(dateType)){
            int year = calendar.get(Calendar.YEAR);
            dateStr.append(year);
        }else if("month".equals(dateType)){
            dateStr.append("-");
            int month = calendar.get(Calendar.MONTH);
            dateStr.append(month);
        }else if("day".equals(dateType)){
            dateStr.append("-");
            int day = calendar.get(Calendar.DATE);
            dateStr.append(day);
        }else if("hour".equals(dateType)){
            dateStr.append(" ");
            int hour = calendar.get(Calendar.HOUR);
            dateStr.append(hour);
        }else if("minute".equals(dateType)){
            dateStr.append(":");
            int minute = calendar.get(Calendar.MINUTE);
            dateStr.append(minute);
        }
        return dateStr.toString();
    }
    
    /**
     * 日期格式字符串集合
     * @return
     */
    public static List<String> dateFormatList() {
        List<String> dateFormatList = new ArrayList<String>();
        dateFormatList.add("yyyy/MM/dd");
        dateFormatList.add("yy/MM/dd");
        dateFormatList.add("yy-MM-dd");
        dateFormatList.add("yyyyMMdd");
        dateFormatList.add("yyyy-MM-dd");
        dateFormatList.add("MM/dd/yyyy");
        dateFormatList.add("MM-dd-yyyy");
        dateFormatList.add("MM/dd/yy");
        dateFormatList.add("MM-dd-yyyy");
        dateFormatList.add("dd/MM/yyyy");
        dateFormatList.add("dd-MM-yyyy");
        return dateFormatList;
    }
    
    /**
     * 月格式字符串集合
     * @return
     */
    public static List<String> monthFormatList() {
        List<String> monthFormatList = new ArrayList<String>();
        monthFormatList.add("yy/MM");
        monthFormatList.add("yyyy/MM");
        monthFormatList.add("yy-MM");
        monthFormatList.add("yyyyMM");
        monthFormatList.add("yyyy-MM");
        monthFormatList.add("yyyy/MM");
        monthFormatList.add("MM/yyyy");
        monthFormatList.add("MM-yyyy");
        monthFormatList.add("MM/yy");
        return monthFormatList;
    }
    
    /**
     * 年格式字符串集合
     * @return
     */
    public static List<String> yearFormatList() {
        List<String> yearFormatList = new ArrayList<String>();
        yearFormatList.add("yyyy");
        yearFormatList.add("yy");
        return yearFormatList;
    }
    
    
    /**
     * 时间格式字符串集合
     * @return
     */
    public static List<String> hourFormatList() {
        List<String> hourFormatList = new ArrayList<String>();
        hourFormatList.add("yyyy/MM/dd HH:mm:ss");
        hourFormatList.add("yy/MM/dd HH:mm:ss.SSS");
        hourFormatList.add("yy/MM/dd HH:mm:ss.SSS XXX");
        hourFormatList.add("yy/MM/dd HH:mm:ss");
        hourFormatList.add("yy/MM/dd HH:mm:ss.XXX");
        hourFormatList.add("yyMMddHHmmss");
        hourFormatList.add("yy-MM-dd HH:mm:ss");
        hourFormatList.add("yy-MM-dd HH:mm:ss XXX");
        hourFormatList.add("MM/dd/yyyy HH:mm:ss");
        hourFormatList.add("MM-dd-yyyy HH:mm:ss");
        hourFormatList.add("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return hourFormatList;
    }
    
    public static String getCurrentYear(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }
    
    public static String getCurrentMonth(){
    	SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date date = new Date();
        return sdf.format(date);
    }
    
    public static String getCurrentDay(){
    	SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date date = new Date();
        return sdf.format(date);
    }
    
    /**
	 * 取得季度第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfSeason(Date date) {
		return getFirstDateOfMonth(getSeasonDate(date)[0]);
	}
	/**
	 * format date
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}
	public static final String YYYYMMDD = "yyyy-MM-dd";
	/**
	 * format date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		String strDate = null;
		try {
			if (pattern == null) {
				pattern = YYYYMMDD;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			strDate = format.format(date);
		} catch (Exception e) {
		}
		return strDate;
	}
	
	/**
	 * 取得月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}
	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {
		
		int season = 0;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
			case Calendar.JANUARY:
			case Calendar.FEBRUARY:
			case Calendar.MARCH:
				season = 1;
				break;
			case Calendar.APRIL:
			case Calendar.MAY:
			case Calendar.JUNE:
				season = 2;
				break;
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.SEPTEMBER:
				season = 3;
				break;
			case Calendar.OCTOBER:
			case Calendar.NOVEMBER:
			case Calendar.DECEMBER:
				season = 4;
				break;
			default:
				break;
		}
		return season;
	}
	
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	

}