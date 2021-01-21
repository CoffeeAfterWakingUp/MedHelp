package com.epam.tcfp.medHelp.dao.impl;

import com.epam.tcfp.medHelp.dao.connection.ConnectionPool;
import com.epam.tcfp.medHelp.dao.interfaces.ProfessionDAO;
import com.epam.tcfp.medHelp.entity.Profession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessionDAOImpl implements ProfessionDAO {
    private static final String SELECT_ALL_PROFESSION = "SELECT * FROM PROFESSION";
    private static final String SELECT_PROFESSION_BY_ID = "SELECT * FROM PROFESSION WHERE ID = ?";

    private ConnectionPool connectionPool;
    private Connection connection;


    @Override
    public List<Profession> getAll() throws SQLException {
        List<Profession> professions = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROFESSION)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Profession profession = new Profession();
                setParameters(profession,resultSet);
                professions.add(profession);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return professions;
    }

    @Override
    public Profession getById(Long id) throws SQLException {
        Profession profession = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFESSION_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                profession = new Profession();
                setParameters(profession,resultSet);
            }
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return profession;
    }


    private void setParameters(Profession profession,ResultSet resultSet) throws SQLException{
        profession.setId(resultSet.getLong("ID"));
        profession.setName(resultSet.getString("NAME"));
    }
}
