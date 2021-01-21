package com.epam.tcfp.medHelp.dao.impl;


import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.USER;

public class UserDAOImpl implements UserDAO {
    private static final String CREATE_USER = "INSERT INTO USER(EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE, ROLE) VALUES (?,?,?,?,?,?)";
    private static final String SELECT_ALL_USER = "SELECT * FROM USER";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM USER WHERE ID = ?";
    private static final String UPDATE_USER = "UPDATE user SET email = ?,password = ?,firstName = ?,lastName = ?,phone = ?,role = ?,exist = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";



    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public void createUser(User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)){
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setString(5,user.getPhone());
            preparedStatement.setString(6,user.getRole());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER)){
           // preparedStatement.setString(1,USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                setUserParameters(user,resultSet);
                users.add(user);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return users;
    }


    @Override
    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                setUserParameters(user,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        User user = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                setUserParameters(user,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public User getById(Long id) throws SQLException {
        User user = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                setUserParameters(user,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)){
            preparedStatement.setLong(8,user.getId());
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setString(5,user.getPhone());
            preparedStatement.setString(6,user.getRole());
            preparedStatement.setBoolean(7,user.getExist());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void setUserParameters(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getLong("ID"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setFirstName(resultSet.getString("FIRSTNAME"));
        user.setLastName(resultSet.getString("LASTNAME"));
        user.setPhone(resultSet.getString("PHONE"));
        user.setRole(resultSet.getString("ROLE"));
        user.setExist(resultSet.getBoolean("EXIST"));
    }


}
