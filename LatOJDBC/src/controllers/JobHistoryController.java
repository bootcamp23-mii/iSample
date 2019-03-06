/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.JobHistoryDAO;
import java.sql.Connection;

/**
 *
 * @author Panji Sadewo
 */
public class JobHistoryController {
    
    private Connection connection;
    private JobHistoryDAO jhdao;
    public JobHistoryController(Connection connection) {
        jhdao = new JobHistoryDAO(connection);
    }
    
    
    public String delete(String id){
        String result="";
        if (jhdao.delete(Integer.parseInt(id))) {
            result="Selamat data berhasil dihapus";
        }else{
            result="Maaf, data gagal dihapus";
        }
        return result;
    }
    
    
}
