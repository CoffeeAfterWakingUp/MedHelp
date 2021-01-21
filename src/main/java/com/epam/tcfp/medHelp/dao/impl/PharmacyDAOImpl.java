package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.dao.interfaces.PharmacyDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.ParametersName.APPROVED;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DISAPPROVED;

public class PharmacyDAOImpl implements PharmacyDAO {
    private static final String SELECT_ALL_PHARMACY = "SELECT * FROM PHARMACY";
    private static final String SELECT_PHARMACY_BY_CITY_ID = "SELECT * FROM PHARMACY WHERE CITY_ID = ? AND approved = 1";
    private static final String SELECT_PHARMACY_BY_ID = "SELECT * FROM PHARMACY WHERE ID = ?";
    private static final String UPDATE_PHARMACY = "UPDATE pharmacy SET name = ?,city_id = ?,address = ?,phone = ?,approved = ?,exist = ? WHERE id = ?";
    private static final String CREATE_PHARMACY = "INSERT INTO pharmacy(name, city_id, address, phone, approved, exist) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_PHARMACY = "DELETE FROM pharmacy WHERE id = ?";
    private static final String APPROVE_OR_DISAPPROVE_PHARMACY = "UPDATE pharmacy SET approved = ? WHERE id = ?";
    private static final String SELECT_APPROVED_PHARMACY = "SELECT * FROM PHARMACY WHERE approved = 1";

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");


    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<Pharmacy> getAll() throws SQLException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PHARMACY)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Pharmacy pharmacy = new Pharmacy();
                setPharmacyParameters(pharmacy,resultSet);
                pharmacies.add(pharmacy);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return pharmacies;
    }

    @Override
    public List<Pharmacy> getPharmacyByCityId(Long id) throws SQLException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHARMACY_BY_CITY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Pharmacy pharmacy = new Pharmacy();
                setPharmacyParameters(pharmacy,resultSet);
                pharmacies.add(pharmacy);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return pharmacies;
    }

    @Override
    public Pharmacy getPharmacyById(Long id) throws SQLException {
        Pharmacy pharmacy = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHARMACY_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                pharmacy = new Pharmacy();
                setPharmacyParameters(pharmacy,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return pharmacy;
    }

    @Override
    public void update(Pharmacy pharmacy) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHARMACY)){
            preparedStatement.setString(1,pharmacy.getName());
            preparedStatement.setLong(2,pharmacy.getCity().getId());
            preparedStatement.setString(3,pharmacy.getAddress());
            preparedStatement.setString(4,pharmacy.getPhone());
            preparedStatement.setBoolean(5,pharmacy.getApproved());
            preparedStatement.setBoolean(6,pharmacy.getExist());
            preparedStatement.setLong(7,pharmacy.getId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void create(Pharmacy pharmacy) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PHARMACY)){
            preparedStatement.setString(1,pharmacy.getName());
            preparedStatement.setLong(2,pharmacy.getCity().getId());
            preparedStatement.setString(3,pharmacy.getAddress());
            preparedStatement.setString(4,pharmacy.getPhone());
            preparedStatement.setBoolean(5,pharmacy.getApproved());
            preparedStatement.setBoolean(6,pharmacy.getExist());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PHARMACY)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void approve(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_OR_DISAPPROVE_PHARMACY)){
            preparedStatement.setBoolean(1,APPROVED);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void disapprove(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_OR_DISAPPROVE_PHARMACY)){
            preparedStatement.setBoolean(1,DISAPPROVED);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Pharmacy> getApprovedPharmacies() throws SQLException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPROVED_PHARMACY)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Pharmacy pharmacy = new Pharmacy();
                setPharmacyParameters(pharmacy,resultSet);
                pharmacies.add(pharmacy);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return pharmacies;
    }

    private void setPharmacyParameters(Pharmacy pharmacy, ResultSet resultSet) throws SQLException{
        City city;
        pharmacy.setId(resultSet.getLong("ID"));
        pharmacy.setName(resultSet.getString("NAME"));
        city = cityDAO.getCityById(resultSet.getLong("CITY_ID"));
        pharmacy.setCity(city);
        pharmacy.setAddress(resultSet.getString("ADDRESS"));
        pharmacy.setPhone(resultSet.getString("PHONE"));
        pharmacy.setApproved(resultSet.getBoolean("APPROVED"));
        pharmacy.setExist(resultSet.getBoolean("EXIST"));
    }
}
