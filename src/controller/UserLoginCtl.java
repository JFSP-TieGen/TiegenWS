package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import server.UserBiz;
import util.InstanceFactory;



public class UserLoginCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 // use instance factory to get the only instance of userbizimp class
	private UserBiz userbiz = InstanceFactory.getInstance()
				    			.createInstance(UserBiz.class);
 
    public UserLoginCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User user = (User)request.getAttribute("user");
		 ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		 
		 User user = null;
		try {
			user = (User)in.readObject();
			in.close();
			User newUser = userbiz.login(user);
			response.setContentType("application/octet-stream");
			ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
			if(newUser != null){	
	             out.writeObject(newUser);   
			}else{
				out.writeObject(null);
			}
			 out.close ();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
