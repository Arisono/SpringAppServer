package org.springframework.web.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <p>时间转换工具�?</p>
 * 
 * 
 */
public class DateFormatUtil {
	
	/** 格式：yyyy-MM-dd */
	public static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	/** 格式：yyyyMMdd */
	public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	/** 格式：yyyy/MM/dd */
	public static SimpleDateFormat _yyyy_MM_dd = new SimpleDateFormat("yyyy/MM/dd");
	
	/** 格式：yyyy-MM-dd HH:mm:ss */
	public static SimpleDateFormat yMd_Hms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 格式：yyyy/MM/dd HH:mm:ss */
	public static SimpleDateFormat _yMd_Hms = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/** 格式：yyyyMMddHHmmss */
	public static SimpleDateFormat yMdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/** 格式：yyyyMM */
	public static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
	/** 格式：HH:mm:ss */
	public static SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
	
	
	/**
	 * <p>获取当前系统时间</p>
	 * 通过java.util.Date类获�?
	 * 
	 * @return 返回java.util.Date类型对象
	 * @see #getCurrentDate()
	 */
	public static Date getDate(){
		return new Date();
	}
	
	/**
	 * <p>获取当前系统时间</p>
	 * 通过java.util.Calendar类获�?
	 * 
	 * @return 返回java.util.Date类型对象
	 * @see Calendar
	 */
	public static Date getCalendarDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * <p>获取当前系统日期，返回字符串格式</p>
	 * 格式：yyyy-MM-dd
	 *
	 * @return 返回日期字符串格式：yyyy-MM-dd
	 * @see #getDateStr(Date)
	 */
	public static String getDateStr(){
		return getDateStr(new Date());
	}

	/**
	 * <p>获取格式化字符串日期，返回字符串格式</p>
	 * 格式：yyyy-MM-dd
	 *
	 * @param date 指定日期对象
	 * @return 返回日期字符串格式：yyyy-MM-dd
	 */
	public static String getDateStr(Date date){
		return yyyy_MM_dd.format(date);
	}

	/**
	 * <p><b>[默认]</b> 获取当前系统日期时间，返回字符串格式</p>
	 * 格式：yyyy-MM-dd HH:mm:ss<br>
	 *
	 * @return 返回字符串对象：yyyy-MM-dd HH:mm:ss
	 * @see #getDateTimeStr(Date)
	 */
	public static String getDateTime() {
		return yMd_Hms.format(new Date());
	}

	/**
	 * <p>获取当前系统日期时间，返回字符串格式</p>
	 * 格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @return 返回字符串对象：yyyy-MM-dd HH:mm:ss
	 * @see #getDateTimeStr(Date)
	 */
	public static String getDateTimeStr(){
		return getDateTimeStr(new Date());
	}

	/**
	 * <p>获取格式化字符串日期时间，返回字符串格式</p>
	 * 格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 指定日期对象
	 * @return 返回日期时间字符串格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeStr(Date date){
		return yMd_Hms.format(date);
	}

	/**
	 * <p>获取当前�?</p>
	 *
	 * @return 返回int类型的整�?
	 */
	public static int getCurrentYear(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 *
	 * <p>获取当前�?</p>
	 *
	 * @return 返回int类型的整数，�?位或两位数，范围是：1-12
	 */
	public static int getCurrentMonth(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH)+1;
	}

