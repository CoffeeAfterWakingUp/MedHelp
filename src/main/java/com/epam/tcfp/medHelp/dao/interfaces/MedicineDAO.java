package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.Medicine;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDAO extends MainDAO<Medicine> {

    Medicine getMedicineById(Long id) throws SQLException;

    List<Medicine> getMedicineByMedicineForId(Long id) throws SQLException;

    List<Medicine> getMedicineByMedicineFromId(Long id) throws SQLException;

    List<Medicine> getMedicineByMedicineHowId(Long id) throws SQLException;
}
