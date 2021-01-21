package com.epam.tcfp.medHelp.dao.interfaces;


import java.sql.SQLException;
import java.util.List;

public interface MainDAO<T> {


    List<T> getAll() throws SQLException;
}
