package com.etrack.global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class PersistentUtility {
	
	private PersistentUtility(){
		// singleton
	}

	@Autowired
	private static DataSource dataSource;
	
	
	public static Connection getConn()throws SQLException{
		return dataSource.getConnection();
	}
	
	public static void close(Connection con)throws SQLException {
		if(con != null){
			con.close();
		}
	}
	
	public static void close(PreparedStatement ps)throws SQLException{
		if(ps != null){
			ps.close();
		}
	}
	
	public static void close(ResultSet rs)throws SQLException {
		if(rs != null){
			rs.close();
		}
	}
	
	public static void close(Statement st)throws SQLException {
		if(st != null){
			st.close();
		}
	}
	
}
