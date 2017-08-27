package com.etrack.job.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.etrack.global.DateUtil;
import com.etrack.global.PersistentUtility;
import com.etrack.job.exception.InsertJobException;
import com.etrack.job.model.JobRequestModel;
import com.etrack.job.util.JobUtility;

@Repository
public class JobRepository {

	@Autowired
	private DataSource dataSource;

	// QUERIES MYSQL //
	private static final String INSERT_NEW_JOB = "INSERT INTO JOB (JOB_NO, JOB_DESCRIPTION, JOB_REMARKS, REQUEST_BY, REQUEST_TO, JOB_STATUS, OPEN_DATE)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
	// ---------------------------------------------------------------------------------------------------//

	/**
	 * To insert new job in the DB. At this stage, model must be ensured to have
	 * all the mandatory fields being filled up before inserting to DB tables,
	 * fields included are JOB_NO, JOB_DESCRIPTION, JOB_REMARKS, REQUEST_BY,
	 * REQUEST_TO, JOB_STATUS and OPEN_DATE. Only CLOSE_DATE are to be left as
	 * null and this field eventually be updated in runtime as user update the
	 * job status . Before getting into this method, ensure that validation of
	 * fields is carried out from the caller before calling this.
	 * 
	 * @param model
	 *            the job model to be insert
	 * @return a boolean value indicating if the insertion is successful.
	 * @throws InsertJobException
	 * @throws SQLException
	 */
	public boolean insertNewJob(JobRequestModel model) throws InsertJobException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(INSERT_NEW_JOB);
			ps.setString(1, model.getJobNo());
			ps.setString(2, model.getJobDescription());
			ps.setString(3, model.getRemarks());
			ps.setString(4, model.getReqBy());
			ps.setString(5, model.getReqTo());
			ps.setInt(6, JobUtility.getJobStatusCode(model.getJobStatus()));
			ps.setTimestamp(7, DateUtil.getTimestamp(model.getOpenDate()));

			int added = ps.executeUpdate();
			con.commit();
			if (added < 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			throw new InsertJobException("Error occured in : insertNewJob()");
		} finally {
			PersistentUtility.close(ps);
			PersistentUtility.close(con);
		}
	}

}
