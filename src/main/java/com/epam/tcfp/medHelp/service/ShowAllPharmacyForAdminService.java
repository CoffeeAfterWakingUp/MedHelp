package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.PharmacyDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.PharmacyDAO;
import com.epam.tcfp.medHelp.entity.Pharmacy;
import com.epam.tcfp.medHelp.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.*;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.ALL_PHARMACY;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class ShowAllPharmacyForAdminService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null) {
            RequestDispatcher requestDispatcher;
            List<Pharmacy> pharmacies = pharmacyDAO.getAll();
            request.setAttribute(ALL_PHARMACY,pharmacies);
            requestDispatcher = request.getRequestDispatcher(PHARMACIES_PAGE);
            requestDispatcher.forward(request,response);
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
