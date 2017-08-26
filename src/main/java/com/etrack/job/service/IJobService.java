package com.etrack.job.service;

import com.etrack.job.model.JobRequestModel;

public interface IJobService {

	/**
	 * Creating the new created JOB and pass to DAO for db insert
	 * <code>JobRequestModel<code>
	 * 
	 * @param model
	 * @return
	 */
	public boolean createNewJob(JobRequestModel model);
	
	
	/**
	 * To delete the job
	 * Using the job number for referencing the job entity
	 * 
	 * @param jobNumber
	 * @return
	 */
	public boolean deleteJob(int jobNumber);
	
	
	

}
