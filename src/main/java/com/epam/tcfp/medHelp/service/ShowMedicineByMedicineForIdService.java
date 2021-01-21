package com.epam.tcfp.medHelp.service;


import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.MedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedicineForDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedicineFromDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedicineHowDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineForDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineFromDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineHowDAO;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.MedicineFor;
import com.epam.tcfp.medHelp.entity.MedicineFrom;
import com.epam.tcfp.medHelp.entity.MedicineHow;
import com.epam.tcfp.medHelp.service.form.ShowMedicineByMedicineForIdForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_MEDICINE_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.PageName.ALL_MEDICINE_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.ALL_MEDICINE_FROM_GROUP;

public class ShowMedicineByMedicineForIdService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private MedicineForDAO medicineForDAO = (MedicineForDAOImpl) daoFactory.getDAO("MEDICINE_FOR_DAO");
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();


    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        List<Medicine> medicines;
        ShowMedicineByMedicineForIdForm showMedicineByMedicineForIdForm = ShowMedicineByMedicineForIdForm.getInstance();
        showMedicineByMedicineForIdForm.setFormParameters(request);
        if(showMedicineByMedicineForIdForm.getButton() != null){
            medicines = medicineDAO.getMedicineByMedicineForId(showMedicineByMedicineForIdForm.getId());
            request.setAttribute(MEDICINE_GROUP_NAME,medicineForDAO.getMedicineForById(showMedicineByMedicineForIdForm.getId()).getName());
            request.setAttribute(MEDICINES_OF_FOR_GROUP,medicines);
            serviceFactory.getService(SHOW_ALL_MEDICINE_SERVICE).perform(request,response);
        }
        else{
            System.out.println("error " + getClass().getName());
        }
    }
}
