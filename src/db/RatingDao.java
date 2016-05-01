package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Rate;

public class RatingDao extends Db {
	public void create(int userId, int serviceProviderId, int bookingId, float rating, String review) throws SQLException {
		// TODO: rating_insert
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("rating_insert"));
		stmt.setInt(1, userId);
		stmt.setInt(2, serviceProviderId);
		stmt.setInt(3, bookingId);
		stmt.setFloat(4, rating);
		stmt.setString(5, review);
		stmt.executeUpdate();
		stmt.close();
	}

	public float avgRating(int serviceProviderId) throws SQLException {
		// TODO: rating_avg
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("rating_avg"));
		stmt.setInt(1, serviceProviderId);
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next();
		float rating = 0;
		if (exists) {
			rating = rs.getFloat(1);
		}
		stmt.close();
		return rating;
	}

	public int countRating(int serviceProviderId) throws SQLException {
		// TODO: rating_count
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("rating_count"));
		stmt.setInt(1, serviceProviderId);
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next();
		int numRating = 0;
		if (exists) {
			numRating = rs.getInt(1);
		}
		stmt.close();
		return numRating;
	}
	
	public ArrayList<Rate> getRatings(int serviceProviderId) throws SQLException {
		// TODO: rating_list
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("rating_list"));
		stmt.setInt(1, serviceProviderId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Rate> ratings = new ArrayList<>();
		while (rs.next()) {
			Rate rate = new Rate();
			rate.setRateId(rs.getInt(1));
			rate.setUserId(rs.getInt(2));
			rate.setOrderId(rs.getInt(3));
			rate.setRate(rs.getFloat(4));
			rate.setReview(rs.getString(5));
			ratings.add(rate);
		}
		stmt.close();
		return ratings;
	}

	public void delete(int ratingId) throws SQLException {
		// TODO: rating_delete
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("rating_delete"));
		stmt.setInt(1, ratingId);
		stmt.executeUpdate();
		stmt.close();
	}

	public void updateRating(int ratingId, float rating, String review) throws SQLException {
		// TODO: rating_update
		PreparedStatement stmt = this.connection.prepareStatement(dbProps.getProperty("rating_update"));
		stmt.setInt(1, ratingId);
		stmt.setFloat(2, rating);
		stmt.setString(3, review);
		stmt.executeUpdate();
		stmt.close();
	}
}
