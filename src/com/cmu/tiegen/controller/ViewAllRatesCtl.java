package com.cmu.tiegen.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.tiegen.entity.Rate;
import com.cmu.tiegen.server.RateBooking;
import com.cmu.tiegen.util.InstanceFactory;

public class ViewAllRatesCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 // use instance factory to get the only instance of servicebizimp class
		private RateBooking servicebiz = InstanceFactory.getInstance()
				.createInstance(RateBooking.class);
		
    public ViewAllRatesCtl() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int serviceId = (Integer)request.getAttribute("serviceId");
		 ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		
		try {
			int serviceId = (Integer)in.readObject();
			in.close();
			ArrayList<Rate> results = servicebiz.displayRates(serviceId);
			response.setContentType("application/octet-stream");
			ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
			out.writeObject(results);   
			out.close();
		} catch (Exception e) {
			throw new IOException(e);
		}
		  /*
	   	 * we need to figure out how backend talk to front end and forward some 
	   	 * message here to front end
	   	*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    this.doGet(request, response);
	}

}
