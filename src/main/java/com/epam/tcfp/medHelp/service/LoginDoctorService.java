package com.epam.tcfp.medHelp.service;


import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.service.form.LoginDoctorForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.util.constants.ErrorName.DOCTOR_AUTH_NOT_VALID_ERROR;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.DOCTOR_AUTH_NOT_VALID_ERROR_MSG;
import static com.epam.tcfp.medHelp.util.constants.PageName.*;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_DOCTOR_SESSION;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.DOCTOR_EMAIL;

public class LoginDoctorService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");


    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        LoginDoctorForm form = LoginDoctorForm.getInstance();
        form.setFormParameters(request);

        if(form.getButton() != null){
            if(!form.isDoctorExist()){
                request.setAttribute(DOCTOR_EMAIL,form.getEmail());
                request.setAttribute(DOCTOR_AUTH_NOT_VALID_ERROR,DOCTOR_AUTH_NOT_VALID_ERROR_MSG);
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
                requestDispatcher.forward(request, response);
            }
            else{
                session.setAttribute(CURRENT_DOCTOR_SESSION,doctorDAO.getByEmail(form.getEmail()));
                response.sendRedirect(MAIN_PAGE);
            }
        } else{
            requestDispatcher = request.getRequestDispatcher(INTERNAL_SERVER_ERROR_PAGE);
            requestDispatcher.forward(request,response);
        }
    }
}
