package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.UserBiz;
import util.InstanceFactory;
import entity.User;


public class UserSignUpCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // use instance factory to get the only instance of servicebizimp class
	private UserBiz userbiz = InstanceFactory.getInstance()
			    			.createInstance(UserBiz.class);
	
    public UserSignUpCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//get the username and password from front end
        User user = (User) request.getAttribute("user");
		//call the biz layer functions
		if(userbiz.verifyUsername(user.getUserName())){
			/*
			 * we need to figure out how backend talk to front end and forward some 
			 * message here to front end
			 */
		}else{
			userbiz.signUp(user);
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
