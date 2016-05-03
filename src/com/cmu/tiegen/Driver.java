package com.cmu.tiegen;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.cmu.tiegen.entity.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.cmu.tiegen.server.UserBiz;
import com.cmu.tiegen.server.UserBizImp;


/**
 *
 * @author keerthanathangaraju
 */
public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

           User user = new User("kt", "password");
           UserBiz userbiz = new UserBizImp();
        try {
           if(userbiz.verifyUsername(user.getUserName())){
        	   userbiz.signUp(user);
           }
        } catch (Exception ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
