package com.fute.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtils {
	
    public static String FORMAT_DATE_TIME_DEFAULT="yyyy-MM-dd HH:mm:ss";
	
	public static String FORMAT_DATE_DEFAULT="yyyy-MM-dd";
	
	public static String FORMAT_DATE_TIME_YYYYMMDDHHMMSS="yyyyMMddHHmmss";
	
	public static String FORMAT_DATE_YYYYMMDD="yyyyMMdd";
	
	public static Date parseDate(String text,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			return sdf.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String format(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
	    return sdf.format(date);
	}
	
	/**
	 * 按指定格式把日期类型转换成字符串
	 * @param d 需要转换的日期
	 * @param format 转换的格式
	 */
	public static String getDateByFormat(Date _d, String _format) {
		SimpleDateFormat df = new SimpleDateFormat(_format);
		return df.format(_d);
	}
	
	/**
	 * 按指定格式把字符串转换成日期类型
	 * @param d 需要转换的日期
	 * @param format 转换的格式
	 */
	public static Date getDateByFormat(String _s, String _format){
		SimpleDateFormat df = new SimpleDateFormat(_format);
		Date date = null;
		try{
			if(!"".equals(_s))
				date = df.parse(_s);
		}catch(ParseException pe){
			date = null;
		}
		return date;
	}

	/**
	 * 日期显示成中文 yyyy年MM月dd日[ HH:mm:ss]
	 * @param sdate
	 */
	public static String getCNDate(String _sdate) {
		String s = "";
		if (_sdate == null || _sdate.equals("")) {
			return s;
		}
		_sdate = get8DateByStr(_sdate.trim());

		if (_sdate.length() == 4) {
			s = _sdate.substring(0, 4) + "年";
		} else if (_sdate.length() == 6) {
			s = _sdate.substring(0, 4) + "年" + _sdate.substring(4, 6) + "月";
		} else if (_sdate.length() == 8) {
			s = _sdate.substring(0, 4) + "年" + _sdate.substring(4, 6) + "月" + _sdate.substring(6, 8) + "日";
		} else if (_sdate.length() == 12) {
			s = _sdate.substring(0, 4) + "年" + _sdate.substring(4, 6) + "月" + _sdate.substring(6, 8) + "日 "
				+ _sdate.substring(8, 10) + ":" + _sdate.substring(10, 12);
		} else if (_sdate.length() >= 14) {
			s = _sdate.substring(0, 4) + "年" + _sdate.substring(4, 6) + "月" + _sdate.substring(6, 8) + "日 " 
				+ _sdate.substring(8, 10) + ":" + _sdate.substring(10, 12) + ":" + _sdate.substring(12, 14);
		} else {
			s = _sdate;
		}

		return s;
	}
	
	/**
	 * 当前时间yyyy-MM-dd
	 * @return
	 */
	public static String getDateNow() {
		Date date = new Date();
		return getDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 根据输入日期返回 加/减n后的日期 
	 * @param s String 输入的日期 (yyyy-MM-dd或yyyyMMdd)
	 * @param n int 要加、减的天数
	 * @return String 结果日期 (yyyy-MM-dd)
	 */
	public static String getTheDate(String _s, int _n) {
		Date dt = null;
		if (_s == null || _s.trim().equals("")) {
			dt = new Date();
		} else {
			_s = get8DateByStr(_s);
			try {
				dt = new SimpleDateFormat("yyyyMMdd").parse(_s);
			} catch (Exception e) {
				//Log.writeLog("DateUtils:getTheDate" + e.toString());
			}
		}

		Calendar cd = new GregorianCalendar();
		cd.setTime(dt);
		cd.add(GregorianCalendar.DATE, _n);
		String dst = getDateByFormat(cd.getTime(), "yyyy-MM-dd");

		return dst;
	}
	
	/**
	 * 返回8位日期yyyyMMdd
	 */
	public static String get8DateByStr(String _date) {
		_date = _date.replaceAll("-", "");
		_date = _date.replaceAll("年", "");
		_date = _date.replaceAll("月", "");
		_date = _date.replaceAll("日", "");
		return _date;
	}
	
	/**
	 * 返回10位日期yyyy-MM-dd
	 */
	public static String get10DateByStr(String _date) {
		if (_date == null || _date.length() < 8) {
			return _date;
		}
		_date = get8DateByStr(_date);
		_date = _date.substring(0, 4) + "-" + _date.substring(4, 6) + "-" + _date.substring(6, 8);
		return _date;
	}
	
	/**
	 * 取年初到参数天的天数
	 * date:yyyy-MM-dd或yyyyMMdd
	 */
	public static long getYearDays(String _date) {
		return getDiffDays(_date.substring(0, 4) + "-01-01", _date);
	}
	
	/**
	 * 取某2天间的天数
	 * date:yyyy-MM-dd或yyyyMMdd
	 * 2天相等返回1天
	 */
	public static long getDiffDays(String _date1, String _date2) {
		try {
			if (_date1.indexOf("-") < 0) {
				_date1 = get10DateByStr(_date1);
			}
			if (_date2.indexOf("-") < 0) {
				_date2 = get10DateByStr(_date2);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = new Date();
			d1 = sdf.parse(_date1);
			Date d2 = new Date();
			d2 = sdf.parse(_date2);
			long daytime = 24 * 60 * 60 * 1000; //一天的毫秒数
			return Math.abs((d2.getTime() - d1.getTime()) / daytime) + 1;
		} catch (Exception e) {
			//Log.writeLog("DateUtils.getDays", e);
			return 0;
		}
	}
	
	
	

	
	/**
	 * 取某年某月的天数
	 * date:yyyy-MM或yyyyMM
	 */
	@SuppressWarnings("deprecation")
	public static int getMonthDays(String _date) {
		/*GregorianCalendar(year, month, dayofmonth)
		 * 这里的month从0开始计数，所以假如要取4月的天数只要传year和4即可
		 */
		try {
			if (_date.indexOf("-") < 0) {
				_date = _date.substring(0, 4) + "-" + _date.substring(4);
			}
			GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(_date.substring(0, 4)), Integer.parseInt(_date.substring(5, 7)), 1);
			gc.add(5, -1);
			return gc.getTime().getDate();
		} catch (Exception e) {
			//Log.writeLog("DateUtils.getMonthDays", e);
			return 0;
		}		
	}
	
	/**
	 * 取参数月的前一个月
	 * month: yyyy-MM或yyyyMM
	 */
	public static String getPreMonth(String _month) {
		String preMonth = "";
		if (_month.length() == 6) {
			_month = _month.substring(0, 4) + "-" + _month.substring(4);
		}
		if (_month.length() >= 7) {
			if (_month.substring(5, 7).equals("01")) { //1月
				preMonth = (Integer.parseInt(_month.substring(0, 4))-1) + "-12";
			} else {
				int im = Integer.parseInt(_month.substring(5, 7))-1;
				if (im < 10) {
					preMonth = _month.substring(0, 4) + "-0" + im;
				} else {
					preMonth = _month.substring(0, 4) + "-" + im;
				}
			}
		}
		
		return preMonth;
	}
	
	/**
	 * 取参数月的下一个月
	 * month: yyyy-MM或yyyyMM
	 */
	public static String getNextMonth(String _month) {
		String nextMonth = "";
		if (_month.length() == 6) {
			_month = _month.substring(0, 4) + "-" + _month.substring(4);
		}
		if (_month.length() >= 7) {
			if (_month.substring(5, 7).equals("12")) { //12月
				nextMonth = (Integer.parseInt(_month.substring(0, 4))+1) + "-01";
			} else {
				int im = Integer.parseInt(_month.substring(5, 7))+1;
				if (im < 10) {
					nextMonth = _month.substring(0, 4) + "-0" + im;
				} else {
					nextMonth = _month.substring(0, 4) + "-" + im;
				}
			}
		}
		
		return nextMonth;
	}
	
	/**
	 * 取2个月间的月差(2月相等返回1)
	 * month: yyyy-MM
	 */
	public static int getDiffMonths(String _month1, String _month2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = sdf.parse(_month1 + "-01");
			Date d2 = sdf.parse(_month2 + "-01");
			int months = 0;
			GregorianCalendar gc1 = new GregorianCalendar(); //前日期
			GregorianCalendar gc2 = new GregorianCalendar(); //后日期
			if (d1.before(d2)) {
				gc1.setTime(d1);
				gc2.setTime(d2);
			} else {
				gc1.setTime(d2);
				gc2.setTime(d1);
			}
			
			while (gc1.before(gc2)) {
				gc1.add(GregorianCalendar.MONTH, 1);
				months++;
			}
			
			months++;
			
			return months;
		} catch (Exception e) {
			//Log.writeLog("DateUtils.getMonths", e);
			return 0;
		}
	}
	
	
	/**
	 * 取得参数日期的上季度最后一个月
	 * @param _date
	 * 			格式:yyyy-MM-dd
	 * @return
	 * 			格式:yyyy-MM
	 * @author Noah Rong 
	 */
	public static String getPreSeason(String _date){
		String preSeason = "";
		int currYear = 0;
		int currMonth = 0;
		
		if(_date.equals("") || _date==null)
			return "";
		currYear = Integer.parseInt(_date.substring(0, 4));
		currMonth = Integer.parseInt(_date.substring(5, 7));
		
		switch(currMonth){
		case 1:
		case 2:
		case 3:
			preSeason = (currYear-1) + "-12"; 
			break;
		case 4:
		case 5:
		case 6:
			preSeason = currYear + "-03"; 
			break;
		case 7:
		case 8:
		case 9:
			preSeason = currYear + "-06"; 
			break;
		case 10:
		case 11:
		case 12:
			preSeason = currYear + "-09"; 
			break;
		default:
			preSeason = currYear + "-" + currMonth + "";
		}
		
		return preSeason;
	}
	
	/**
	 * 取得系统时间
	 * @param _date
	 * 			格式:yyyyMMddmmss
	 * @return
	 * @author slj 
	 */
	public static String getTimeNow() {
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpledateformat.format(date);
	}
	
	/**
	 * 判断年月是否正确
	 * @param _ny	yyyyMM或yyyy-MM
	 * @return
	 * @throws Exception
	 */
	public static boolean isNY(String _ny) throws Exception {
		_ny = get8DateByStr(_ny);
		if (_ny.length() != 6)	{
			return false;
		}
		try {
			Integer.parseInt(_ny);
		} catch (Exception e) {
			return false;
		}
		if (_ny.substring(4, 6).compareTo("01") < 0 || _ny.substring(4, 6).compareTo("12") > 0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 取某2天间的天数
	 * date:yyyy-MM-dd或yyyyMMdd
	 * 2天相等返回1天
	 */
	public static long getDiffDays(Date d1, Date d2) {
		try {
			long daytime = 24 * 60 * 60 * 1000; //一天的毫秒数
			return Math.abs((d2.getTime() - d1.getTime()) / daytime) + 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	/**
	 * 取时间，1小时内取几分钟前，24小时内取几小时前，超过一天显示天
	 * date:yyyy-MM-dd或yyyyMMdd
	 * 2天相等返回1天
	 */
	public static String getMillisecondNotes(Date d1, Date d2) {
		try {
			//long daytime = 24 * 60 * 60 * 1000; //一天的毫秒数
			long ss = Math.abs((d2.getTime() - d1.getTime()));
			if(ss <= (60 * 60 * 1000)){
				return ss/1000/60 + "分钟前";
			}else if(ss <= (60 * 60 * 1000 * 24)){
				return ss/1000/60/60 + "小时前";
			}else{
				return getDateByFormat(d1, "yyyy-M-d");
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 根据条件获取跟当天相比较的前或者后几天的具体时间
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getPreOrAfterDateByCondition(int day,int hour,int minute,int second){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}
	
	
	public static void main(String[] args) {

		int yy = Integer.parseInt("20110614".substring(0, 4));
		int mm = Integer.parseInt("20110614".substring(4, 6))-1;
		int dd = Integer.parseInt("20110614".substring(6));
		System.out.println(yy + " " + mm  + " " + dd);
		GregorianCalendar gc = new GregorianCalendar(yy,mm,dd);
		gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
		System.out.println(gc.getTime());
	}
}