package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.UserCommentMedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.entity.UserCommentMedicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCommentMedicineDAOImpl implements UserCommentMedicineDAO {
    private static final String CREATE_COMMENT = "INSERT INTO user_comment_medicine(medicine_id, user_id, info) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM user_comment_medicine";
    private static final String SELECT_COMMENT_BY_MEDICINE_ID = "SELECT * FROM user_comment_medicine WHERE medicine_id = ? ORDER BY date DESC";


    private ConnectionPool connectionPool;
    private Connection connection;

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");

    @Override
    public void create(UserCommentMedicine comment) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COMMENT)){
            preparedStatement.setLong(1,comment.getMedicine().getId());
            preparedStatement.setLong(2,comment.getUser().getId());
            preparedStatement.setString(3,comment.getInfo());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<UserCommentMedicine> getAll() throws SQLException {
        List<UserCommentMedicine> comments = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserCommentMedicine comment = new UserCommentMedicine();
                setCommentParameters(comment,resultSet);
                comments.add(comment);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return comments;
    }

    @Override
    public List<UserCommentMedicine> getByMedicineId(Long id) throws SQLException {
        List<UserCommentMedicine> comments = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_MEDICINE_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserCommentMedicine comment = new UserCommentMedicine();
                setCommentParameters(comment,resultSet);
                comments.add(comment);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return comments;
    }


    private void setCommentParameters(UserCommentMedicine comment,ResultSet resultSet) throws SQLException{
        Medicine medicine;
        User user;
        comment.setId(resultSet.getLong("ID"));
        medicine = medicineDAO.getMedicineById(resultSet.getLong("MEDICINE_ID"));
        comment.setMedicine(medicine);
        user = userDAO.getById(resultSet.getLong("USER_ID"));
        comment.setUser(user);
        comment.setInfo(resultSet.getString("INFO"));
        comment.setDate(resultSet.getTimestamp("DATE"));
    }
}
