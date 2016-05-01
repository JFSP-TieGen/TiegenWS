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
import db.RatingDao;
import db.ServiceProviderInfoDao;

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
	public void proxyRateBooking(Rate rate) throws Exception {
		/*
		 * we need to go to database and fetch the average rate of this service and update it 
		 */
	
		float score = rate.getRate();
		int serviceId = new BookingDao().getServiceId(rate.getRateId(), rate.getOrderId());
		int countRating = new RatingDao().countRating(serviceId);
		float avgRating = new RatingDao().avgRating(serviceId);
		float newAvg = (float)(((float)(countRating * avgRating) + score)/ countRating + 1);
		new ServiceProviderInfoDao().updateRating(rate.getOrderId(), newAvg);
		new RatingDao().create(rate.getRateId(), serviceId, rate.getOrderId(), rate.getRate(), "Some Review");
	}
	
	public ArrayList<Service> proxyLoadBookMark(int userId) throws Exception{
		return new BookmarkDao().getAllBookmarkedServices(userId);
	}
	
	public void proxyEditRate(Rate rate) throws Exception {
		// GAYATRI : what is difference between this functions and proxyRateBooking ???
		proxyRateBooking(rate);
	}
	
	/*delete the booking in database
	 * 
	 */
	public void proxyCancelBooking(Booking booking) throws Exception {
		new BookingDao().delete(booking.getOrderId());
	
	}
	
	/*
	 * get user id and service id from bookmark object and insert into database
	 */
	public void proxyDeleteBookMark(BookMark bookmark) throws Exception {
		new BookmarkDao().delete(bookmark.getUserId(), bookmark.getServiceId());
	}

	/*
	 * get the attribute of qinfo(like type, name...) and query for result in database accordingly
	 */
	public ArrayList<Service> proxySearch(QueryInfo qinfo) throws Exception {
		// GAYATRI : assuming all qInfo fields are SQL regex 
		return new ServiceProviderInfoDao().getAllServicesQuery(qinfo.getServiceName(), qinfo.getLocation(), qinfo.getType());
	}

	/*
	 * get all rates of certain service from database
	 */
	public ArrayList<Rate> proxyDisplayRates(int serviceId) throws Exception {
		return new RatingDao().getRatings(serviceId);
	}
}
