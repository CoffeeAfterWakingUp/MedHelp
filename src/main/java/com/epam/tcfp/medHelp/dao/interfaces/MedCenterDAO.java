package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.MedCenter;

import java.sql.SQLException;
import java.util.List;

public interface MedCenterDAO extends MainDAO<MedCenter> {

    MedCenter getByID(Long id) throws SQLException;

    void update(MedCenter medCenter) throws SQLException;

    void delete(Long id) throws SQLException;

    void approve(Long id) throws SQLException;

    void disapprove(Long id) throws SQLException;

    void create(MedCenter medCenter) throws SQLException;

    List<MedCenter> getApprovedMedCenters() throws SQLException;

}
