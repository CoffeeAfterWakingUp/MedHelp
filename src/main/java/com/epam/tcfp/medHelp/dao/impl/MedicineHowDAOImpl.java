package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineHowDAO;
import com.epam.tcfp.medHelp.entity.MedicineHow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineHowDAOImpl implements MedicineHowDAO {
    private static final String SELECT_MEDICINE_HOW_BY_ID = "SELECT * FROM MEDICINE_HOW WHERE ID = ?";
    private static final String SELECT_ALL_MEDICINE_HOW = "SELECT * FROM MEDICINE_HOW";

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<MedicineHow> getAll() throws SQLException {
        List<MedicineHow> medicineHowGroup = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINE_HOW)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedicineHow medicineHow = new MedicineHow();
                setMedicineHowParameters(medicineHow,resultSet);
                medicineHowGroup.add(medicineHow);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineHowGroup;
    }

    @Override
    public MedicineHow getMedicineHowById(Long id) throws SQLException {
        MedicineHow medicineHow = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_HOW_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                medicineHow = new MedicineHow();
                setMedicineHowParameters(medicineHow,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineHow;
    }

    private void setMedicineHowParameters(MedicineHow medicineHow,ResultSet resultSet) throws SQLException{
        medicineHow.setId(resultSet.getLong("ID"));
        medicineHow.setName(resultSet.getString("NAME"));
    }
}
