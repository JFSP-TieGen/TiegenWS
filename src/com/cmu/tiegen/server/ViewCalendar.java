package com.cmu.tiegen.server;

import com.cmu.tiegen.entity.CalendarDay;



public interface ViewCalendar {
	public CalendarDay LoadCalendarDay(CalendarDay day) throws Exception;
}
