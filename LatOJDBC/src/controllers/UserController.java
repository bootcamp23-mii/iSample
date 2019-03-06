/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import java.sql.Connection;
import java.util.List;
import models.User;
import tools.BCrypt;

/**
 *
 * @author FES
 */
public class UserController {
    private Connection connection;
    private UserDAO udao;

    public UserController(Connection connection) {
        udao = new UserDAO(connection);
    }
    
    public boolean login(String username, String password){
        List<User> list = udao.login(new User(username, password));
        if (!list.isEmpty()) 
            for (User user : list) 
                if (BCrypt.checkpw(password, user.getPassword())) 
                    return true;
        return false;
    }
    
}
