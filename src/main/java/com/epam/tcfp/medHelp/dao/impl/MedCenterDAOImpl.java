package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.MedCenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.ParametersName.APPROVED;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DISAPPROVED;

public class MedCenterDAOImpl implements MedCenterDAO {
    private static final String SELECT_ALL_MED_CENTER = "SELECT * FROM MEDCENTER";
    private static final String SELECT_MED_CENTER_BY_ID = "SELECT * FROM MEDCENTER WHERE id = ?";
    private static final String UPDATE_MED_CENTER = "UPDATE medcenter SET name = ?,city_id = ?,phone = ?,address = ?,medInstitution = ?,approved = ?,exist = ? WHERE id = ?";
    private static final String DELETE_MED_CENTER = "DELETE FROM medcenter WHERE id = ?";
    private static final String APPROVE_OR_DISAPPROVE_MED_CENTER = "UPDATE medcenter SET approved = ? WHERE id = ?";
    private static final String CREATE_MED_CENTER = "INSERT INTO medcenter(name, city_id, phone, address, medInstitution, approved, exist) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_APPROVED_MED_CENTER = "SELECT * FROM MEDCENTER WHERE approved = 1 AND exist = 1";

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<MedCenter> getAll() throws SQLException {
        List<MedCenter> medCenters = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MED_CENTER)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedCenter medCenter = new MedCenter();
                setParameters(medCenter,resultSet);
                medCenters.add(medCenter);
            }

        }finally {
            connectionPool.releaseConnection(connection);
        }

        return medCenters;
    }

    @Override
    public MedCenter getByID(Long id) throws SQLException {
        MedCenter medCenter = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MED_CENTER_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                medCenter = new MedCenter();
                setParameters(medCenter,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medCenter;
    }

    @Override
    public void update(MedCenter medCenter) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MED_CENTER)){
            preparedStatement.setString(1,medCenter.getName());
            preparedStatement.setLong(2,medCenter.getCity().getId());
            preparedStatement.setString(3,medCenter.getPhone());
            preparedStatement.setString(4,medCenter.getAddress());
            preparedStatement.setString(5,medCenter.getMedInstitution());
            preparedStatement.setBoolean(6,medCenter.getApproved());
            preparedStatement.setBoolean(7,medCenter.getExist());
            preparedStatement.setLong(8,medCenter.getId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }


    @Override
    public void delete(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MED_CENTER)){
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
        try(PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_OR_DISAPPROVE_MED_CENTER)){
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
        try(PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_OR_DISAPPROVE_MED_CENTER)){
            preparedStatement.setBoolean(1,DISAPPROVED);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void create(MedCenter medCenter) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MED_CENTER)){
            preparedStatement.setString(1,medCenter.getName());
            preparedStatement.setLong(2,medCenter.getCity().getId());
            preparedStatement.setString(3,medCenter.getPhone());
            preparedStatement.setString(4,medCenter.getAddress());
            preparedStatement.setString(5,medCenter.getMedInstitution());
            preparedStatement.setBoolean(6,medCenter.getApproved());
            preparedStatement.setBoolean(7,medCenter.getExist());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<MedCenter> getApprovedMedCenters() throws SQLException {
        List<MedCenter> medCenters = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPROVED_MED_CENTER)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedCenter medCenter = new MedCenter();
                setParameters(medCenter,resultSet);
                medCenters.add(medCenter);
            }

        }finally {
            connectionPool.releaseConnection(connection);
        }

        return medCenters;
    }

    private void setParameters(MedCenter medCenter, ResultSet resultSet) throws SQLException {
        City city = cityDAO.getCityById(resultSet.getLong("CITY_ID"));;
        medCenter.setId(resultSet.getLong("ID"));
        medCenter.setName(resultSet.getString("NAME"));
        medCenter.setCity(city);
        medCenter.setPhone(resultSet.getString("PHONE"));
        medCenter.setAddress(resultSet.getString("ADDRESS"));
        medCenter.setMedInstitution(resultSet.getString("MEDINSTITUTION"));
        medCenter.setApproved(resultSet.getBoolean("APPROVED"));
        medCenter.setExist(resultSet.getBoolean("EXIST"));

    }
}
