package com.cmu.tiegen.adapter;

import com.cmu.tiegen.db.UserInfoDao;
import com.cmu.tiegen.entity.User;

public abstract class proxyUserAdapter {
    
	/*
	 * here we need a database query that return if the username is existed, if it is, 
	 * the new user can't register with this name;
	 */
	public boolean proxyVerifyUserName(String userName) throws Exception {
		int userId = new UserInfoDao().getUserId(userName);
		return (userId < 0);
	}

	/*
	 * query if there is a match in user table, return the user object if there is a match
	 * 
	 */
	public User proxyLogin(User user) throws Exception {
		return new UserInfoDao().loginUser(user.getUserName(), user.getPassword());
	}

	/*
	 * put the new user in table
	 */
	public User proxySignUp(User user) throws Exception {
		UserInfoDao dao = new UserInfoDao();
		dao.create(user.getUserName(), user.getPassword());
		return dao.loginUser(user.getUserName(), user.getPassword());
	}

	public void proxySignOut(){
		// Nothing doing
	}
	
}
