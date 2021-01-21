package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineFromDAO;
import com.epam.tcfp.medHelp.entity.MedicineFrom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineFromDAOImpl implements MedicineFromDAO {
    private static final String SELECT_MEDICINE_FROM_BY_ID = "SELECT * FROM MEDICINE_FROM WHERE ID = ?";
    private static final String SELECT_ALL_MEDICINE_FROM = "SELECT * FROM MEDICINE_FROM";


    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<MedicineFrom> getAll() throws SQLException {
        List<MedicineFrom> medicineFromGroup = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINE_FROM)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedicineFrom medicineFrom = new MedicineFrom();
                setMedicineFromParameters(medicineFrom,resultSet);
                medicineFromGroup.add(medicineFrom);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineFromGroup;
    }

    @Override
    public MedicineFrom getMedicineFromById(Long id) throws SQLException {
        MedicineFrom medicineFrom = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_FROM_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                medicineFrom = new MedicineFrom();
                setMedicineFromParameters(medicineFrom,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineFrom;
    }

    private void setMedicineFromParameters(MedicineFrom medicineFrom,ResultSet resultSet) throws SQLException{
        medicineFrom.setId(resultSet.getLong("ID"));
        medicineFrom.setName(resultSet.getString("NAME"));
    }
}














