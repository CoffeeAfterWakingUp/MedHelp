package com.epam.tcfp.medHelp.dao.interfaces;


import com.epam.tcfp.medHelp.entity.City;

import java.sql.SQLException;

public interface CityDAO extends MainDAO<City> {

    City getCityById(Long id) throws SQLException;


}
