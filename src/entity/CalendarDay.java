package entity;

import java.util.ArrayList;
import java.util.Date;

// user's schedule in one calendar day
public class CalendarDay {
	int userId;
	Date date;
	ArrayList<Booking> orders;
}
