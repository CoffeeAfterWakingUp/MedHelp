package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorCommentMedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.entity.Doctor;
import com.epam.tcfp.medHelp.entity.DoctorCommentMedicine;
import com.epam.tcfp.medHelp.entity.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorCommentMedicineDAOImpl implements DoctorCommentMedicineDAO {
    private static final String CREATE_COMMENT = "INSERT INTO doctor_comment_medicine(doctor_id, medicine_id, pros, cons, info, efficiency_rating, priceAndQuality_rating, sideEffects_rating) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM doctor_comment_medicine";
    private static final String SELECT_AVG_EFFICIENCY_RATING = "SELECT AVG(efficiency_rating) FROM doctor_comment_medicine WHERE medicine_id = ?";
    private static final String SELECT_AVG_SIDE_EFFECTS_RATING = "SELECT AVG(sideEffects_rating) FROM doctor_comment_medicine WHERE medicine_id = ?";
    private static final String SELECT_AVG_PRICE_AND_QUALITY_RATING = "SELECT AVG(priceAndQuality_rating) FROM doctor_comment_medicine WHERE medicine_id = ?";
    private static final String SELECT_COMMENT_BY_MEDICINE_ID = "SELECT * FROM doctor_comment_medicine WHERE medicine_id = ? ORDER BY date DESC";


    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public void create(DoctorCommentMedicine comment) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COMMENT)){
            preparedStatement.setLong(1,comment.getDoctor().getId());
            preparedStatement.setLong(2,comment.getMedicine().getId());
            preparedStatement.setString(3,comment.getPros());
            preparedStatement.setString(4,comment.getCons());
            preparedStatement.setString(5,comment.getInfo());
            preparedStatement.setInt(6,comment.getEfficiency_rating());
            preparedStatement.setInt(7,comment.getPriceAndQuality_rating());
            preparedStatement.setInt(8,comment.getSideEffects_rating());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<DoctorCommentMedicine> getAll() throws SQLException {
        List<DoctorCommentMedicine> doctorComments = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                DoctorCommentMedicine doctorCommentMedicine = new DoctorCommentMedicine();
                setCommentParameters(doctorCommentMedicine,resultSet);
                doctorComments.add(doctorCommentMedicine);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return doctorComments;
    }

    @Override
    public Integer getAverageEfficiencyRating(Long id) throws SQLException {
        int avgRating = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVG_EFFICIENCY_RATING)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                avgRating = resultSet.getInt(1);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return avgRating;
    }

    @Override
    public Integer getAverageSideEffectsRating(Long id) throws SQLException {
        int avgRating = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVG_SIDE_EFFECTS_RATING)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                avgRating = resultSet.getInt(1);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return avgRating;
    }

    @Override
    public Integer getAveragePriceAndQualityRating(Long id) throws SQLException {
        int avgRating = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVG_PRICE_AND_QUALITY_RATING)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                avgRating = resultSet.getInt(1);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return avgRating;
    }

    @Override
    public List<DoctorCommentMedicine> getByMedicineId(Long id) throws SQLException {
        List<DoctorCommentMedicine> doctorComments = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_MEDICINE_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                DoctorCommentMedicine doctorCommentMedicine = new DoctorCommentMedicine();
                setCommentParameters(doctorCommentMedicine,resultSet);
                doctorComments.add(doctorCommentMedicine);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return doctorComments;
    }

    private void setCommentParameters(DoctorCommentMedicine doctorCommentMedicine,ResultSet resultSet) throws SQLException{
        Doctor doctor;
        Medicine medicine;
        doctorCommentMedicine.setId(resultSet.getLong("ID"));
        doctor = doctorDAO.getById(resultSet.getLong("DOCTOR_ID"));
        doctorCommentMedicine.setDoctor(doctor);
        medicine = medicineDAO.getMedicineById(resultSet.getLong("MEDICINE_ID"));
        doctorCommentMedicine.setMedicine(medicine);
        doctorCommentMedicine.setPros(resultSet.getString("PROS"));
        doctorCommentMedicine.setCons(resultSet.getString("CONS"));
        doctorCommentMedicine.setInfo(resultSet.getString("INFO"));
        doctorCommentMedicine.setSideEffects_rating(resultSet.getInt("SIDEEFFECTS_RATING"));
        doctorCommentMedicine.setEfficiency_rating(resultSet.getInt("EFFICIENCY_RATING"));
        doctorCommentMedicine.setPriceAndQuality_rating(resultSet.getInt("PRICEANDQUALITY_RATING"));
        doctorCommentMedicine.setDate(resultSet.getTimestamp("DATE"));
    }
}
