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
import com.epam.tcfp.medHelp.service.form.MedicineByPharmacyForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_MEDICINE_BY_PHARMACY_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class ChangeMedicineByPharmacyService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private MedicineByPharmacyDAO medicineByPharmacyDAO = (MedicineByPharmacyDAOImpl) daoFactory.getDAO("MEDICINE_BY_PHARMACY_DAO");
    private PharmacyDAO pharmacyDAO = (PharmacyDAOImpl) daoFactory.getDAO("PHARMACY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null){
            MedicineByPharmacyForm form = MedicineByPharmacyForm.getInstance();
            form.setFormParameters(request);
            if(form.getButton() != null){
                MedicineByPharmacy medicineByPharmacy = new MedicineByPharmacy();
                Medicine medicine = medicineDAO.getMedicineById(form.getMedicineId());
                Pharmacy pharmacy = pharmacyDAO.getPharmacyById(form.getPharmacyId());
                medicineByPharmacy.setId(form.getId());
                medicineByPharmacy.setMedicine(medicine);
                medicineByPharmacy.setPharmacy(pharmacy);
                medicineByPharmacy.setPrice(form.getPrice());
                medicineByPharmacy.setExist(form.getExist());
                medicineByPharmacyDAO.update(medicineByPharmacy);
                response.sendRedirect(SHOW_ALL_MEDICINE_BY_PHARMACY_SERVICE);
            }
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
