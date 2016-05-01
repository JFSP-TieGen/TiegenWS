package server;

import entity.User;
import adapter.proxyUserAdapter;

public class UserBizImp extends proxyUserAdapter implements UserBiz{

	@Override
	public boolean verifyUsername(String userName) throws Exception {
     return proxyVerifyUserName(userName);
	}
	
	@Override
    public User login(User user) throws Exception {
	 return proxyLogin(user);
    }
	
	@Override
	public User signUp(User user) throws Exception {
		return proxySignUp(user);
	}
	
}
