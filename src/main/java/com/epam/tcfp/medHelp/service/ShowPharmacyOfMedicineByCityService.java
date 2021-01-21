package com.epam.tcfp.medHelp.service;


import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.*;
import com.epam.tcfp.medHelp.dao.interfaces.*;
import com.epam.tcfp.medHelp.entity.*;
import com.epam.tcfp.medHelp.service.form.ShowPharmacyOfMedicineByCityForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.service.ServiceName.MEDICINE_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MEDICINE_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class ShowPharmacyOfMedicineByCityService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineByPharmacyDAO medicineByPharmacyDAO = (MedicineByPharmacyDAOImpl) daoFactory.getDAO("MEDICINE_BY_PHARMACY_DAO");
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();



    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        List<MedicineByPharmacy> medicineByPharmacies;
        ShowPharmacyOfMedicineByCityForm showPharmacyOfMedicineByCityForm = ShowPharmacyOfMedicineByCityForm.getInstance();
        showPharmacyOfMedicineByCityForm.setFormParameters(request);

//        if(showPharmacyOfMedicineByCityForm.getButton() != null){
        medicineByPharmacies = medicineByPharmacyDAO.getPharmacyOfMedicineByCityId(showPharmacyOfMedicineByCityForm.getCityId(), showPharmacyOfMedicineByCityForm.getMedicineId());
//            cities = cityDAO.getAll();
//            medicine = medicineDAO.getMedicineById(showPharmacyOfMedicineByCityForm.getMedicineId());


        request.setAttribute(PHARMACY_OF_MEDICINE_BY_CITY, medicineByPharmacies);
//            request.setAttribute(MEDICINE,medicine);
//            request.setAttribute(ALL_CITY,cities);

//            requestDispatcher = request.getRequestDispatcher(MEDICINE_PAGE);
//            requestDispatcher.forward(request,response);
        serviceFactory.getService(MEDICINE_SERVICE).perform(request, response);
//        }
//        else{
//            System.out.println("error " + getClass().getName());
//        }

    }
}
