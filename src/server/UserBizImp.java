package server;

import entity.User;
import adapter.proxyUserAdapter;

public class UserBizImp extends proxyUserAdapter implements UserBiz{

	@Override
	public boolean verifyUsername(String userName) {
     return proxyVerifyUserName(userName);
	}
	
	@Override
    public User login(User user){
	 return proxyLogin(user);
    }
	
	@Override
	public void signUp(User user){
		proxySignUp(user);
	}
	
}
