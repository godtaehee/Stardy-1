package com.stardy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	public static String transDate(Date date) {
		
		return sdf.format(date);
	}
}
