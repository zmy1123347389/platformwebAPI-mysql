package com.behere.common.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.joda.time.DateTime;

/**
 * 获取当前日期和时间的工具类
 * 
 * @author nanbo
 * @version V1.0.0-RELEASE 日期：2016-4-20
 * @since 1.0.0-RELEASE
 */
public class JodaTimeUtil {
	/**
	 * 获取当前日期和时间。<br/>
	 * 详细描述：获取当前日期和时间，返回String类型,输出格式：yyyy-MM-dd HH:mm:ss。<br/>
	 * 使用方式：
	 * 
	 * @return String 输出格式：yyyy-MM-dd HH:mm:ss的字符串。
	 */
	public static String getDateTime(String dataFormat) {
		return DateTime.now().toString(dataFormat);
	}

	/**
	 * 获取当前日期和时间<br/>
	 * 详细描述：获取当前日期和时间，返回Timestamp类型，输出格式：yyyy-MM-dd HH:mi:ss.ff。<br/>
	 * 使用方式：
	 * 
	 * @return Timestamp 输出格式：yyyy-MM-dd HH:mi:ss.ff的形式。
	 */
	public static Timestamp getTimestamp() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	/**
	 * 获取当前日期。<br/>
	 * 详细描述：获取当前日期，返回String类型，输出格式：yyyy-MM-dd。<br/>
	 * 使用方式：
	 * 
	 * @return String 输出格式：yyyy-MM-dd的字符串。
	 */
	public static String getDate() {
		return LocalDate.now().toString();
	}

	/**
	 * 获取当前日期。<br/>
	 * 详细描述：获取当前日志，返回Date类型，输出格式：yyyy-MM-dd。<br/>
	 * 使用方式：
	 * 
	 * @return Date 输出格式为：yyyy-MM-dd。
	 */
	public static Date getSqlDate() {
		return new java.sql.Date(new java.util.Date().getTime());
	}

	/**
	 * 获取当前时间<br/>
	 * 详细描述：获取当前时间，返回String类型，输出格式：HH:mm:ss。<br/>
	 * 使用方式：
	 * 
	 * @param String 输出格式为HH:mm:ss的字符串。
	 */
	public static String getTime() {
		return LocalTime.now().toString();
	}

	/**
	 * 返回前days天数的日期
	 * 
	 * @param days       前多少天
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusDays(Integer days, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.minusDays(days).toString(dataFormat);
	}

	/**
	 * 返回前指定日期days天数的日期
	 * 
	 * @param days       前多少天
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusDaysByDate(Integer days, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.minusDays(days).toString(dataFormat);
	}

	/**
	 * 返回后days天数的日期
	 * 
	 * @param days       后多少天
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusDays(Integer days, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.plusDays(days).toString(dataFormat);
	}

	/**
	 * 返回后days天数的日期
	 * 
	 * @param days       后多少天
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusDaysByDate(Integer days, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.plusDays(days).toString(dataFormat);
	}

	/**
	 * 返回前months月数的日期
	 * 
	 * @param months     前多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusMonths(Integer months, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.minusMonths(months).toString(dataFormat);
	}

	/**
	 * 返回指定日期前months月数的日期
	 * 
	 * @param months     前多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusMonthsByDate(Integer months, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.minusMonths(months).toString(dataFormat);
	}

	/**
	 * 返回后months月数的日期
	 * 
	 * @param months     后多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusMonths(Integer months, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.plusMonths(months).toString(dataFormat);
	}

	/**
	 * 返回指定日期后months月数的日期
	 * 
	 * @param months     后多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusMonthsByDate(Integer months, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.plusMonths(months).toString(dataFormat);
	}

	/**
	 * 返回后months月数的最后一天日期
	 * 
	 * @param months     后多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusMonthsEndDay(Integer months, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.plusMonths(months).dayOfMonth().withMaximumValue().toString(dataFormat);
	}

	/**
	 * 返回指定日期后months月数的最后一天日期
	 * 
	 * @param months     后多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusMonthsEndDayByDate(Integer months, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.plusMonths(months).dayOfMonth().withMaximumValue().toString(dataFormat);
	}

	/**
	 * 返回前months月数的最后一天日期
	 * 
	 * @param months     前多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusMonthsEndDay(Integer months, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.minusMonths(months).dayOfMonth().withMaximumValue().toString(dataFormat);
	}

	/**
	 * 返回指定日期前months月数的最后一天日期
	 * 
	 * @param months     前多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusMonthsEndDayByDate(Integer months, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.minusMonths(months).dayOfMonth().withMaximumValue().toString(dataFormat);
	}

	/**
	 * 返回后months月数的第一天日期
	 * 
	 * @param months     后多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusMonthsBeginDay(Integer months, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.plusMonths(months).dayOfMonth().withMinimumValue().toString(dataFormat);
	}

	/**
	 * 返回指定日期后months月数的第一天日期
	 * 
	 * @param months     后多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getPlusMonthsBeginDayByDate(Integer months, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.plusMonths(months).dayOfMonth().withMinimumValue().toString(dataFormat);
	}

	/**
	 * 返回前months月数的第一天日期
	 * 
	 * @param months     前多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusMonthsBeginDay(Integer months, String dataFormat) {
		DateTime dt = new DateTime();
		return dt.minusMonths(months).dayOfMonth().withMinimumValue().toString(dataFormat);
	}

	/**
	 * 返回指定日期前months月数的第一天日期
	 * 
	 * @param date       指定日期
	 * @param months     前多少月数
	 * @param dataFormat 日期格式
	 * @return
	 */
	public static String getMinusMonthsBeginDayByDate(Integer months, String date, String dataFormat) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormat);
		DateTime dt = formatter.parseDateTime(date);
		return dt.minusMonths(months).dayOfMonth().withMinimumValue().toString(dataFormat);
	}

	/**
	 * 获取指定日期的年
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
		DateTime dt = formatter.parseDateTime(date);
		return dt.toString("yyyy");
	}

	/**
	 * 获取指定日期的月
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(String date) {
		org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
		DateTime dt = formatter.parseDateTime(date);
		return dt.toString("MM");
	}

	/**
	 * 获取指定日
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
		DateTime dt = formatter.parseDateTime(date);
		return dt.toString("dd");
	}
}