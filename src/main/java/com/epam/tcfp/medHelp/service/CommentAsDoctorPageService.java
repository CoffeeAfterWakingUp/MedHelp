package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.MedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.service.form.CommentAsDoctorForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.util.constants.PageName.DOCTOR_MEDICINE_COMMENT_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class CommentAsDoctorPageService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        CommentAsDoctorForm form = CommentAsDoctorForm.getInstance();
        form.setFormParameters(request);

//        if(form.getButton() != null){
            request.setAttribute(MEDICINE_ID,form.getMedicineId());
            request.setAttribute(MEDICINE_NAME,medicineDAO.getMedicineById(form.getMedicineId()).getName());
            request.setAttribute(DOCTOR_ID,form.getDoctorId());
            requestDispatcher = request.getRequestDispatcher(DOCTOR_MEDICINE_COMMENT_PAGE);
            requestDispatcher.forward(request,response);
//        }
//        else{
//
//        }
    }
}
