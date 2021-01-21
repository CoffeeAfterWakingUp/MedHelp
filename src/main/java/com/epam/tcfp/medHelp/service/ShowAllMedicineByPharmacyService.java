package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.MedicineByPharmacyDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineByPharmacyDAO;
import com.epam.tcfp.medHelp.entity.MedicineByPharmacy;
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

import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MEDICINE_BY_PHARMACY_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.ALL_MEDICINE_OF_PHARMACY;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class ShowAllMedicineByPharmacyService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineByPharmacyDAO medicineByPharmacyDAO = (MedicineByPharmacyDAOImpl) daoFactory.getDAO("MEDICINE_BY_PHARMACY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null) {
            List<MedicineByPharmacy> medicineByPharmacies = medicineByPharmacyDAO.getAll();
            RequestDispatcher requestDispatcher;
            request.setAttribute(ALL_MEDICINE_OF_PHARMACY,medicineByPharmacies);
            requestDispatcher = request.getRequestDispatcher(MEDICINE_BY_PHARMACY_PAGE);
            requestDispatcher.forward(request,response);
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
