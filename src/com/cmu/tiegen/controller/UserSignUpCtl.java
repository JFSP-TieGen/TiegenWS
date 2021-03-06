package com.cmu.tiegen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cmu.tiegen.server.UserBiz;
import com.cmu.tiegen.util.InstanceFactory;
import com.cmu.tiegen.entity.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserSignUpCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// use instance factory to get the only instance of servicebizimp class
	private UserBiz userbiz = InstanceFactory.getInstance().createInstance(
			UserBiz.class);

	public UserSignUpCtl() {
		super();
	}

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		// get the username and password from front end
		System.out.println("test request: "+request);
		ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		// call the biz layer functions
		User user = null;
		try {
			
			user = (User) in.readObject();
			
			in.close();
			//ObjectOutputStream out = new ObjectOutputStream(
				//	response.getOutputStream());
			if (!userbiz.verifyUsername(user.getUserName())) {
//				System.out.print("signup controller username exist!");
				response.setContentType("application/octet-stream");
				ObjectOutputStream out = new ObjectOutputStream(
						response.getOutputStream());
				out.writeObject(null);
				out.close();
			} else {
				User newUser = userbiz.signUp(user);
				response.setContentType("application/octet-stream");
				ObjectOutputStream out = new ObjectOutputStream(
						response.getOutputStream());
				out.writeObject(newUser);
				out.close();

			}
		} catch (Exception e) {
//			if (user.getUserName().equals("g3"))
				throw new IOException(e);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
