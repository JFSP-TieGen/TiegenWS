package server;

import java.util.ArrayList;
import java.util.Date;

import entity.BookMark;
import entity.Booking;
import entity.CalendarDay;
import entity.QueryInfo;
import entity.Rate;
import entity.Service;
import entity.User;
import adapter.proxyServiceAdapter;

public class ServiceBizImp extends proxyServiceAdapter implements AddBookMark,BookService,RateBooking,ViewCalendar{

	@Override
	public CalendarDay LoadCalendarDay(CalendarDay day) {
		return proxyLoadCalendarDay(day);
	}

	@Override
	public ArrayList<Rate> displayRates(int ServiceId) {
		return proxyDisplayRates(ServiceId);
	}

	@Override
	public ArrayList<Service> loadBookMark(User user) {
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
	public void rateBooking(Rate rate) {
		proxyRateBooking(rate);
	}

	@Override
	public void editRate(Rate rate) {
		proxyEditRate(rate);
	}


	@Override
	public void cancelBooking(Booking booking) {
		proxyCancelBooking(booking);
	}

	@Override
	public ArrayList<Service> search(QueryInfo qinfo) {
		return proxySearch(qinfo);
	}

	@Override
	public void addBookMark(BookMark bookmark) throws Exception{
		proxyAddBookMark(bookmark);
	}

}
