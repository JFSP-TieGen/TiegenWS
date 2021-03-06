package com.cmu.tiegen.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.tiegen.server.BookService;
import com.cmu.tiegen.util.InstanceFactory;
import com.cmu.tiegen.entity.QueryInfo;
import com.cmu.tiegen.entity.Service;


public class SearchServiceCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	 // use instance factory to get the only instance of userbizimp class
	private BookService servicebiz = InstanceFactory.getInstance()
			.createInstance(BookService.class);
	
    public SearchServiceCtl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// QueryInfo info = (QueryInfo) request.getAttribute("qinfo");
		ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		QueryInfo info = null;
		 try {
			 info = (QueryInfo)in.readObject();
			 in.close();
			 if(info.getServiceName()==null){
				 info.setServiceName("%%");
			 }
			 if(info.getLocation()==null){
				 info.setLocation("%%");
			 }
			 if(info.getType()==null){
				 info.setType("%%");
			 }
			 ArrayList<Service> result = servicebiz.search(info);
			 response.setContentType("application/octet-stream");
			 ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
             out.writeObject(result);
             out.close ();
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
