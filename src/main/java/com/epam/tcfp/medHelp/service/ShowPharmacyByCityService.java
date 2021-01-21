package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.CityDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.PharmacyDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.dao.interfaces.PharmacyDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.Pharmacy;
import com.epam.tcfp.medHelp.service.form.ShowPharmacyByCityForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_PHARMACY_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.PageName.ALL_PHARMACY_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class ShowPharmacyByCityService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        List<Pharmacy> pharmacies;
        City city;
        ShowPharmacyByCityForm showPharmacyByCityForm = ShowPharmacyByCityForm.getInstance();
        showPharmacyByCityForm.setFormParameters(request);
        if(showPharmacyByCityForm.getButton() != null){
            pharmacies = pharmacyDAO.getPharmacyByCityId(showPharmacyByCityForm.getId());
            city = cityDAO.getCityById(showPharmacyByCityForm.getId());
            request.setAttribute(PHARMACY_BY_CITY_ID,pharmacies);
            request.setAttribute(CITY_OF_PHARMACY,city);
            serviceFactory.getService(SHOW_ALL_PHARMACY_SERVICE).perform(request,response);
        }else{
            System.out.println("404");
        }
    }
}
