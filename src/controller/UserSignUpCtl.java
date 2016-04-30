package controller;

import com.sun.media.jfxmedia.logging.Logger;
import db.Db;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.UserBiz;
import util.InstanceFactory;
import entity.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;


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
            ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		//call the biz layer functions
            User user = null ;
		try {
                                user = (User) in.readObject();
                                in.close();
//                    if(userbiz.verifyUsername(user.getUserName())){
				/*
				 * we need to figure out how backend talk to front end and forward some 
				 * message here to front end
				 */
//			}else{
				userbiz.signUp(user);
                                response.setContentType ("application/octet-stream");
                                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                                out.writeObject(user);
                                out.close ();
				/*
				 * we need to figure out how backend talk to front end and forward some 
				 * message here to front end
				 */
//			}
		} catch (Exception e) {
                    if(user.getUserName().equals("g3"))
			throw new IOException(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
