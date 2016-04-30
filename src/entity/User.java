package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	
	private int userId;
	private String userName;
	private String password;
	
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String name, String pwd){
    	this.userName = name;
    	this.password = pwd;
    }
}
