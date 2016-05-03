package com.cmu.tiegen.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cmu.tiegen.entity.Service;

public class ServiceProviderInfoDao extends Db {

	public void create(String serviceProviderName, String serviceProviderType, String serviceProviderLocation, String serviceProviderWebsite) throws SQLException {
		// TODO: spinfo_insert
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("spinfo_insert"));
		stmt.setString(1, serviceProviderName);
		stmt.setString(2, serviceProviderType);
		stmt.setString(3, serviceProviderLocation);
		stmt.setString(4, serviceProviderWebsite);
		stmt.setFloat(5, 0.0f);
		stmt.executeUpdate();
		stmt.close();
	}

	public int getServiceProviderId(String serviceProviderName) throws SQLException {
		// TODO: spinfo_get_id
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("spinfo_get_id"));
		stmt.setString(1, serviceProviderName);
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next();
		int serviceId = -1;
		if (exists) {
			serviceId = rs.getInt(1);
		}
		stmt.close();
		return serviceId;
	}

	public ArrayList<Service> getAllServicesQuery(String nameRegex, String locationRegex, String typeRegex) throws SQLException {
		// TODO: spinfo_get_query
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("spinfo_get_query"));
		ArrayList<Service> services = new ArrayList<Service>();
		
		stmt.setString(1, nameRegex);
		stmt.setString(2, locationRegex);
		stmt.setString(3, typeRegex);
		System.out.print(stmt.toString());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Service service = new Service(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6));
			service.setServiceId(rs.getInt(1));
			services.add(service);
		}
		stmt.close();
		return services;
	}

	public void delete(String serviceProviderName) throws SQLException {
		// TODO: spinfo_delete
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("spinfo_delete"));
		stmt.setString(1, serviceProviderName);
		stmt.executeUpdate();
		stmt.close();
	}

	public void updateWebsite(String serviceProviderName, String website) throws SQLException {
		// TODO: spinfo_update_website
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("spinfo_update_website"));
		stmt.setString(1, serviceProviderName);
		stmt.setString(2, website);
		stmt.executeUpdate();
		stmt.close();
	}

	public void updateRating(int serviceId, float rating) throws SQLException {
		// TODO: spinfo_update_rating
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("spinfo_update_rating"));
		stmt.setFloat(1, rating);
		stmt.setInt(2, serviceId);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void updateLocation(String serviceProviderName, String location) throws SQLException {
		// TODO: spinfo_update_location
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("spinfo_update_location"));
		stmt.setString(1, serviceProviderName);
		stmt.setString(2, location);
		stmt.executeUpdate();
		stmt.close();
	}
}
