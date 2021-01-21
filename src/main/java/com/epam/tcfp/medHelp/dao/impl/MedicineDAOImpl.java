package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.*;
import com.epam.tcfp.medHelp.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {
    private static final String SELECT_MEDICINE_BY_ID = "SELECT * FROM medicine WHERE id = ?";
    private static final String SELECT_ALL_MEDICINE = "SELECT * FROM medicine";
    private static final String SELECT_MEDICINE_BY_MEDICINE_FOR_ID = "SELECT * FROM medicine WHERE medicine_for_id = ?";
    private static final String SELECT_MEDICINE_BY_MEDICINE_FROM_ID = "SELECT * FROM medicine WHERE  medicine_from_id = ?";
    private static final String SELECT_MEDICINE_BY_MEDICINE_HOW_ID = "SELECT * FROM medicine WHERE  medicine_how_id = ?";


    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineForDAO medicineForDAO = (MedicineForDAOImpl) daoFactory.getDAO("MEDICINE_FOR_DAO");
    private MedicineFromDAO medicineFromDAO = (MedicineFromDAOImpl) daoFactory.getDAO("MEDICINE_FROM_DAO");
    private MedicineHowDAO medicineHowDAO = (MedicineHowDAOImpl) daoFactory.getDAO("MEDICINE_HOW_DAO");

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<Medicine> getAll() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINE)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Medicine medicine = new Medicine();
                setMedicineParameters(medicine,resultSet);
                medicines.add(medicine);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicines;
    }


    @Override
    public Medicine getMedicineById(Long id) throws SQLException {
        Medicine medicine = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                medicine = new Medicine();
                setMedicineParameters(medicine,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicine;
    }

    @Override
    public List<Medicine> getMedicineByMedicineForId(Long id) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_MEDICINE_FOR_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Medicine medicine = new Medicine();
                setMedicineParameters(medicine,resultSet);
                medicines.add(medicine);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicines;
    }

    @Override
    public List<Medicine> getMedicineByMedicineFromId(Long id) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_MEDICINE_FROM_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Medicine medicine = new Medicine();
                setMedicineParameters(medicine,resultSet);
                medicines.add(medicine);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicines;
    }

    @Override
    public List<Medicine> getMedicineByMedicineHowId(Long id) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_MEDICINE_HOW_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Medicine medicine = new Medicine();
                setMedicineParameters(medicine,resultSet);
                medicines.add(medicine);
            }

        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicines;
    }

    private void setMedicineParameters(Medicine medicine, ResultSet resultSet) throws SQLException{
        MedicineFor medicineFor;
        MedicineFrom medicineFrom;
        MedicineHow medicineHow;
        medicine.setId(resultSet.getLong("ID"));
        medicine.setName(resultSet.getString("NAME"));
        medicine.setManufacturer(resultSet.getString("MANUFACTURER"));
        medicine.setShortDescription(resultSet.getString("SHORT_DESCRIPTION"));
        medicine.setContraindication(resultSet.getString("CONTRAINDICATION"));
        medicine.setIndication(resultSet.getString("INDICATION"));
        medicine.setDosage(resultSet.getString("DOSAGE"));
        medicine.setPharmacology(resultSet.getString("PHARMACOLOGY"));
        medicine.setSideEffect(resultSet.getString("SIDE_EFFECT"));
        medicineFor = medicineForDAO.getMedicineForById(resultSet.getLong("MEDICINE_FOR_ID"));
        medicine.setMedicineFor(medicineFor);
        medicineFrom = medicineFromDAO.getMedicineFromById(resultSet.getLong("MEDICINE_FROM_ID"));
        medicine.setMedicineFrom(medicineFrom);
        medicineHow = medicineHowDAO.getMedicineHowById(resultSet.getLong("MEDICINE_HOW_ID"));
        medicine.setMedicineHow(medicineHow);
    }
}
