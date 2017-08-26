package com.etrack.job.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.etrack.job.exception.InsertJobException;
import com.etrack.job.model.JobRequestModel;

@Repository
public class JobRepository {

	@Autowired
	private DataSource dataSource;
	
	
	// QUERIES MYSQL // 
//	private static final String INSERT INTO JOB (JOB_NO, DESCRIPTION, REMARKS)
	
	
	public boolean insertNewJob(JobRequestModel model)throws InsertJobException {
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement("");
			return true;
		}catch(SQLException e){
			throw new InsertJobException("Error occured in : insertNewJob()");
		}
	}

	
	
	
}
