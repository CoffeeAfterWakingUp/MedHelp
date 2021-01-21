package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.entity.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements CityDAO {
    private static final String SELECT_ALL_CITY = "SELECT * FROM CITY";
    private static final String SELECT_CITY_BY_ID = "SELECT * FROM CITY WHERE ID = ?";


    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<City> getAll() throws SQLException {
        List<City> cities = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITY)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                City city = new City();
                city.setId(resultSet.getLong("ID"));
                city.setName(resultSet.getString("NAME"));
                cities.add(city);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return cities;
    }

    @Override
    public City getCityById(Long id) throws SQLException {
        City city = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                city = new City();
                city.setId(resultSet.getLong("ID"));
                city.setName(resultSet.getString("NAME"));
            }

        }finally {
            connectionPool.releaseConnection(connection);
        }
        return city;
    }
}
