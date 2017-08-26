package com.etrack.job.util;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import com.etrack.job.model.JobRequestModel;

public class JobUtility {

	
	/**
	 * Validating the content for this new JOB
	 * 
	 * @param model
	 * @return true if all the contents in field are approved, false if some fields are invalid
	 */
	public static boolean validateJobRequest(JobRequestModel model){
		
		String description = model.getJobDescription();
		String remarks = model.getRemarks();
		if(description.length() >= 30 || remarks.length() >= 100){
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * To set the open date for the particular job 
	 * will only run once upon creation of the new JOB entity
	 * 
	 * @param model
	 */
	public static void setOpenJobDate(JobRequestModel model){
		model.setOpenDate(new Date());
	}
	
	/**
	 * To set the closing date for the particular job 
	 * Will sue this to get closing date when user have approved
	 * the job to be resolved. 
	 * This will only return the current system time 
	 * 
	 * @param model
	 */
	public static void setCloseJobDate(JobRequestModel model){
		model.setCloseDate(new Date());
	}
	
	/**
	 * This generate the new JOB number for newly created.
	 * Will you a basic 4 digits with randomly generated from 0 -9 
	 * 
	 * @param model
	 */
	public static void setJobNumber(JobRequestModel model){
		// DO ALGO HERE // 
		Random ran = new Random();
		int digit1 = ran.nextInt(9 - 0 + 1) + 0;
		int digit2 = ran.nextInt(9 - 0 + 1) + 0;
		int digit3 = ran.nextInt(9 - 0 + 1) + 0;
		int digit4 = ran.nextInt(9 - 0 + 1) + 0;
		
		String index = (String.valueOf(digit1) + String.valueOf(digit2) + String.valueOf(digit3) + String.valueOf(digit4));
		model.setJobNo(index);
	}
	
	
	
	
	
	
}
