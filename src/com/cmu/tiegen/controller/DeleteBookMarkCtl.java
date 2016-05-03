package com.cmu.tiegen.controller;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.tiegen.entity.BookMark;
import com.cmu.tiegen.server.AddBookMark;
import com.cmu.tiegen.util.InstanceFactory;


public class DeleteBookMarkCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AddBookMark servicebiz = InstanceFactory.getInstance()
			.createInstance(AddBookMark.class);
   
    public DeleteBookMarkCtl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		BookMark bookmark = null ;
		try {
			bookmark = (BookMark)in.readObject();
			in.close();
			servicebiz.deleteBookMark(bookmark);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
