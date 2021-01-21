package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.CityDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.PharmacyDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.dao.interfaces.PharmacyDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.Pharmacy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.ALL_PHARMACY_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.ALL_CITY;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.ALL_PHARMACY;

public class ShowAllPharmacyService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        List<Pharmacy> pharmacies = pharmacyDAO.getApprovedPharmacies();
        List<City> cities = cityDAO.getAll();

        request.setAttribute(ALL_PHARMACY,pharmacies);
        request.setAttribute(ALL_CITY,cities);
        requestDispatcher = request.getRequestDispatcher(ALL_PHARMACY_PAGE);
        requestDispatcher.forward(request,response);
    }
}
