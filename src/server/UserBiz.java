package server;

import entity.User;

public interface UserBiz {
	public boolean verifyUsername(String userName) throws Exception;
	public User login(User user) throws Exception;
	public void signUp(User user) throws Exception;
}