package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineByPharmacyDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.PharmacyDAO;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.MedicineByPharmacy;
import com.epam.tcfp.medHelp.entity.Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineByPharmacyDAOImpl implements MedicineByPharmacyDAO {
    private static final String SELECT_MEDICINE_OF_PHARMACY_BY_PHARMACY_ID = "SELECT * FROM medicine_by_pharmacy WHERE pharmacy_id = ? AND exist = 1";
    private static final String SELECT_MEDICINE_BY_PHARMACY_ID = "SELECT * FROM medicine_by_pharmacy WHERE medicine_id = ? AND exist = 1";
    private static final String SELECT_PHARMACY_OF_MEDICINE_BY_CITY_ID = "SELECT * FROM medicine_by_pharmacy WHERE medicine_id = ? AND pharmacy_id IN (SELECT id FROM pharmacy WHERE city_id = ? AND approved = 1) AND exist = 1";
    private static final String SELECT_ALL = "SELECT * FROM medicine_by_pharmacy";
    private static final String SELECT_BY_ID = "SELECT * FROM medicine_by_pharmacy WHERE id = ?";
    private static final String UPDATE_MEDICINE_BY_PHARMACY = "UPDATE medicine_by_pharmacy SET medicine_id = ?,pharmacy_id = ?,price = ?,exist = ? WHERE id = ?";
    private static final String DELETE_MEDICINE_BY_PHARMACY = "DELETE FROM medicine_by_pharmacy WHERE id = ?";
    private static final String CREATE_MEDICINE_BY_PHARMACY = "INSERT INTO medicine_by_pharmacy(medicine_id, pharmacy_id, price, exist) VALUES (?,?,?,?)";




    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<MedicineByPharmacy> getAll() throws SQLException {
        List<MedicineByPharmacy> medicineByPharmacies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedicineByPharmacy medicineByPharmacy = new MedicineByPharmacy();
                setMedicineByPharmacyParameters(medicineByPharmacy,resultSet);
                medicineByPharmacies.add(medicineByPharmacy);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineByPharmacies;
    }


    @Override
    public List<MedicineByPharmacy> getMedicineByPharmacyId(Long id) throws SQLException {
        List<MedicineByPharmacy> medicineByPharmacies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_OF_PHARMACY_BY_PHARMACY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedicineByPharmacy medicineByPharmacy = new MedicineByPharmacy();
                setMedicineByPharmacyParameters(medicineByPharmacy,resultSet);
                medicineByPharmacies.add(medicineByPharmacy);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineByPharmacies;
    }

    @Override
    public List<MedicineByPharmacy> getPharmacyByMedicineId(Long id) throws SQLException {
        List<MedicineByPharmacy> medicineByPharmacies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_PHARMACY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MedicineByPharmacy medicineByPharmacy = new MedicineByPharmacy();
                setMedicineByPharmacyParameters(medicineByPharmacy,resultSet);
                medicineByPharmacies.add(medicineByPharmacy);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineByPharmacies;
    }

    @Override
    public List<MedicineByPharmacy> getPharmacyOfMedicineByCityId(Long cityId,Long medicineId) throws SQLException {
        List<MedicineByPharmacy> medicineByPharmacies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHARMACY_OF_MEDICINE_BY_CITY_ID)){
            preparedStatement.setLong(1,medicineId);
            preparedStatement.setLong(2,cityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                MedicineByPharmacy medicineByPharmacy = new MedicineByPharmacy();
                setMedicineByPharmacyParameters(medicineByPharmacy, resultSet);
                medicineByPharmacies.add(medicineByPharmacy);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineByPharmacies;
    }

    @Override
    public MedicineByPharmacy getById(Long id) throws SQLException {
        MedicineByPharmacy medicineByPharmacy = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                medicineByPharmacy = new MedicineByPharmacy();
                setMedicineByPharmacyParameters(medicineByPharmacy, resultSet);

            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return medicineByPharmacy;
    }

    @Override
    public void update(MedicineByPharmacy medicineByPharmacy) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEDICINE_BY_PHARMACY)){
            preparedStatement.setLong(1,medicineByPharmacy.getMedicine().getId());
            preparedStatement.setLong(2,medicineByPharmacy.getPharmacy().getId());
            preparedStatement.setInt(3,medicineByPharmacy.getPrice());
            preparedStatement.setBoolean(4,medicineByPharmacy.getExist());
            preparedStatement.setLong(5,medicineByPharmacy.getId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEDICINE_BY_PHARMACY)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void create(MedicineByPharmacy medicineByPharmacy) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MEDICINE_BY_PHARMACY)){
            preparedStatement.setLong(1,medicineByPharmacy.getMedicine().getId());
            preparedStatement.setLong(2,medicineByPharmacy.getPharmacy().getId());
            preparedStatement.setInt(3,medicineByPharmacy.getPrice());
            preparedStatement.setBoolean(4,medicineByPharmacy.getExist());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void setMedicineByPharmacyParameters(MedicineByPharmacy medicineByPharmacy, ResultSet resultSet) throws SQLException{
        Medicine medicine;
        Pharmacy pharmacy;
        medicineByPharmacy.setId(resultSet.getLong("ID"));
        medicineByPharmacy.setPrice(resultSet.getInt("PRICE"));
        medicineByPharmacy.setExist(resultSet.getBoolean("EXIST"));
        medicine = medicineDAO.getMedicineById(resultSet.getLong("MEDICINE_ID"));
        pharmacy = pharmacyDAO.getPharmacyById(resultSet.getLong("PHARMACY_ID"));
        medicineByPharmacy.setMedicine(medicine);
        medicineByPharmacy.setPharmacy(pharmacy);
    }
}














