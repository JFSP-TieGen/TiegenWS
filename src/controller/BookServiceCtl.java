package controller;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Booking;
import server.BookService;
import util.InstanceFactory;

/**
 * Servlet implementation class BookService
 */

public class BookServiceCtl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	 // use instance factory to get the only instance of userbizimp class
	private BookService servicebiz = InstanceFactory.getInstance()
			.createInstance(BookService.class);
		
    public BookServiceCtl() {
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
			servicebiz.bookService(booking);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
