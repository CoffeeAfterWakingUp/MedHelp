package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.Profession;

import java.sql.SQLException;

public interface ProfessionDAO extends MainDAO<Profession> {


    Profession getById(Long id) throws SQLException;

}
