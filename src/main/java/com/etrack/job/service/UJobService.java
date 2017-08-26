package com.etrack.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrack.job.model.JobRequestModel;
import com.etrack.job.repository.JobRepository;
import com.etrack.job.util.JobUtility;

@Service
public class UJobService implements IJobService{

	@Autowired
	JobRepository jobRepo;
	
	@Override
	public boolean createNewJob(JobRequestModel model) {
		JobUtility.setOpenJobDate(model);
		JobUtility.setJobNumber(model);	
		model.setJobStatus(JobRequestModel.OPEN);
		return jobRepo.insertNewJob(model));
			
		
	}

	@Override
	public boolean deleteJob(int jobNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
