package com.cmu.tiegen.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BookingDao extends Db {
	public void create(int userId, int serviceProviderId, Date date) throws SQLException {
		// TODO: booking_insert
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("booking_insert"));
		stmt.setInt(1, userId);
		stmt.setInt(2, serviceProviderId);
		stmt.setDate(3, new java.sql.Date(date.getTime()));
		stmt.executeUpdate();
		stmt.close();
	}

	public int getServiceId(int userId, int bookingId) throws SQLException {
		// TODO: booking_get_serviceid
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("booking_get_serviceid"));
		stmt.setInt(1, userId);
		stmt.setInt(2, bookingId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int autoId = rs.getInt(1);
		stmt.close();
		return autoId;
	}

	public void delete(int bookingId) throws SQLException {
		// TODO: booking_delete
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("booking_delete"));
		stmt.setInt(1, bookingId);
		stmt.executeUpdate();
		stmt.close();
	}

	// Any other change should require Deleting and creating new booking
	public void updateFrequency(int bookingId, String frequency) throws SQLException {
		// TODO: booking_update
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("booking_update"));
		stmt.setInt(1, bookingId);
		stmt.setString(2, frequency);
		stmt.executeUpdate();
		stmt.close();
	}
}
