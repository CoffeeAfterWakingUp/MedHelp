package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedCenterDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.ProfessionDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.dao.interfaces.ProfessionDAO;
import com.epam.tcfp.medHelp.entity.Doctor;
import com.epam.tcfp.medHelp.entity.MedCenter;
import com.epam.tcfp.medHelp.entity.Profession;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.EditDoctorForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.EDIT_DOCTOR_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class EditDoctorService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");
    private ProfessionDAO professionDAO = (ProfessionDAOImpl) daoFactory.getDAO("PROFESSION_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        EditDoctorForm form = EditDoctorForm.getInstance();
        form.setFormParameters(request);
        Doctor doctor;
        List<Profession> professions;
        List<MedCenter> medCenters;
        if(admin != null) {
            if (form.getButton() != null) {
                doctor = doctorDAO.getById(form.getId());
                professions = professionDAO.getAll();
                medCenters = medCenterDAO.getApprovedMedCenters();
                request.setAttribute(DOCTOR, doctor);
                request.setAttribute(ALL_PROFESSIONS_SESSION, professions);
                request.setAttribute(ALL_MED_CENTERS_SESSION, medCenters);
                requestDispatcher = request.getRequestDispatcher(EDIT_DOCTOR_PAGE);
                requestDispatcher.forward(request, response);
            }
        } else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
