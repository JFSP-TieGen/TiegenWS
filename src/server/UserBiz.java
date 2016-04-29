package server;

import entity.User;

public interface UserBiz {
	public boolean verifyUsername(String userName);
	public User login(User user);
	public void signUp(User user);
}
