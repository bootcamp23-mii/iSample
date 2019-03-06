/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;
import tools.BCrypt;

/**
 *
 * @author FES
 */
public class UserDAO {
    private Connection connection;
    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<User> login(User user){
        List<User> listUser = new ArrayList<User>();
        String query = "SELECT * FROM USERS WHERE USERNAME = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUser.add(new User(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }
    
    public List<User> getData(Object keyword, boolean isGetById) {
        List<User> listUser = new ArrayList<User>();
        String query = "";
        if (isGetById) {
            query = "SELECT * FROM USERS WHERE USERNAME = '" + keyword+"' ORDER BY 1";
        } else {
            query = "SELECT * FROM USERS WHERE USERNAME like '%" + keyword +
                    "%' or PASSWORD like '%" + keyword + "%'";
        }
        try {
            PreparedStatement prepareStatment = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatment.executeQuery();
            while (resultSet.next()) {
                listUser.add(new User(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }
    
    
    public boolean save(User u, boolean isInsert) {
        boolean result = false;
        String query = "";
        if (isInsert) {
            query = "INSERT INTO USERS (USERNAME,PASSWORD) VALUES ('"+u.getUsername()+
                    "','"+ BCrypt.hashpw(u.getPassword(), BCrypt.gensalt())+"')";
        } else {
            query = "UPDATE USERS SET PASSWORD='"+BCrypt.hashpw(u.getPassword(), BCrypt.gensalt())+"' WHERE USERNAME='"+u.getUsername()+"'";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeQuery();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public boolean delete(String username){
        boolean result = false;
        String query = "DELETE FROM USERS WHERE USERNAME = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);            
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = true;
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        return result;
    }
}
