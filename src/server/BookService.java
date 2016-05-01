package server;

import java.util.ArrayList;

import entity.Booking;
import entity.QueryInfo;
import entity.Service;
import entity.User;

public interface BookService {
	
	public void bookService(Booking booking) throws Exception;
	public void cancelBooking(Booking booking) throws Exception;
	public ArrayList<Service> search(QueryInfo info) throws Exception;
	
}