	/**
	 * <p>获取当前�?</p>
	 *
	 * @return 返回int类型的整数，�?位或两位数，范围是：1-31
	 */
	public static int getCurrentDay(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * <p>获得指定的年，int格式</p>
	 *
	 * @param date 指定日期对象
	 * @return 返回int类型的整�?
	 */
	public static int getCustomYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * <p>获得指定的月，int格式</p>
	 *
	 * @param date 指定日期对象
	 * @return 返回int类型的整数，�?位或两位数，范围是：1-12
	 */
	public static int getCustomMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH)+1;
	}

	/**
	 * <p>获得指定的日，int格式</p>
	 *
	 * @param date 指定日期对象
	 * @return 返回int类型的整数，�?位或两位数，范围是：1-31
	 */
	public static int getCustomDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * <p>获取当前系统时间的小时数</p>
	 * 通过java.util.Calendar获取
	 *
	 * @return 返回小时�?
	 */
	public static int getCurrentHour() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * <p>获取当前系统时间的分钟数</p>
	 * 通过java.util.Calendar获取
	 *
	 * @return 返回分钟�?
	 */
	public static int getCurrentMinute() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * <p>获取当前系统时间的秒�?</p>
	 * 通过java.util.Calendar获取
	 *
	 * @return 返回秒数
	 */
	public static int getCurrentSecond() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * <p>获得指定的小�?(日中�?)，int格式</p>
	 * <br>
	 * @return
	 */
	public static int getCustomHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * <p>获得指定的分钟，int格式</p>
	 * <br>
	 * @return
	 */
	public static int getCustomMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * <p>获得指定的秒，int格式</p>
	 * <br>
	 * @return
	 */
	public static int getCustomSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**********************我是华丽丽的分割�?***************************************************/

	/**
	 * <p>
	 * 获取本月第一天日期（格式如YYYYMMDD�?,如果当前日为当月1�?,则返回上月第�?�?
	 * </p>
	 *
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = 0;
		if (day == 1)// 当月第一�?
		{
			calendar.add(Calendar.MONTH, -1);
		}
		month = calendar.get(Calendar.MONTH);
		if (month < 10) {
			return "" + calendar.get(Calendar.YEAR) + "0" + (month + 1) + "01";
		} else {
			return "" + calendar.get(Calendar.YEAR) + month + "01";
		}
	}

	/**
	 * <p>
	 * 获取当前时间前几天或后几天的日期
	 * </p>
	 *
	 * @return
	 */
	public static Date getAddDays(int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	/**
	 * <p>
	 * 获取某个月后的日期格式（yyyyMMdd�?
	 * </p>
	 *
	 * @return
	 */
	public static String getAfterMonth(int monthNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, monthNum);
		return yyyyMMdd.format(calendar.getTime());
	}

	/**
	 * <p>
	 * 返回日期（格式yyyyMMdd�?
	 * </p>
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String getFormatDate(long timeMillis) {
		return yMdHms.format(new Date(timeMillis));
	}

	/**
	 * 获取当前系统时间距离传入时间的毫秒数
	 *
	 * @param strTime
	 *            格式[ DD:00:00]
	 * @return
	 * @throws ParseException
	 */
	public static long getSleepTime(String strTime) throws ParseException {
		long p = 1;
		long l_date = System.currentTimeMillis();
		Date date_now = new Date(l_date);
		String strDate = yyyy_MM_dd.format(date_now) + strTime;
		if (date_now.before(yMd_Hms.parse(strDate)))
			p = (yMd_Hms.parse(strDate)).getTime() - l_date;
		else {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date_now);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			Date date = calendar.getTime();
			strDate = yyyy_MM_dd.format(date) + strTime;
			p = (yMd_Hms.parse(strDate)).getTime() - l_date;
		}
		return p;
	}

	public static String getPredate() {
		Date nowDate = new Date();
		String nowdates = yyyy_MM_dd.format(nowDate);
		String[] dates = nowdates.split("-");
		int year = Integer.parseInt(dates[0]);
		int month = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(dates[2]) - 1;
		if (day == 0) {
			switch (month - 1) {
			case 1:
				day = 31;
				break;
			case 3:
				day = 31;
				break;
			case 5:
				day = 31;
				break;
			case 7:
				day = 31;
				break;
			case 8:
				day = 31;
				break;
			case 10:
				day = 31;
				break;
			case 0:
				month = 13;
				year = year - 1;
				day = 31;
				break;
			case 4:
				day = 30;
				break;
			case 6:
				day = 30;
				break;
			case 9:
				day = 30;
				break;
			case 11:
				day = 30;
				break;
			case 2:
				if (year % 4 == 0) {
					day = 29;
				} else {
					day = 28;
				}
				break;
			default:
				break;
			}
			month = month - 1;
		}
		String predate = Integer.toString(year) + "-"
				+ (month < 10 ? "0" + month : month) + "-"
				+ (day < 10 ? "0" + day : day);
		return predate;
	}

	/**
	 * <p>获取中文日期格式</p>
	 * 格式：xxxx年xx月xx�?
	 * 
	 * @param date
	 * @return
	 */
	/*public static String getChinaDateFormat(Date date) {
		// 获取yyyy-mm-dd格式日期格式
		String dateStr = getDateTime_I(date);
		StringBuffer sb = new StringBuffer();
		if (dateStr != null && dateStr.length() > 0) {
			String[] newStr = dateStr.split("-");
			// 获取�?
			int month = Integer.valueOf(newStr[1]);
			// 获取�?
			int day = Integer.valueOf(newStr[2]);
			sb.append(newStr[0]).append("�?");
			sb.append(month).append("�?").append(day).append("�?");
		}
		return sb.toString();
	}*/
	
	/**
	 * <p>获取中文日期时间格式</p>
	 * 格式：xxxx年xx月xx�?<br>
	 * 
	 * @param date 指定日期对象，为null时获取当前系统时�?
	 * @return 返回诸如“xxxx年xx月xx日�?�格式的日期
	 */
	public static String getChineseDate(Date date) {
		if(date == null)
			date = new Date();
		int yyyy = getCustomYear(date);
		int MM = getCustomMonth(date);
		int dd = getCustomDay(date);
		
		StringBuilder sb = new StringBuilder();
		sb.append(yyyy + "�?");
		sb.append(MM + "�?");
		sb.append(dd + "�?");
		return sb.toString();
	}
	
	/**
	 * <p>获取中文日期时间格式</p>
	 * 格式：xxxx年xx月xx�? xx时xx分xx�?
	 * 
	 * @param date 指定日期对象，为null时获取当前系统时�?
	 * @return 返回诸如“xxxx年xx月xx�? xx时xx分xx秒�?�格式的日期
	 */
	public static String getChineseDateTime(Date date) {
		if(date == null)
			date = new Date();
		int yyyy = getCustomYear(date);
		int MM = getCustomMonth(date);
		int dd = getCustomDay(date);
		
		int HH = getCustomHour(date);
		int mm = getCustomMinute(date);
		int ss = getCustomSecond(date);
		
		StringBuilder sb = new StringBuilder();
		sb.append(yyyy + "�?");
		sb.append(MM + "�?");
		sb.append(dd + "�?");
		sb.append(" ");
		sb.append(HH + "�?");
		sb.append(mm + "�?");
		sb.append(ss + "�?");
		
		return sb.toString();
	}
	
	/**
	 * add by lipp
	 * <p>
	 * 获取xxxx年xx月xx�? 日期格式�?
	 * </p>
	 * 
	 * @param date
	 *            格式必须�?2009-03-21字符�?
	 * @return
	 */
	public static String getChinaDateFormat(String date) {
		// 获取yyyy-mm-dd格式日期格式
		StringBuffer sb = new StringBuffer();
		if (date != null && date.length() > 0) {
			String[] newStr = date.split("-");
			// 获取�?
			int month = Integer.valueOf(newStr[1]);
			// 获取�?
			int day = Integer.valueOf(newStr[2]);
			sb.append(newStr[0]).append("�?");
			sb.append(month).append("�?").append(day).append("�?");
		}
		return sb.toString();
	}

	/**
	 * 判断�?个日期字符串是否合法
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @author liufengyu
	 */
	public static boolean isDateStringCorrect(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);

		try {
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * add by gongtao
	 * <P>
	 * 将字符串类型的日期格�? 转换�? 符合要求的日期格�?
	 * </P>
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStrDate4String(String date, String format) {
		if (date == null || date.trim().equals("")) {
			return "";
		} else {
			SimpleDateFormat df = new SimpleDateFormat(format);
			try {
				Date d = df.parse(date);
				return df.format(d);
			} catch (ParseException e) {
				System.out.println(e);
				return "";
			}
		}
	}
	
	/**
	 * add by gongtao
	 * <P>
	 * 将Date类型的日期格�? 转换�? 符合要求�? String日期格式
	 * </P>
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStrDate4Date(Date date, String format) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
	}

	/**
	 * add by gongtao
	 * <P>
	 * 将字符串类型的日期格�? 转换�? 符合要求�? Date类型的日期格�?
	 * </P>
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDate4StrDate(String date, String format) {
		if (date == null || date.trim().equals("")) {
			return null;
		} else {
			SimpleDateFormat df = new SimpleDateFormat(format);
			try {
				return df.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	/**
	 * add by gongtao 计算指定日期时间之间的时间差
	 * 
	 * @param beginStr
	 *            �?始日期字符串
	 * @param endStr
	 *            结束日期字符�?
	 * @param f
	 *            时间差的形式0-�?,1-分种,2-小时,3--�? 日期时间字符串格�?:yyyyMMddHHmmss
	 * */
	public static int getInterval(String beginStr, String endStr, int f) {
		int hours = 0;
		try {
			Date beginDate = yMd_Hms.parse(beginStr);
			Date endDate = yMd_Hms.parse(endStr);
			long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减获取日期差X(单位:毫秒)
			/**
			 * Math.abs((int)(millisecond/1000)); 绝对�? 1�? = 1000毫秒
			 * millisecond/1000 --> �? millisecond/1000*60 - > 分钟
			 * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
			 * �?
			 * */
			switch (f) {
			case 0: // second
				return (int) (millisecond / 1000);
			case 1: // minute
				return (int) (millisecond / (1000 * 60));
			case 2: // hour
				return (int) (millisecond / (1000 * 60 * 60));
			case 3: // day
				return (int) (millisecond / (1000 * 60 * 60 * 24));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hours;
	}

	/**
	 * add by lipp
	 * <P>
	 * 获取起始日期前或后天数的日期
	 * </P>
	 * 
	 * @param starttime
	 *            起始日期 格式：yyyy-MM-dd
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static Date getStartDateInterval(String starttime, int days) {
		// 格式化起始时�? yyyyMMdd
		Date startDate = null;
		try {
			startDate = yyyy_MM_dd.parse(starttime);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar startTime = Calendar.getInstance();
		startTime.clear();
		startTime.setTime(startDate);

		startTime.add(Calendar.DAY_OF_YEAR, days);
		return startTime.getTime();
	}

	/**
	 * add by lipp
	 * <P>
	 * 获取起始日期和结束日期之间的天数
	 * </P>
	 * 
	 * @param beginStr
	 *            起始日期
	 * @param endStr
	 *            结束日期
	 * @param format
	 *            根据 日期参数的格式，传对应的SimpleDateFormat格式
	 * @return 天数
	 */
	public static int getDaysInterval(String beginStr, String endStr,
			SimpleDateFormat format) {

		try {
			Date beginDate = format.parse(beginStr);
			Date endDate = format.parse(endStr);
			long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减获取日期差X(单位:毫秒)
			return (int) (millisecond / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取本月第一�?
	 * @return
	 */
	public static Date getFristDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();

	}
	
	/**
	 * 获取本月�?后一�?
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();

	}
	
	/**
	 * 获得本周�?的日�?
	 * @return
	 * @throws ParseException 
	 */
	/*public static Date getMondayOFWeek() throws ParseException {
	  int mondayPlus = getMondayPlus();
	  GregorianCalendar currentDate = new GregorianCalendar();
	  currentDate.add(Calendar.DATE, mondayPlus);
	  Date monday = currentDate.getTime();
	  String dateStr = getDateTime_I(monday);
	  StringBuffer sb = new StringBuffer(dateStr);
	  sb.append(" ").append("00:00:00");
	  return parseDateStrToDate(sb.toString());
	}*/
	
	/**
	 * 获得本周星期日的日期
	 * @return
	 * @throws ParseException 
	 */
	/*public static Date getCurrentWeekday() throws ParseException {
	  int mondayPlus = getMondayPlus();
	  GregorianCalendar currentDate = new GregorianCalendar();
	  currentDate.add(Calendar.DATE, mondayPlus + 6);
	  Date monday = currentDate.getTime();
	  String dateStr = getDateTime_I(monday);
	  StringBuffer sb = new StringBuffer(dateStr);
	  sb.append(" ").append("00:00:00");
	  return parseDateStrToDate(sb.toString());
	}*/
	
	/**
	 * 字符串时间格式转换为 Date
	 * @param date 此格�? yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
/*	public static Date parseDateStrToDate(String date) 
			throws ParseException{
		Date date_time = null;
		if(!StringUtil.isEmpty(date)){
			date_time = yMd_Hms.parse(date);
		}
		return date_time;
	}
	*/
	/**
	 * 字符串时间格式转换为 Date
	 * @param date 此格�? yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
/*	public static Date parseDateFromStr(String date) 
			throws ParseException{
		Date date_time = null;
		if(!StringUtil.isEmpty(date)){
			date_time = yyyy_MM_dd.parse(date);
		}
		return date_time;
	}*/

	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 因为按中国礼拜一作为第一天所以这里减1
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; 
		if (dayOfWeek == 1) {
			return 0;
		}else if(dayOfWeek==0){
			return 1-7;
		} else {
			return 1 - dayOfWeek;
		}
	}
	
	
	
	/**
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param f 
	 *  时间差的形式0:�?,1:分种,2:小时,3:�? 
	 * @return
	 */
	public static int getDifferenceNum(Date beginDate,Date endDate,int f){
		int result = 0;
		if(beginDate==null || endDate==null){
			return 0;
		}
		try{
			// 日期相减获取日期差X(单位:毫秒)
			long millisecond = endDate.getTime() - beginDate.getTime(); 
			/**
			 * Math.abs((int)(millisecond/1000)); 绝对�? 1�? = 1000毫秒
			 * millisecond/1000 --> �? millisecond/1000*60 - > 分钟
			 * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
			 * �?
			 * */
			switch (f) {
			case 0: // second
				return (int) (millisecond / 1000);
			case 1: // minute
				return (int) (millisecond / (1000 * 60));
			case 2: // hour
				return (int) (millisecond / (1000 * 60 * 60));
			case 3: // day
				return (int) (millisecond / (1000 * 60 * 60 * 24));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * <p>比较两个日期的大�?,精确到秒</p>
	 * 
	 * @param d1
	 * @param d2
	 * @author lipp@icloud-edu.com
	 * @date 2014-06-03
	 * @return 返回�?个long类型的整数，若大�?0表示第一个日期晚于第二个日期，小�?0表示第一个日期早于第二个日期，否则相�?
	 */
	public static long compareEachOther(Date d1, Date d2){
		if(d1 == null || d2 == null)
			return -1;
		String d1Str = d1.getTime() + "";
		String d2Str = d2.getTime() + "";
		int l1 = d1Str.length();
		int l2 = d2Str.length();
		d1Str = d1Str.substring(0, l1 - 3) + "000";
		d2Str = d2Str.substring(0, l2 - 3) + "000";
		//System.out.println(d1Str + "   " + d2Str);
		long long1 = Long.parseLong(d1Str);
		long long2 = Long.parseLong(d2Str);
		return long1 - long2;
	}
	
	public static void main(String[] args) {
		
		System.out.println(getFristDayOfMonth());
		System.out.println(getCurrentYear());
		System.out.println(getCurrentMonth());
		System.out.println(getCurrentDay());
		
	}
	
}
