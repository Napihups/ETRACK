package com.etrack.registration.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.etrack.global.DateUtil;
import com.etrack.global.PersistentUtility;
import com.etrack.registration.exception.RegisterException;
import com.etrack.registration.model.UserRole;
import com.etrack.registration.model.Users;

@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;

	private final static String INSERT_NEW_USER = 
			"INSERT INTO USER (ACTIVE, EMAIL, USERNAME, PASSWORD,LAST_RESET_PASSWORD) VALUES (?, ?, ?, ?, ?)";
	private final static String SET_USER_ROLE = 
			"INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES " + 
	"((SELECT USER_ID FROM USER WHERE USERNAME = ?), (SELECT ROLE_ID FROM ROLE WHERE ROLE = ?));";
	
	// ----------------------------------------------------------------//


	
	/**
	 * Inserting user model into database.
	 * This method will also setup the user roles,
	 * will throw Register Exception if any of the execution failed
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 * @throws RegisterException
	 */
	public void registerNewUser(Users user) throws SQLException, RegisterException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(INSERT_NEW_USER);
			ps.setInt(1, user.getActive());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassWord());
			ps.setTimestamp(5, DateUtil.getTimestamp(user.getLastPasswordReset()));
			ps.executeUpdate();
			con.commit();
			setupUserRights(user);
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			PersistentUtility.close(con);
			PersistentUtility.close(rs);
			PersistentUtility.close(ps);
		}
		
	}
	
	/**
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void setupUserRights(Users user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SET_USER_ROLE);
			for(UserRole ur : user.getUserRights()){
				ps.setString(1, user.getUserName());
				ps.setString(2, ur.getRight());
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
		}catch(SQLException e){
			throw new SQLException(e);
		}finally {
			PersistentUtility.close(ps);
			PersistentUtility.close(rs);
			PersistentUtility.close(con);
		}
	}
	
	

	
	
	
}
