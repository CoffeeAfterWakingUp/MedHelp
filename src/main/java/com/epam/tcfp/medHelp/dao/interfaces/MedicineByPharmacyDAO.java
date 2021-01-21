package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.MedicineByPharmacy;

import java.sql.SQLException;
import java.util.List;

public interface MedicineByPharmacyDAO extends MainDAO<MedicineByPharmacy> {


    List<MedicineByPharmacy> getMedicineByPharmacyId(Long id) throws SQLException;


    List<MedicineByPharmacy> getPharmacyByMedicineId(Long id) throws SQLException;

    List<MedicineByPharmacy> getPharmacyOfMedicineByCityId(Long cityId,Long medicineId) throws SQLException;

    MedicineByPharmacy getById(Long id) throws SQLException;

    void update(MedicineByPharmacy medicineByPharmacy) throws SQLException;

    void delete(Long id) throws SQLException;

    void create(MedicineByPharmacy medicineByPharmacy) throws SQLException;




}
