package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.AddBookMark;
import util.InstanceFactory;
import entity.BookMark;
import entity.Service;
import entity.User;


public class ViewBookMarkCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	 // use instance factory to get the only instance of servicebizimp class
		private AddBookMark servicebiz = InstanceFactory.getInstance()
				.createInstance(AddBookMark.class);
		
    public ViewBookMarkCtl() {
        super();
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       User user = (User) request.getAttribute("user");
       try {
		ArrayList<Service> result = servicebiz.loadBookMark(user);
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
