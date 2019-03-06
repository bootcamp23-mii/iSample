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
import java.util.Scanner;
import models.Location;

/**
 *
 * @author Pandu
 */
public class LocationDAO {

    private Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * This method use to delete some row from locations table
     *
     * @param id this id use to
     * @return return value that given are statement true or false
     */
    public boolean delete(int id) {
        boolean result = false;
        String query = "DELETE FROM LOCATIONS WHERE LOCATION_ID=" + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeQuery();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method use to show some row from locations table
     *
     * @param keyword are the value of what you want to search
     * @param isGetById are the value of boolean that to separate search or
     * getByID
     * @return return that given are the series of data that get from ID
     */
    public List<Location> getData(Object keyword, boolean isGetById) {
        String query = "";
        List<Location> listRegion = new ArrayList<Location>();
        if (isGetById) {
            query = "SELECT * FROM LOCATIONS WHERE LOCATION_ID =" + keyword;
        } else {
            query = "SELECT * FROM LOCATIONS WHERE LOCATION_ID LIKE '%" + keyword + "%' OR STREET_ADDRESS LIKE '%" + keyword + "%'";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listRegion.add(new Location(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRegion;
    }

    /**
     * This method use to save or insert some row to locations table
     *
     * @param l are the value of what you want to input
     * @param isInsert are the value of boolean that to separate insert and
     * update
     * @return
     */
    public boolean save(Location l, boolean isInsert) {
        boolean result = false;
        String query = "";
        if (isInsert) {

            query = "INSERT INTO LOCATIONS(street_address, postal_code, city, state_province, country_id, location_id)"
                    + "VALUES (?,?,?,?,?,?)";
        } else {
            query = "UPDATE LOCATIONS SET STREET_ADDRESS = ?, POSTAL_CODE = ?, CITY = ?, STATE_PROVINCE = ?, COUNTRY_ID =?, LOCATION_ID = ?  WHERE LOCATION_ID = ?";

        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, l.getPostal());
            preparedStatement.setString(3, l.getCity());
            preparedStatement.setString(4, l.getProvince());
            preparedStatement.setString(5, l.getCountry());
            preparedStatement.setInt(6, l.getId());
            preparedStatement.executeQuery();
            result = true;

            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
