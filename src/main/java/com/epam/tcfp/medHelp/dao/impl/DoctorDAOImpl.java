package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.dao.interfaces.ProfessionDAO;
import com.epam.tcfp.medHelp.entity.Doctor;
import com.epam.tcfp.medHelp.entity.MedCenter;
import com.epam.tcfp.medHelp.entity.Profession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.ParametersName.APPROVED;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DISAPPROVED;

public class DoctorDAOImpl implements DoctorDAO {
    private static final String CREATE_DOCTOR = "INSERT INTO DOCTOR(EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE, MEDCENTER_ID, PROFESSION_ID, EXPERIENCE,approved,exist) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_DOCTOR_BY_EMAIL = "SELECT * FROM DOCTOR WHERE EMAIL = ?";
    private static final String SELECT_DOCTOR_BY_EMAIL_AND_PASSWORD = "SELECT * FROM DOCTOR WHERE EMAIL = ? AND PASSWORD = ? AND approved = 1 AND exist=1";
    private static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM DOCTOR WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM doctor";
    private static final String UPDATE_DOCTOR = "UPDATE doctor SET email = ?,password = ?,firstName = ?,lastName = ?,phone = ?,medCenter_id = ?,profession_id = ?,experience = ?,approved = ?,exist = ? WHERE id = ?";
    private static final String DELETE_DOCTOR = "DELETE FROM doctor WHERE id = ?";
    private static final String APPROVE_OR_DISAPPROVE_DOCTOR = "UPDATE doctor SET approved = ? WHERE id = ?";

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ProfessionDAO professionDAO = (ProfessionDAOImpl) daoFactory.getDAO("PROFESSION_DAO");
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public Doctor getByEmail(String email) throws SQLException {
        Doctor doctor = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_EMAIL)){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                doctor = new Doctor();
                setDoctorParameters(doctor,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return doctor;
    }


    @Override
    public void createDoctor(Doctor doctor) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DOCTOR)){
            preparedStatement.setString(1,doctor.getEmail());
            preparedStatement.setString(2,doctor.getPassword());
            preparedStatement.setString(3,doctor.getFirstName());
            preparedStatement.setString(4,doctor.getLastName());
            preparedStatement.setString(5,doctor.getPhone());
            preparedStatement.setLong(6,doctor.getMedCenter().getId());
            preparedStatement.setLong(7,doctor.getProfession().getId());
            preparedStatement.setInt(8,doctor.getExperience());
            preparedStatement.setBoolean(9,doctor.getApproved());
            preparedStatement.setBoolean(10,doctor.getExist());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public List<Doctor> getAll() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Doctor doctor = new Doctor();
                setDoctorParameters(doctor,resultSet);
                doctors.add(doctor);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return doctors;
    }

    @Override
    public Doctor getByEmailAndPassword(String email, String password) throws SQLException {
        Doctor doctor = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_EMAIL_AND_PASSWORD)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                doctor = new Doctor();
                setDoctorParameters(doctor,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return doctor;
    }

    @Override
    public Doctor getById(Long id) throws SQLException {
        Doctor doctor = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                doctor = new Doctor();
                setDoctorParameters(doctor,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return doctor;
    }

    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR)){
            preparedStatement.setString(1,doctor.getEmail());
            preparedStatement.setString(2,doctor.getPassword());
            preparedStatement.setString(3,doctor.getFirstName());
            preparedStatement.setString(4,doctor.getLastName());
            preparedStatement.setString(5,doctor.getPhone());
            preparedStatement.setLong(6,doctor.getMedCenter().getId());
            preparedStatement.setLong(7,doctor.getProfession().getId());
            preparedStatement.setInt(8,doctor.getExperience());
            preparedStatement.setBoolean(9,doctor.getApproved());
            preparedStatement.setBoolean(10,doctor.getExist());
            preparedStatement.setLong(11,doctor.getId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteDoctor(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DOCTOR)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void approveDoctor(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_OR_DISAPPROVE_DOCTOR)){
            preparedStatement.setBoolean(1,APPROVED);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void disapproveDoctor(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_OR_DISAPPROVE_DOCTOR)){
            preparedStatement.setBoolean(1,DISAPPROVED);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void setDoctorParameters(Doctor doctor, ResultSet resultSet) throws SQLException {
        MedCenter medCenter;
        Profession profession;
        doctor.setId(resultSet.getLong("ID"));
        doctor.setFirstName(resultSet.getString("FIRSTNAME"));
        doctor.setLastName(resultSet.getString("LASTNAME"));
        doctor.setEmail(resultSet.getString("EMAIL"));
        doctor.setPassword(resultSet.getString("PASSWORD"));
        doctor.setPhone(resultSet.getString("PHONE"));
        medCenter = medCenterDAO.getByID(resultSet.getLong("MEDCENTER_ID"));
        doctor.setMedCenter(medCenter);
        profession = professionDAO.getById(resultSet.getLong("PROFESSION_ID"));
        doctor.setProfession(profession);
        doctor.setExperience(resultSet.getInt("EXPERIENCE"));
        doctor.setExist(resultSet.getBoolean("EXIST"));
        doctor.setApproved(resultSet.getBoolean("APPROVED"));
    }
}
