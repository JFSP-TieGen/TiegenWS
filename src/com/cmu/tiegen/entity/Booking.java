package com.cmu.tiegen.entity;

import java.io.Serializable;
import java.util.Date;

//a booked service
public class Booking implements Serializable{	
    	private static final long serialVersionUID = 6529685098267757690L;

	private int orderId;
	private int userId;
	private int serviceId;
	private String serviceName;
	private Date date;
	private Rate rate;
	
	public Booking(int uid, int sid,Date date){
		this.userId = uid;
		this.serviceId = sid;
		this.date = date;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Rate getRate() {
		return rate;
	}
	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	

}
