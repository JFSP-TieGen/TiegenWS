package com.cmu.tiegen.server;

import java.util.ArrayList;

import com.cmu.tiegen.entity.BookMark;
import com.cmu.tiegen.entity.Booking;
import com.cmu.tiegen.entity.CalendarDay;
import com.cmu.tiegen.entity.QueryInfo;
import com.cmu.tiegen.entity.Rate;
import com.cmu.tiegen.entity.Service;
import com.cmu.tiegen.entity.User;
import com.cmu.tiegen.adapter.proxyServiceAdapter;

public class ServiceBizImp extends proxyServiceAdapter implements AddBookMark,BookService,RateBooking,ViewCalendar{

	@Override
	public CalendarDay LoadCalendarDay(CalendarDay day) {
		return proxyLoadCalendarDay(day);
	}

	@Override
	public ArrayList<Rate> displayRates(int ServiceId) throws Exception {
		return proxyDisplayRates(ServiceId);
	}

	@Override
	public ArrayList<Service> loadBookMark(User user) throws Exception {
		return proxyLoadBookMark(user.getUserId());
	}

	@Override
	public void deleteBookMark(BookMark bookmark) throws Exception{
		proxyDeleteBookMark(bookmark);
	}
	
	@Override
	public void bookService(Booking booking) throws Exception{
		proxyBookService(booking);
	}

	@Override
	public void rateBooking(Rate rate) throws Exception{
		proxyRateBooking(rate);
	}

	@Override
	public void editRate(Rate rate) throws Exception {
		proxyEditRate(rate);
	}


	@Override
	public void cancelBooking(Booking booking) throws Exception {
		proxyCancelBooking(booking);
	}

	@Override
	public ArrayList<Service> search(QueryInfo qinfo) throws Exception {
		return proxySearch(qinfo);
	}

	@Override
	public void addBookMark(BookMark bookmark) throws Exception{
		proxyAddBookMark(bookmark);
	}

}
