package com.youzitech.crawler.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	private static SimpleDateFormat sf = null;
	public static String format(Date date,String pattern){
		sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}
}
