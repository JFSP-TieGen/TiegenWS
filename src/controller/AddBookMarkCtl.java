package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.AddBookMark;
import util.InstanceFactory;
import entity.BookMark;


public class AddBookMarkCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 // use instance factory to get the only instance of servicebizimp class
	private AddBookMark servicebiz = InstanceFactory.getInstance()
			.createInstance(AddBookMark.class);
	
    public AddBookMarkCtl() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookMark bookmark = (BookMark) request.getAttribute("bookmark");
		servicebiz.addBookMark(bookmark);
		
		/*
    	 * we need to figure out how backend talk to front end and forward some 
    	 * message here to front end
    	*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
