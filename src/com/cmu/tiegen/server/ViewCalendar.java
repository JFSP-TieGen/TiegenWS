package com.cmu.tiegen.server;

import com.cmu.tiegen.entity.Booking;
import com.cmu.tiegen.entity.CalendarDay;
import java.util.ArrayList;



public interface ViewCalendar {
	public CalendarDay LoadCalendarDay(CalendarDay day) throws Exception;
        public ArrayList<Booking> getToRate(CalendarDay day) throws Exception;
}
