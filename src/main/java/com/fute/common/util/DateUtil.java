package com.fute.common.util;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	public final static String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 日志
	 */
	private static final Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * 格式化日期
	 * 
	 * @param dateStr
	 *            日期格式字符串 '2003-1-1'
	 * @return java.util.Date
	 */

	public static final Date parseDate(String dateStr) {
		if ((dateStr == null) || (dateStr.trim().equals("")))
			return null;
		DateFormat format = DateFormat.getDateInstance(DateFormat.DEFAULT,
				Locale.CHINA);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			log.warn("日期字符串格式错误,按默认值设置");
			return null;
		}
	}

	/**
	 * 
	 * @param days
	 * @return
	 */
	public static String plusMinus(int days) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day - days);
		Date cc = calendar.getTime();
		return form.format(cc);
	}

	/**
	 * 
	 * @param days
	 * @return
	 */
	public static String plusMinus(String currdate, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			if ("".equals(currdate)) {
				date = new Date();
				return "";
			} else {
				date = sdf.parse(currdate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				int day = calendar.get(Calendar.DAY_OF_YEAR);
				calendar.set(Calendar.DAY_OF_YEAR, day - days);
				Date cc = calendar.getTime();
				return sdf.format(cc);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @param days
	 * @return
	 */
	public static int plusMinus(String startdate, String enddate) {

		long quot = 0;

		if ("".equals(startdate) || null == startdate) {
			return 0;
		}
		if ("".equals(enddate) || null == enddate) {
			return 0;
		}
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startd = ft.parse(startdate);
			Date endd = ft.parse(enddate);
			quot = (endd.getTime() - startd.getTime()) / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) quot;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            java.util.Date
	 * @return String '2003-01-01'
	 */
	public static final String formatDate(Date date) {
		if (date == null)
			return "";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            java.util.Date
	 * @return String '2003-01-01'
	 */
	public static final Date formatString(String date) {
		Date da = null;
		try {
			if (date == null || date.equals(""))
				return null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			da = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            java.util.Date
	 * @return String '2003-01-01 15:30:30'
	 */

	public static final String formatLangDate(Date date) {
		if (date == null)
			return "";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		return format.format(date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            java.util.Date
	 * @return Date '2003-01-01 15:30:30'
	 */
	public static final Date formatHHMM(String date) throws Exception {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.CHINA);
		return format.parse(date);
	}

	public static final Date formatHHMMSS(String date) throws Exception {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.parse(date);
	}

	public static void main(String[] args) {
		try {
			System.out.println(formatHHMMSS("20130101000000"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static final Date formatyyyyMM(String date) throws Exception {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyyMM");
		return format.parse(date);
	}

	public static final Date formatyyyy(String date) throws Exception {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyy");
		return format.parse(date);
	}

	public static final Date formatyyyyMMdd(String date) throws Exception {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.parse(date);
	}

	public static final Date formatHM(String date) throws Exception {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		return format.parse(date);
	}

	public static final String formatStringHHMM(Date date) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}

	public static final String formatDateYMD(Date date) {
		if (date == null)
			return "";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static final String formatStringYYHHMM(Date date) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
	}

	public static final int formatIntDate(String date) {
		return Integer.parseInt(date.replace(":", ""));
	}

	/**
	 * 格式化文件名字
	 * 
	 * @param date
	 * @return 20070728145012
	 */
	public static final String formatFileDate(Date date) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	/**
	 * 格式化文件名字
	 * 
	 * @param date
	 * @return 20070728145012
	 */
	public static final String formatHHMM(Date date) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		return format.format(date);
	}

	/**
	 * 格式化文件名字
	 * 
	 * @param date
	 * @return 20070728145012
	 */
	public static final String formatYYYYMMdd(Date date) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	/**
	 * 日期的加减处理
	 * 
	 * @param date
	 *            java.util.Date
	 * @return String '2003-01-01'
	 */

	public static final String pnDay(Date date, int pn) {
		String pndate = "";
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(date);
		gc.add(5, pn);
		pndate = sf.format(gc.getTime());
		return pndate;
	}

	/**
	 * 格式化当前时间 年-月-日
	 */
	public static String formatNow() {
		DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(new Date());
	}

	public static Long getTime(String currtime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(currtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long ltime = date.getTime();
		return ltime;
	}

	public static String MD5_1(String s) {
		try {
			byte[] btInput = s.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取取日时分秒做版本号
	 * 
	 * @return
	 */
	public static String fetchVersion() {
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("ddHHmmss");
		return sdf.format(date);
	}

	// 格式：年－月－日 小时：分钟：秒
	public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

	// 格式：年－月－日 小时：分钟
	public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

	// 格式：年月日 小时分钟秒
	public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

	// 格式：年－月－日
	public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

	// 格式：月－日
	public static final String SHORT_DATE_FORMAT = "MM-dd";

	// 格式：小时：分钟：秒
	public static final String LONG_TIME_FORMAT = "HH:mm:ss";

	// 格式：年-月
	public static final String MONTG_DATE_FORMAT = "yyyy-MM";

	// 年的加减
	public static final int SUB_YEAR = Calendar.YEAR;

	// 月加减
	public static final int SUB_MONTH = Calendar.MONTH;

	// 天的加减
	public static final int SUB_DAY = Calendar.DATE;

	// 小时的加减
	public static final int SUB_HOUR = Calendar.HOUR;

	// 分钟的加减
	public static final int SUB_MINUTE = Calendar.MINUTE;

	// 秒的加减
	public static final int SUB_SECOND = Calendar.SECOND;

	static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六" };

	@SuppressWarnings("unused")
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 把符合日期格式的字符串转换为日期类型
	 */
	public static Date stringtoDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);// 指定日期\时间解析是否不严格
			d = formater.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 把符合日期格式的字符串转换为日期类型
	 */
	public static Date stringtoDate(String dateStr, String format,
			ParsePosition pos) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr, pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 把日期转换为字符串
	 */
	public static String dateToString(Date date, String format) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取当前时间的指定格式
	 */
	public static String getCurrDate(String format) {
		return dateToString(new Date(), format);
	}

	public static String dateSub(int dateKind, String dateStr, int amount) {
		Date date = stringtoDate(dateStr, FORMAT_ONE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(dateKind, amount);
		return dateToString(calendar.getTime(), FORMAT_ONE);
	}

	/**
	 * 两个日期相减
	 * 
	 * @return 相减得到的秒数
	 */
	public static long timeSub(String firstTime, String secTime) {
		long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
		long second = stringtoDate(secTime, FORMAT_ONE).getTime();
		return (second - first) / 1000;
	}

	/**
	 * 获得某月的天数
	 */
	public static int getDaysOfMonth(String year, String month) {
		int days = 0;
		if (month.equals("1") || month.equals("3") || month.equals("5")
				|| month.equals("7") || month.equals("8") || month.equals("10")
				|| month.equals("12")) {
			days = 31;
		} else if (month.equals("4") || month.equals("6") || month.equals("9")
				|| month.equals("11")) {
			days = 30;
		} else {
			if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
					|| Integer.parseInt(year) % 400 == 0) {
				days = 29;
			} else {
				days = 28;
			}
		}

		return days;
	}

	/**
	 * 获取某年某月的天数
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得当前日期
	 */
	public static int getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获得当前月份
	 */
	public static int getToMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得当前年份
	 */
	public static int getToYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回日期的天
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 返回日期的年
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回日期的月份，1-12
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
	 */
	public static long dayDiff(Date date1, Date date2) {
		return (date2.getTime() - date1.getTime()) / 86400000;
	}

	/**
	 * 比较两个日期的年差
	 */
	public static int yearDiff(String before, String after) {
		Date beforeDay = stringtoDate(before, LONG_DATE_FORMAT);
		Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
		return getYear(afterDay) - getYear(beforeDay);
	}

	/**
	 * 比较指定日期与当前日期的差
	 */
	public static int yearDiffCurr(String after) {
		Date beforeDay = new Date();
		Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
		return getYear(beforeDay) - getYear(afterDay);
	}

	/**
	 * 获取每月的第一周
	 */
	public static int getFirstWeekdayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
		c.set(year, month - 1, 1);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取每月的最后一周
	 */
	public static int getLastWeekdayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
		c.set(year, month - 1, getDaysOfMonth(year, month));
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return
	 */
	public static String getNow() {
		Calendar today = Calendar.getInstance();
		return dateToString(today.getTime(), FORMAT_ONE);
	}

	/**
	 * 判断日期是否有效,包括闰年的情况
	 * 
	 * @param date
	 *            YYYY-mm-dd
	 * @return
	 */
	public static boolean isDate(String date) {
		StringBuffer reg = new StringBuffer(
				"^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
		reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
		reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
		reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
		reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
		reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
		Pattern p = Pattern.compile(reg.toString());
		return p.matcher(date).matches();
	}

	/**
	 * 根据时间后的礼拜一和礼拜天
	 */

	public static String getMonday(String cdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		// 当前时间，貌似多余，其实是为了所有可能的系统一致
		try {
			cdate = cdate.replaceAll(" ", "-");
			calendar.setTime(DateUtil.formatyyyyMMdd(cdate));
			calendar.add(Calendar.DATE, -1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return sdf.format(calendar.getTime());
	}

	public static String getDay(String cdate, int k) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		// 当前时间，貌似多余，其实是为了所有可能的系统一致
		try {
			cdate = cdate.replaceAll(" ", "-");
			calendar.setTime(DateUtil.formatyyyyMMdd(cdate));
			calendar.add(Calendar.DATE, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdf.format(calendar.getTime());
	}

	/**
	 * 根据时间后的礼拜一和礼拜天
	 */

	public static String getSunday(String cdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		cdate = cdate.replaceAll(" ", "-");
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		// 当前时间，貌似多余，其实是为了所有可能的系统一致
		try {
			calendar.setTime(DateUtil.formatyyyyMMdd(cdate));
			calendar.add(Calendar.DATE, 6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sdf.format(calendar.getTime());
	}

}
