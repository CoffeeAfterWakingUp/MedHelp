package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.User;

import java.sql.SQLException;


public interface UserDAO extends MainDAO<User>{


    void createUser(User user) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    User getUserByEmailAndPassword(String email,String password) throws SQLException;

    User getById(Long id) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(Long id) throws SQLException;

}
