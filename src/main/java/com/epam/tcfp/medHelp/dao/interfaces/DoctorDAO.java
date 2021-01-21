package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.Doctor;

import java.sql.SQLException;

public interface DoctorDAO extends MainDAO<Doctor> {

    Doctor getByEmail(String email) throws SQLException;

    void createDoctor(Doctor doctor) throws SQLException;

    Doctor getByEmailAndPassword(String email,String password) throws SQLException;

    Doctor getById(Long id) throws SQLException;

    void updateDoctor(Doctor doctor) throws SQLException;

    void deleteDoctor(Long id) throws SQLException;

    void approveDoctor(Long id) throws SQLException;

    void disapproveDoctor(Long id) throws SQLException;
}
