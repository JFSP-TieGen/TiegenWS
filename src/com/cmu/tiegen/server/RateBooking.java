package com.cmu.tiegen.server;

import java.util.ArrayList;

import com.cmu.tiegen.entity.Booking;
import com.cmu.tiegen.entity.Rate;
import com.cmu.tiegen.entity.Service;

public interface RateBooking {
	public void rateBooking(Rate rate) throws Exception;
	public void editRate(Rate rate) throws Exception;
	public ArrayList<Rate> displayRates(int ServiceId) throws Exception;
}
