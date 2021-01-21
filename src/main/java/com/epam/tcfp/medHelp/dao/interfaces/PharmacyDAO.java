package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.Pharmacy;

import java.sql.SQLException;
import java.util.List;

public interface PharmacyDAO extends MainDAO<Pharmacy> {

    List<Pharmacy> getPharmacyByCityId(Long id) throws SQLException;

    Pharmacy getPharmacyById(Long id) throws SQLException;

    void update(Pharmacy pharmacy) throws SQLException;

    void create(Pharmacy pharmacy) throws SQLException;

    void delete(Long id) throws SQLException;

    void approve(Long id) throws SQLException;

    void disapprove(Long id) throws SQLException;

    List<Pharmacy> getApprovedPharmacies() throws SQLException;


}
