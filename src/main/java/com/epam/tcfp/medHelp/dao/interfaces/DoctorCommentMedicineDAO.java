package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.DoctorCommentMedicine;

import java.sql.SQLException;
import java.util.List;

public interface DoctorCommentMedicineDAO extends MainDAO<DoctorCommentMedicine>{

    void create(DoctorCommentMedicine comment) throws SQLException;

    Integer getAverageEfficiencyRating(Long id) throws SQLException;

    Integer getAverageSideEffectsRating(Long id) throws SQLException;

    Integer getAveragePriceAndQualityRating(Long id) throws SQLException;

    List<DoctorCommentMedicine> getByMedicineId(Long id) throws SQLException;

}
