/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import db.UserInfoDao;
import java.io.IOException;
import java.sql.SQLException;


/**
 *
 * @author keerthanathangaraju
 */
public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
           new UserInfoDao().create("kt", "password");

    }

}
