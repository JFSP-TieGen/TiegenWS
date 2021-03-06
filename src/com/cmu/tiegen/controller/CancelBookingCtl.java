package com.cmu.tiegen.controller;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.tiegen.server.BookService;
import com.cmu.tiegen.util.InstanceFactory;
import com.cmu.tiegen.entity.Booking;

public class CancelBookingCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 // use instance factory to get the only instance of servicebizimp class
	private BookService servicebiz = InstanceFactory.getInstance()
				.createInstance(BookService.class);
		
    public CancelBookingCtl() {
        super();
  
    }

    /*
     * we need to figure out how backend talk to front end and forward some 
     * message here to front end
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //Booking booking = (Booking) request.getAttribute("booking");
		  ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		  Booking booking = null;
	    try {
	    	booking = (Booking)in.readObject();
	    	in.close();
			servicebiz.cancelBooking(booking);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
