package com.etrack.global;

import java.util.Date;

public class CommonUtil {

	private CommonUtil() {
		// no args conts
	}

	/**
	 * To check the value of the String. Will consider all condition as a no
	 * value
	 * 
	 * @param s
	 * @return
	 */
	public static boolean checkValueEmpty(String s) {
		if (s == null || s.equals("") || s.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean checkValueEmpty(Date s) {
		if (s == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean checkValueEmpty(Integer s) {
		if (s == null) {
			return true;
		} else {
			return false;
		}
	}

}
