package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineForDAO;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.MedicineFor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineForDAOImpl implements MedicineForDAO {
    private static final String SELECT_MEDICINE_FOR_BY_ID = "SELECT * FROM MEDICINE_FOR WHERE ID = ?";
    private static final String SELECT_ALL_MEDICINE_FOR = "SELECT * FROM medicine_for";


    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<MedicineFor> getAll() throws SQLException {
        List<MedicineFor> medicineForGroup = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINE_FOR)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedicineFor medicineFor = new MedicineFor();
                setMedicineForParameters(medicineFor,resultSet);
                medicineForGroup.add(medicineFor);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineForGroup;
    }

    @Override
    public MedicineFor getMedicineForById(Long id) throws SQLException {
        MedicineFor medicineFor = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_FOR_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                medicineFor = new MedicineFor();
                setMedicineForParameters(medicineFor,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineFor;
    }


    private void setMedicineForParameters(MedicineFor medicineFor,ResultSet resultSet) throws SQLException{
        medicineFor.setId(resultSet.getLong("ID"));
        medicineFor.setName(resultSet.getString("NAME"));
    }




}









