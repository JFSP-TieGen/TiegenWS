package com.cmu.tiegen.server;

import java.util.ArrayList;

import com.cmu.tiegen.entity.Booking;
import com.cmu.tiegen.entity.QueryInfo;
import com.cmu.tiegen.entity.Service;
import com.cmu.tiegen.entity.User;

public interface BookService {
	
	public void bookService(Booking booking) throws Exception;
	public void cancelBooking(Booking booking) throws Exception;
	public ArrayList<Service> search(QueryInfo info) throws Exception;
	
}
