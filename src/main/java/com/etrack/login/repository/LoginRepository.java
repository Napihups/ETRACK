package com.etrack.login.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.etrack.global.PersistentUtility;
import com.etrack.registration.model.UserRole;
import com.etrack.registration.model.Users;

@Repository
public class LoginRepository {

	@Autowired
	private DataSource dataSource;
	
	
	private final static String FIND_USER_BY_USERNAME = 
			"SELECT * FROM USER U WHERE U.USERNAME = ?";
	
	private final static String FIND_USER_ROLES = 
			"SELECT ROLE FROM USER_ROLE INNER JOIN ROLE ON ROLE.ROLE_ID = USER_ROLE.ROLE_ID WHERE USER_ID = ?";
	
	/*---------------------------------------------------------------*/
	
	
	public Users findUserByUsername(String username) throws UsernameNotFoundException, SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(FIND_USER_BY_USERNAME);
			ps.setString(1, username);
			rs = ps.executeQuery();
			Users user = null;
			if(rs.next()){
				user = new Users();
				user.setUserId(rs.getInt("USER_ID"));
				user.setActive(rs.getInt("ACTIVE"));
				user.setEmail(rs.getString("EMAIL"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassWord(rs.getString("PASSWORD"));
				user.setLastPasswordReset(rs.getTimestamp("LAST_RESET_PASSWORD"));
				getUserRights(user);
			}
			return user;
		}catch(SQLException e){
			System.out.println("Error in findUserByUsername() " + e);
			throw new UsernameNotFoundException("User not found !");
		}finally {
			PersistentUtility.close(rs);
			PersistentUtility.close(ps);
			PersistentUtility.close(con);
		}
	}
	
	public void getUserRights(Users user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs  = null;
		Set<UserRole> userRights = new HashSet<UserRole>();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(FIND_USER_ROLES);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while(rs.next()){
				userRights.add(new UserRole(rs.getString("ROLE")));
			}
			user.setUserRights(userRights);
		}catch(SQLException e){
			throw new SQLException(e);
		}finally {
			PersistentUtility.close(rs);
			PersistentUtility.close(ps);
			PersistentUtility.close(con);
		}
	}
	
	
}
