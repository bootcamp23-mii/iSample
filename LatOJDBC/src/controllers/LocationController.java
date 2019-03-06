/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.LocationDAO;
import java.sql.Connection;
import java.util.List;
import models.Location;

/**
 *
 * @author Pandu
 */
public class LocationController {

    private Connection connection;
    private LocationDAO ldao;

    public LocationController(Connection connection) {
        ldao = new LocationDAO(connection);
    }

    public String insert(String id, String address, String postal, String city, String province, String country) {
        String result = "";
        if (ldao.save(new Location(Integer.parseInt(id), address, postal, city, province, country), true)) {

            result = "YEAY, your new data inserted into table";
        } else {
            result = "OUCH";

//        } else {
//            result = "Ouch, Sorry you might miss something";
        }
        return result;
    }

    public String update(String id, String address, String postal, String city, String province, String country) {
        String result = "";
        if (ldao.save(new Location(Integer.parseInt(id), address, postal, city, province, country), false)) {
            result = "Data Changes Complete";
        } else {
            result = "Ouch, Sorry you might miss something";
        }
        return result;
    }

    public List<Location> getAll(String keyword) {
        return ldao.getData(keyword, false);
    }

    public List<Location> getById(String keyword) {
        return ldao.getData(keyword, true);
    }

    public String delete(String id) {
        String result = "";
        if (ldao.delete(Integer.parseInt(id))) {
            result = "Data have been deleted";
        } else {
            result = "Ouch, Something Missing";
        }
        return result;
    }
}
