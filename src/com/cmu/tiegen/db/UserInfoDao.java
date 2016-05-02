package com.cmu.tiegen.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cmu.tiegen.entity.User;

public class UserInfoDao extends Db {

	public void create(String userName, String password) throws SQLException {
		// TODO: userinfo_insert
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("userinfo_insert"));
		stmt.setString(1, userName);
		stmt.setString(2, password);
		stmt.executeUpdate();
		stmt.close();
	}

	public int getUserId(String userName) throws SQLException {
		// TODO: userinfo_get_id
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("userinfo_get_id"));
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next();
		int userId = -1;
		if (exists) {
			userId = rs.getInt(1);
		}
		stmt.close();
		return userId;
	}

	public User loginUser(String userName, String password) throws SQLException {
		// TODO: userinfo_get_user
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("userinfo_get_user"));
		stmt.setString(1, userName);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next();
		User user = null;
		if (exists) {
			user = new User(rs.getString(2), rs.getString(3));
			user.setUserId(rs.getInt(1));
		}
		stmt.close();
		return user;
	}

	public void delete(String userName) throws SQLException {
		// TODO: userinfo_delete
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("userinfo_delete"));
		stmt.setString(1, userName);
		stmt.executeUpdate();
		stmt.close();
	}

//	public void updatePassword(String userName, String password) throws SQLException {
//		// TODO: userinfo_update_password
//		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("userinfo_update_password"));
//		stmt.setString(1, userName);
//		stmt.setString(2, password);
//		stmt.executeUpdate();
//		stmt.close();
//	}
}
