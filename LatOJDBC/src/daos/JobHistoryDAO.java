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
import models.JobHistory;

/**
 *
 * @author Panji Sadewo
 */
public class JobHistoryDAO {
    private Connection connection;
    
    public JobHistoryDAO (Connection connection) {
        this.connection = connection;
    }
    
    public List<JobHistory> getData(Object keyword, boolean isGetById) {
        List<JobHistory> listJobHistory = new ArrayList<JobHistory>();
        String query = "";
        if (isGetById) {
            query = "SELECT * FROM job_history WHERE employee_id = " + keyword;
        } else {
            query = "SELECT * FROM job_history WHERE employee_id like '%" + keyword +
                    "%' or start_date like '%" + keyword + "%' or end_date like '%" + keyword + 
                    "%' or job_id like '%" + keyword + "%' or department_id like '%" + keyword +"%'";
        }
        try {
            PreparedStatement prepareStatment = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatment.executeQuery();
            while (resultSet.next()) {
                listJobHistory.add(new JobHistory(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listJobHistory;
    }
    
    public boolean delete(int id){
        boolean result = false;
        String query = "DELETE FROM job_history WHERE EMPLOYEE_ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);            
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = true;
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        return result;
    }
        
    
}
