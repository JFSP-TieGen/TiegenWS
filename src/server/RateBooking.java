package server;

import java.util.ArrayList;

import entity.Booking;
import entity.Rate;
import entity.Service;

public interface RateBooking {
	public void rateBooking(Rate rate) throws Exception;
	public void editRate(Rate rate) throws Exception;
	public ArrayList<Rate> displayRates(int ServiceId) throws Exception;
}
