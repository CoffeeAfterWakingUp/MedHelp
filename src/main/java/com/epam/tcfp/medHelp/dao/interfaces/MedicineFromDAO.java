package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.MedicineFrom;

import java.sql.SQLException;

public interface MedicineFromDAO extends MainDAO<MedicineFrom> {

    MedicineFrom getMedicineFromById(Long id) throws SQLException;
}
