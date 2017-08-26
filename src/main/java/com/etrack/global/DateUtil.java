package com.etrack.global;

import java.text.ParseException;

public class DateUtil {

	private DateUtil(){
		// Singleton
	}
	
	
	public static java.sql.Timestamp getTimestamp(java.util.Date utilDate){
		java.sql.Timestamp timestamp;
		java.text.DateFormat format = new java.text.SimpleDateFormat(GlobalAppConstant.GLOBAL_DATE_FORMAT);
		timestamp = new java.sql.Timestamp(utilDate.getTime());
		System.out.println(timestamp); // prints "2011-02-10 12:05:34.0"
		return timestamp;
		
	}
	

	
}
