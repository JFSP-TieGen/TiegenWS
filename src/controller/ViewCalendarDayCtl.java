package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.BookService;
import server.ViewCalendar;
import util.InstanceFactory;
import entity.Booking;
import entity.CalendarDay;
import entity.User;


public class ViewCalendarDayCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 // use instance factory to get the only instance of userbizimp class
		private ViewCalendar servicebiz = InstanceFactory.getInstance()
				.createInstance(ViewCalendar.class);
  
    public ViewCalendarDayCtl() {
        super();
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         CalendarDay day = (CalendarDay) request.getAttribute("CalendarDay");
         CalendarDay detailedDay = servicebiz.LoadCalendarDay(day);
         /*
			 * we need to figure out how backend talk to front end and forward some 
			 * message here to front end
			 */
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doGet(request, response);
	}

}
