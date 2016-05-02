package com.cmu.tiegen.server;

import com.cmu.tiegen.entity.User;
import com.cmu.tiegen.adapter.proxyUserAdapter;

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
