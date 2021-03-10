package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.IdForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_DOCTOR_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class DeleteDoctorService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        IdForm form = IdForm.getInstance();
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        form.setFormParameters(request);

        if(admin != null) {
            if (form.getButton() != null) {
                doctorDAO.deleteDoctor(form.getId());
                response.sendRedirect(SHOW_ALL_DOCTOR_SERVICE);
            }
        }else {
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
