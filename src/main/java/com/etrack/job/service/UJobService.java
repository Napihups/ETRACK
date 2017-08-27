package com.etrack.job.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrack.job.exception.InsertJobException;
import com.etrack.job.exception.InvalidJobDataException;
import com.etrack.job.model.JobRequestModel;
import com.etrack.job.repository.JobRepository;
import com.etrack.job.util.JobUtility;

@Service
public class UJobService implements IJobService{

	@Autowired
	JobRepository jobRepo;
	
	@Override
	public boolean createNewJob(JobRequestModel model) throws InvalidJobDataException {
		if(JobUtility.validateJobRequest(model)){
			try {
				return jobRepo.insertNewJob(model);
			} catch (InsertJobException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {
			throw new InvalidJobDataException("");
		}
		
			
		
	}

	@Override
	public boolean deleteJob(int jobNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
