package com.cmu.tiegen.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.cmu.tiegen.entity.Booking;


public class BookingDao extends Db {
	
	private long fixDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	public void create(int userId, int serviceProviderId, Date date, String time) throws SQLException {
		// TODO: booking_insert
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("booking_insert"));
		stmt.setInt(1, userId);
		stmt.setInt(2, serviceProviderId);
		stmt.setDate(3, new java.sql.Date(fixDate(date)));
		stmt.setString(4, time);
		stmt.executeUpdate();
		stmt.close();
	}

    public int getBookingId(int uid,Date date,int sid) throws SQLException{
    	PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("booking_get_bookingid"));
		stmt.setInt(1, uid);
		stmt.setDate(2,new java.sql.Date(fixDate(date)));
		stmt.setInt(3, sid);
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next();
		int id = -1;
		if (exists) {
			id = rs.getInt(1);
		}
		stmt.close();
		return id;
    }
    
    public ArrayList<Booking> getAllBookingsByDate(int uid, Date date) throws SQLException{
    	PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("booking_get_all_by_date"));
		stmt.setInt(1, uid);
		stmt.setDate(2,new java.sql.Date(fixDate(date)));
		ResultSet rs = stmt.executeQuery();
		ArrayList<Booking> retList = new ArrayList<>();
		while (rs.next()) {
			Booking booking = new Booking(uid, rs.getInt(2), rs.getDate(3));
			booking.setOrderId(rs.getInt(1));
			booking.setServiceName(rs.getString(4));
			booking.setTime(rs.getString(5));
			retList.add(booking);
		}
		stmt.close();
		return retList;
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
