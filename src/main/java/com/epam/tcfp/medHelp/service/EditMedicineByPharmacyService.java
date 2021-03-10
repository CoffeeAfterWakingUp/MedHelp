package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.MedicineByPharmacyDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.PharmacyDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineByPharmacyDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.PharmacyDAO;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.MedicineByPharmacy;
import com.epam.tcfp.medHelp.entity.Pharmacy;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.IdForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.EDIT_MEDICINE_BY_PHARMACY_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class EditMedicineByPharmacyService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private MedicineByPharmacyDAO medicineByPharmacyDAO = (MedicineByPharmacyDAOImpl) daoFactory.getDAO("MEDICINE_BY_PHARMACY_DAO");
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");


    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null){
            IdForm form = IdForm.getInstance();
            form.setFormParameters(request);
            if(form.getButton() != null){
                RequestDispatcher requestDispatcher;
                MedicineByPharmacy medicineByPharmacy = medicineByPharmacyDAO.getById(form.getId());
                List<Medicine> medicines = medicineDAO.getAll();
                List<Pharmacy> pharmacies = pharmacyDAO.getApprovedPharmacies();
                request.setAttribute(MEDICINE_BY_PHARMACY,medicineByPharmacy);
                request.setAttribute(ALL_MEDICINE,medicines);
                request.setAttribute(ALL_PHARMACY,pharmacies);
                requestDispatcher = request.getRequestDispatcher(EDIT_MEDICINE_BY_PHARMACY_PAGE);
                requestDispatcher.forward(request,response);
            }
        } else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
