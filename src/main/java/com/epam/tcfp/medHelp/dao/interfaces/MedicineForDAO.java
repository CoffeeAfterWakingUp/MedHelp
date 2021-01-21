package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.MedicineFor;

import java.sql.SQLException;

public interface MedicineForDAO extends MainDAO<MedicineFor> {

    MedicineFor getMedicineForById(Long id) throws SQLException;
}
