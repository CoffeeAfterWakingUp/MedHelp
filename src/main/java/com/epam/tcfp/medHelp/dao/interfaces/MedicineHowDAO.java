package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.MedicineHow;

import java.sql.SQLException;

public interface MedicineHowDAO extends MainDAO<MedicineHow> {

    MedicineHow getMedicineHowById(Long id) throws SQLException;

}
