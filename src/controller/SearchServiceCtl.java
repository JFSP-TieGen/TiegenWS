package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.BookService;
import util.InstanceFactory;
import entity.QueryInfo;
import entity.Service;


public class SearchServiceCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	 // use instance factory to get the only instance of userbizimp class
	private BookService servicebiz = InstanceFactory.getInstance()
			.createInstance(BookService.class);
	
    public SearchServiceCtl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 QueryInfo info = (QueryInfo) request.getAttribute("qinfo");
		 ArrayList<Service> result = servicebiz.search(info);
			/*
			 * we need to figure out how backend talk to front end and forward some 
			 * message here to front end
			 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}