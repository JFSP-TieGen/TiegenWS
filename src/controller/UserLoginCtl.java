package controller;

import java.io.IOException;
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
		User user = (User)request.getAttribute("user");
		if(userbiz.login(user) != null){
			/*
			 * we need to figure out how backend talk to front end and forward some 
			 * message here to front end
			 */
		
		}else{
			/*
			 * we need to figure out how backend talk to front end and forward some 
			 * message here to front end
			 */	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
