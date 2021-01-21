package com.epam.tcfp.medHelp.dao.interfaces;

import com.epam.tcfp.medHelp.entity.UserCommentMedicine;

import java.sql.SQLException;
import java.util.List;

public interface UserCommentMedicineDAO extends MainDAO<UserCommentMedicine> {

    void create(UserCommentMedicine comment) throws SQLException;

    List<UserCommentMedicine> getByMedicineId(Long id) throws SQLException;
}
