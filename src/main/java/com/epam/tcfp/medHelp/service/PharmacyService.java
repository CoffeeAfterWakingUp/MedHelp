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
import com.epam.tcfp.medHelp.service.form.PharmacyForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.PHARMACY_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.MEDICINE_OF_PHARMACY;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.PHARMACY;

public class PharmacyService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private MedicineByPharmacyDAO medicineByPharmacyDAO = (MedicineByPharmacyDAOImpl) daoFactory.getDAO("MEDICINE_BY_PHARMACY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Pharmacy pharmacy;
        List<MedicineByPharmacy> medicineByPharmacies;
        RequestDispatcher requestDispatcher;
        PharmacyForm pharmacyForm = PharmacyForm.getInstance();
        pharmacyForm.setFormParameters(request);

        if(pharmacyForm.getButton() != null){
            pharmacy = pharmacyDAO.getPharmacyById(pharmacyForm.getId());
            medicineByPharmacies = medicineByPharmacyDAO.getMedicineByPharmacyId(pharmacyForm.getId());
            request.setAttribute(PHARMACY,pharmacy);
            request.setAttribute(MEDICINE_OF_PHARMACY,medicineByPharmacies);
            requestDispatcher = request.getRequestDispatcher(PHARMACY_PAGE);
            requestDispatcher.forward(request,response);
        }else{
            System.out.println("error" + getClass().getName());
        }
    }
}
