package com.cmu.tiegen.controller;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.tiegen.entity.Rate;
import com.cmu.tiegen.server.AddBookMark;
import com.cmu.tiegen.server.RateBooking;
import com.cmu.tiegen.util.InstanceFactory;

public class RateBookingCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 // use instance factory to get the only instance of servicebizimp class
		private RateBooking servicebiz = InstanceFactory.getInstance()
				.createInstance(RateBooking.class);
    public RateBookingCtl() {
        super();
      
    }

	
    /*
     * we need to figure out how backend talk to front end and forward some 
     * message here to front end
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Rate rate = (Rate) request.getAttribute("rate");
    	ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		Rate rate = null;
    	try {
    		rate = (Rate)in.readObject();
    		in.close();
    		servicebiz.rateBooking(rate);
    	} catch (Exception e) {
    		throw new IOException(e);
    	}
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
