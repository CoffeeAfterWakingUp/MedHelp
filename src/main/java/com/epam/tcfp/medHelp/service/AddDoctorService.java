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
import com.epam.tcfp.medHelp.service.form.DoctorForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.GO_ADD_DOCTOR_SERVICE;
import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_DOCTOR_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.EMAIL_ALREADY_TAKEN_ERROR;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.EMAIL_ALREADY_TAKEN_ERROR_MSG;
import static com.epam.tcfp.medHelp.util.constants.PageName.ADD_DOCTOR_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;


public class AddDoctorService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");
    private ProfessionDAO professionDAO = (ProfessionDAOImpl) daoFactory.getDAO("PROFESSION_DAO");
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();


    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        DoctorForm form = DoctorForm.getInstance();
        form.setFormParameters(request);
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null) {
            if (form.getButton() != null) {
                if (form.isEmailExist()) {
                    request.setAttribute(EMAIL_ALREADY_TAKEN_ERROR, EMAIL_ALREADY_TAKEN_ERROR_MSG);
                    serviceFactory.getService(GO_ADD_DOCTOR_SERVICE).perform(request, response);
                } else {
                    Doctor doctor = new Doctor();
                    MedCenter medCenter = medCenterDAO.getByID(form.getMedCenterId());
                    Profession profession = professionDAO.getById(form.getProfessionId());
                    doctor.setEmail(form.getEmail());
                    doctor.setPassword(form.getPassword());
                    doctor.setFirstName(form.getFirstName());
                    doctor.setLastName(form.getLastName());
                    doctor.setPhone(form.getPhone());
                    doctor.setProfession(profession);
                    doctor.setMedCenter(medCenter);
                    doctor.setApproved(form.getApproved());
                    doctor.setExist(form.getExist());
                    doctor.setExperience(form.getExperience());
                    doctorDAO.createDoctor(doctor);
                    response.sendRedirect(SHOW_ALL_DOCTOR_SERVICE);
                }
            }
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
