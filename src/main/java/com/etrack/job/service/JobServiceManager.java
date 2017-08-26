package com.etrack.job.service;

public class JobServiceManager {

	public static final int IMPL = 1;
	
	private JobServiceManager(){
		// singleton
	}
	
	
	public static IJobService getJobService(int implNumber){
		switch(implNumber){
		case 1: return new UJobService();
		case 2: return new UJobService();
		case 3: return new UJobService();
		default: return new UJobService();
		}
	}
	
}
