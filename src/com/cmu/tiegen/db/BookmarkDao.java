package com.cmu.tiegen.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cmu.tiegen.entity.BookMark;
import com.cmu.tiegen.entity.Service;

public class BookmarkDao extends Db {

	public void create(int userId, int serviceProviderId) throws SQLException {
		// TODO: bookmark_insert
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("bookmark_insert"));
		stmt.setInt(1, userId);
		stmt.setInt(2, serviceProviderId);
		stmt.executeUpdate();
		stmt.close();
	}

	public void delete(int userId, int serviceId) throws SQLException {
		// TODO: bookmark_delete
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("bookmark_delete"));
		stmt.setInt(1, userId);
		stmt.setInt(2, serviceId);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public ArrayList<Service> getAllBookmarkedServices(int userId) throws SQLException {
		// TODO: bookmark_get_all_services
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("bookmark_get_all"));
		ArrayList<Service> services = new ArrayList<Service>();
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Service service = new Service(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6));
			service.setServiceId(rs.getInt(1));
			services.add(service);
		}
		stmt.close();
		return services;
	}

}
