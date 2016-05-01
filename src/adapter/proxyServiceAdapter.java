package adapter;

import java.util.ArrayList;
import java.util.Date;

import entity.BookMark;
import entity.CalendarDay;
import entity.Booking;
import entity.QueryInfo;
import entity.Rate;
import entity.Service;
import entity.User;

import db.BookingDao;
import db.BookmarkDao;

public abstract class proxyServiceAdapter {
	
	//book service 
	/*
	 * insert this booking record in database
	 */
	public void proxyBookService(Booking booking) throws Exception{

		new BookingDao().create(booking.getUserId(), booking.getServiceId(), booking.getDate());
	}
	
	/*
	 * get user id and service id from bookmark object and insert into database
	 */
	public void proxyAddBookMark(BookMark bookmark) throws Exception{
		new BookmarkDao().create(bookmark.getUserId(), bookmark.getServiceId());
		
	}
	
	public CalendarDay proxyLoadCalendarDay(CalendarDay day){
		/*
		 * get the user id and date attribute from the calendarDay object and put all the bookings that match the criteria into an arraylist
		 *  and encapsulate this arraylist in an calendarday object
		 */
		return null;
	}
	
	public void proxyRateBooking(Rate rate){
		/*
		 * we need to go to database and fetch the average rate of this service and update it 
		 */
	
		int score = rate.getRate();
		/*
		 * int average = get the average rate of this service( service table should have a column that track the average rate of the service)
		 * int num = number of rates associated with this service ( rate table)
		 * int newAverage = (num*average + score)/(num + 1)
		 * 
		 * update the new average score in service table
		 */
		
		
		/*
		 * get user id, booking id, rate & review from rate object and insert into database
		 */
	}
	
	public ArrayList<Service> proxyLoadBookMark(int UserId){
		/*
		 * return all bookmark object and put them in an arrayList
		 */
		return null;
	}
	
	public void proxyEditRate(Rate rate){
		
		/*1.we need to go to database and fetch the average rate of this service and update it, just like the add rate case
		 *
		 * 2.update the rate in database
		 * 
		 */
		
	}
	
	public void proxyCancelBooking(Booking booking){
		/*delete the booking in database
		 * 
		 */
	
	}
	
	public void proxyDeleteBookMark(BookMark bookmark) throws Exception{
		/*
		 * get user id and service id from bookmark object and insert into database
		 */
		new BookmarkDao().delete(bookmark.getUserId(), bookmark.getServiceId());
	}
	
	public ArrayList<Service> proxySearch(QueryInfo qinfo){
		/*
		 * get the attribute of qinfo(like type, name...) and query for result in database accordingly
		 */
	return null;
		
	}
	
	public ArrayList<Rate> proxyDisplayRates(int ServiceId){
		/*
		 * get all rates of certain service from database
		 */
		return null;
	}
}
