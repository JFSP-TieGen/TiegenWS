package com.cmu.tiegen;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cmu.tiegen.entity.Booking;
import com.cmu.tiegen.entity.CalendarDay;
import com.cmu.tiegen.entity.QueryInfo;
import com.cmu.tiegen.entity.Rate;
import com.cmu.tiegen.entity.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.cmu.tiegen.entity.User;
import com.cmu.tiegen.server.ServiceBizImp;
import com.cmu.tiegen.server.UserBiz;
import com.cmu.tiegen.server.UserBizImp;


/**


 * @author keerthanathangaraju
 */
public class Driver {

    public static void main(String[] args) throws Exception {

           User user = new User("kt", "password");
           
           UserBiz userbiz = new UserBizImp();

           ServiceBizImp serviceBizImp = new ServiceBizImp();
            QueryInfo info = new QueryInfo();
		   info.setServiceName("%%");
		   info.setLocation("%%");
		   info.setType("%%");
           ArrayList<Service> result=serviceBizImp.search(info);
           
           
           
          
           Rate rate = new Rate(32, 6, 4, "hello");
           serviceBizImp.rateBooking(rate);
            ArrayList<Rate> displayRates = serviceBizImp.displayRates(1);
           System.out.println("Rate 1: " + displayRates.size() + ", " + displayRates.get(0).getRate());
           rate.setRate(4.5f);
           serviceBizImp.rateBooking(rate);
           displayRates = serviceBizImp.displayRates(1);
           System.out.println("Rate 2: " + displayRates.size() + ", " + displayRates.get(0).getRate());
           CalendarDay calendarDay = new CalendarDay();
           calendarDay.setUserId(1);
           
           calendarDay.setDate(Date.valueOf("2016-05-03"));
           CalendarDay d2 = serviceBizImp.proxyLoadCalendarDay(calendarDay);
           System.out.println("Calendar2: " + d2.getUserId() + ", " + d2.getOrders().size() + ", " + d2.getOrders().get(0).getServiceName());

           java.util.Date currTime = Calendar.getInstance().getTime();
           System.out.println("\nInsert Time :" + currTime);
           Booking testBooking = new Booking(1, 1, currTime); 
           serviceBizImp.proxyBookService(testBooking);

           CalendarDay testCal = new CalendarDay();
           Calendar newTime = Calendar.getInstance();
           newTime.setTimeInMillis(currTime.getTime() - 10000);
           System.out.println("\nQuery Time :" + newTime);

           testCal.setUserId(1);
           testCal.setDate(newTime.getTime());
           CalendarDay d3 = serviceBizImp.proxyLoadCalendarDay(testCal);
           System.out.println("Calendar3: " + d3.getUserId() + ", " + d3.getOrders().size() + ", " + d3.getOrders().get(0).getServiceName());
           
           user.setUserId(1);
           ArrayList<Service> loadBookMark = serviceBizImp.loadBookMark(user);
           System.out.println("  BookMark: " + loadBookMark.size() + ", " + loadBookMark.get(0).getServiceId()+ ", " + loadBookMark.get(0).getPrice());
           
        try {
           if(userbiz.verifyUsername(user.getUserName())){
        	   userbiz.signUp(user);
           }
        } catch (Exception ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
