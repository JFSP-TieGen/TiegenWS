package com.cmu.tiegen.adapter;

import java.util.ArrayList;
import java.util.Date;

import com.cmu.tiegen.entity.BookMark;
import com.cmu.tiegen.entity.CalendarDay;
import com.cmu.tiegen.entity.Booking;
import com.cmu.tiegen.entity.QueryInfo;
import com.cmu.tiegen.entity.Rate;
import com.cmu.tiegen.entity.Service;
import com.cmu.tiegen.entity.User;
import com.cmu.tiegen.db.BookingDao;
import com.cmu.tiegen.db.BookmarkDao;
import com.cmu.tiegen.db.RatingDao;
import com.cmu.tiegen.db.ServiceProviderInfoDao;

public abstract class proxyServiceAdapter {
	
	//book service 
	/*
	 * insert this booking record in database
	 */
	public void proxyBookService(Booking booking) throws Exception{
        BookingDao bookingdao = new BookingDao();
        if(bookingdao.getBookingId(booking.getUserId(),booking.getDate(),booking.getServiceId())<0){
        	new BookingDao().create(booking.getUserId(), booking.getServiceId(), booking.getDate());
        }
		
	}
	
	/*
	 * get user id and service id from bookmark object and insert into database
	 */
	public void proxyAddBookMark(BookMark bookmark) throws Exception{
		BookmarkDao bookmarkDao = new BookmarkDao();
		if(bookmarkDao.getBookMarkId(bookmark.getUserId(),bookmark.getServiceId())<0){
	    bookmarkDao.create(bookmark.getUserId(), bookmark.getServiceId());
		}
	}
	
	public CalendarDay proxyLoadCalendarDay(CalendarDay day) throws Exception {
		/*
		 * get the user id and date attribute from the calendarDay object and put all the bookings that match the criteria into an arraylist
		 *  and encapsulate this arraylist in an calendarday object
		 */
		ArrayList<Booking> allBookingsByDate = new BookingDao().getAllBookingsByDate(day.getUserId(), day.getDate());
		day.setOrders(allBookingsByDate);
		return day;
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
	
		try {
			float score = rate.getRate();
			int serviceId = new BookingDao().getServiceId(rate.getUserId(), rate.getOrderId());
			int countRating = new RatingDao().countRating(serviceId);
			float avgRating = new RatingDao().avgRating(serviceId);
			float newAvg = (float)(((float)(countRating * avgRating) + score)/ countRating + 1);
			new ServiceProviderInfoDao().updateRating(serviceId, newAvg);
			new RatingDao().create(rate.getUserId(), serviceId, rate.getOrderId(), rate.getRate(), rate.getReview());			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
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
		// for eg, if serviceName == nul, the value pass should be '%%'
		return new ServiceProviderInfoDao().getAllServicesQuery(qinfo.getServiceName(), qinfo.getLocation(), qinfo.getType());
	}

	/*
	 * get all rates of certain service from database
	 */
	public ArrayList<Rate> proxyDisplayRates(int serviceId) throws Exception {
		return new RatingDao().getRatings(serviceId);
	}
}
