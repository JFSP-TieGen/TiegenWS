package com.cmu.tiegen.remote;

import com.cmu.tiegen.entity.BookMark;
import com.cmu.tiegen.entity.Booking;
import com.cmu.tiegen.entity.QueryInfo;
import com.cmu.tiegen.entity.Rate;
import com.cmu.tiegen.entity.Service;
import com.cmu.tiegen.entity.User;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import com.cmu.tiegen.util.Constants;

public class ServerConnector {
	public Object sendRequest(String urlS, Object input) {
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(urlS);
			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setDoOutput(true);
			ObjectOutputStream out = new ObjectOutputStream(
					urlConnection.getOutputStream());
			out.writeObject(input);
			out.close();
			InputStream is = urlConnection.getInputStream();
			if (is.available() != 0) {
				ObjectInputStream in = new ObjectInputStream(is);
				Object output = in.readObject();
				in.close();
				return output;
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally {
			urlConnection.disconnect();
		}
		return null;
	}

	public static void main(String args[]) {
		ServerConnector conn = new ServerConnector();

		/*
		 * test case 1:User sign up
		 */
		User user = new User("kt", "password");
		User userSignUp = (User) conn.sendRequest(
				Constants.URL.concat(Constants.URL_SIGN_UP), user);
		if (userSignUp != null) {
			System.out.println("new user sign up: " + userSignUp.getUserId()
					+ " " + userSignUp.getUserName());
		} else {
			System.out.println("user name exists!");
		}

		/*
		 * test case 2:User Log in*******************
		 */
		User userLogin = (User) conn.sendRequest(
				Constants.URL.concat(Constants.URL_LOGIN), user);
		System.out.println("user Login : " + userLogin.getUserId() + " "
				+ userLogin.getUserName());

		/*
		 * test case 3:Add Bookmark
		 */
		BookMark bookmark = new BookMark(1, 1);
		conn.sendRequest(Constants.URL.concat(Constants.URL_ADD_BOOKMARK),
				bookmark);

		/***********************problem here!!! 
		 * test case 4:Load all Bookmarks For Gayatri: when try
		 * to get all bookmarks for user whose userid = 1, please help check the
		 * db code
		 */

		ArrayList<Service> list = (ArrayList<Service>) conn.sendRequest(
				Constants.URL.concat(Constants.URL_DISPLAY_BOOKMARKS),
				userLogin);
		System.out.println("total bookmark records : " + list.size()
				+ " for userId: " + userLogin.getUserId());

		/*
		 * test case 5:delete Bookmark
		 */

		conn.sendRequest(Constants.URL.concat(Constants.URL_DELETE_BOOKMARK),
				bookmark);

		/*
		 * test case 6:book service
		 */
		Booking booking = new Booking(1, 2, new Date());
		conn.sendRequest(Constants.URL.concat(Constants.URL_BOOK_SERVICE),
				booking);

		
		/***********************problem here!!! 
		 * test case 7:display calendarday****** db function
		 * need to be implemented
		 */
		// Backend function need to be implemented

		
		
		/*
		 * test case 8:cancel booking
		 */
		// booking.setOrderId(1);
		conn.sendRequest(Constants.URL.concat(Constants.URL_CANCEL_BOOKING),
				booking);

		/************************ problem here!! 
		 * test case 9:rate booking & edit rate db function not
		 * completed, keep throwing bugs when update rate and insert new rate
		 * error msg: Unknown column 'rate' in 'field list'
		 */
		// Rate rate = new Rate(1,1,3,"hello");
		// conn.sendRequest(Constants.URL.concat(Constants.URL_RATE_BOOKING),rate);

		
		
		/*************************problem here!!
		 *  test case 10: view all Rates same error msg with above
		 * case, maybe some bug with the Rate table?
		 */
		// int sid = 1;
		// ArrayList<Rate> rates = (ArrayList<Rate>)
		// conn.sendRequest(Constants.URL.concat(Constants.URL_SHOW_ALL_RATES),new
		// Integer(sid));
		// System.out.print(rates);

		/**************************problem here!!
		 * test case 11: search service
		 * 
		 * search function works only when all the query attributes are filled, 
		 * if some attributes are null, failed to return any result, maybe need to modify the sql statement?
		 */
		   QueryInfo info = new QueryInfo();
		   info.setServiceName("%x%");
		   info.setLocation("%%");
		   info.setType("%%");
		   ArrayList<Service> res =  (ArrayList<Service>) conn.sendRequest(Constants.URL.concat(Constants.URL_SEARCH_SERVICE),info);
		   System.out.print("total records for query is:"+res.size());
	}
	
	
}