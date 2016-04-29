package server;

import java.util.ArrayList;

import entity.Booking;
import entity.Rate;
import entity.Service;

public interface RateBooking {
	public void rateBooking(Rate rate);
	public void editRate(Rate rate);
	public ArrayList<Rate> displayRates(int ServiceId);
}
