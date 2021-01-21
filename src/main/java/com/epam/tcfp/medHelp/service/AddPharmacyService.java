package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.CityDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.PharmacyDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.dao.interfaces.PharmacyDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.Pharmacy;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.PharmacyAdminForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_PHARMACY_FOR_ADMIN_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class AddPharmacyService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null){
            PharmacyAdminForm form = PharmacyAdminForm.getInstance();
            form.setFormParameters(request);
            if(form.getButton() != null){
                Pharmacy pharmacy = new Pharmacy();
                City city = cityDAO.getCityById(form.getCityId());
                pharmacy.setName(form.getName());
                pharmacy.setCity(city);
                pharmacy.setAddress(form.getAddress());
                pharmacy.setPhone(form.getPhone());
                pharmacy.setApproved(form.getApproved());
                pharmacy.setExist(form.getExist());
                pharmacyDAO.create(pharmacy);
                response.sendRedirect(SHOW_ALL_PHARMACY_FOR_ADMIN_SERVICE);
            }
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
