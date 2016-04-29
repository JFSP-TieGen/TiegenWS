package adapter;

import entity.User;

public abstract class proxyUserAdapter {
	public boolean proxyVerifyUserName(String userName){
		/*
		 * here we need a database query that return if the username is existed, if it is, 
		 * the new user can't register with this name;
		 */
		return true;
	}
	public User proxyLogin(User user){
		/*
		 * query if there is a match in user table, return the user object if there is a match
		 * 
		 */
		return null;
	}
	public void proxySignUp(User user){
		/*
		 * put the new user in table
		 */
	}
	public void proxySignOut(){
		
	}
	
}
