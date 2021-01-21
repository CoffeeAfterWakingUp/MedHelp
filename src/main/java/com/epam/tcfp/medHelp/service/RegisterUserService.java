package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedCenterDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.ProfessionDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.UserDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.dao.interfaces.ProfessionDAO;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.Doctor;
import com.epam.tcfp.medHelp.entity.MedCenter;
import com.epam.tcfp.medHelp.entity.Profession;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.DoctorRegisterForm;
import com.epam.tcfp.medHelp.service.form.UserRegisterForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.util.constants.ErrorName.*;
import static com.epam.tcfp.medHelp.util.constants.PageName.*;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.USER;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class RegisterUserService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");
    private ProfessionDAO professionDAO = (ProfessionDAOImpl) daoFactory.getDAO("PROFESSION_DAO");
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User user;
        Doctor doctor;
        HttpSession session = request.getSession();
        boolean valid = true;
        RequestDispatcher requestDispatcher;
        UserRegisterForm userRegisterForm = new UserRegisterForm();
        DoctorRegisterForm doctorRegisterForm = new DoctorRegisterForm();
        userRegisterForm.setFormParameters(request);
        doctorRegisterForm.setFormParameters(request);


        if (userRegisterForm.getButton() != null) {
            valid = checkRegisterForm(userRegisterForm, request);

            if (valid) {
                user = new User();
                user.setEmail(userRegisterForm.getEmail());
                user.setFirstName(userRegisterForm.getFirstName());
                user.setLastName(userRegisterForm.getLastName());
                user.setPassword(userRegisterForm.getPassword());
                user.setPhone(userRegisterForm.getPhone());
                user.setRole(USER);
                userDAO.createUser(user);
                request.setAttribute(SUCCESSFUL_REGISTRATION, SUCCESSFUL_REGISTRATION_MSG);
            } else {
                request.setAttribute(USER_FIRST_NAME, userRegisterForm.getFirstName());
                request.setAttribute(USER_LAST_NAME, userRegisterForm.getLastName());
                request.setAttribute(USER_PHONE, userRegisterForm.getPhone());
                request.setAttribute(USER_EMAIL, userRegisterForm.getEmail());
            }
        }
        if (doctorRegisterForm.getButton() != null) {
            valid = checkRegisterForm(doctorRegisterForm, request);
            if (valid) {
                doctor = new Doctor();
                Profession profession = new Profession();
                MedCenter medCenter = new MedCenter();
                doctor.setEmail(doctorRegisterForm.getEmail());
                doctor.setFirstName(doctorRegisterForm.getFirstName());
                doctor.setLastName(doctorRegisterForm.getLastName());
                doctor.setPassword(doctorRegisterForm.getPassword());
                doctor.setPhone(doctorRegisterForm.getPhone());
                profession.setId(doctorRegisterForm.getProfessionId());
                doctor.setProfession(profession);
                medCenter.setId(doctorRegisterForm.getMedCenterId());
                doctor.setMedCenter(medCenter);
                doctor.setExperience(doctorRegisterForm.getExperience());
                doctorDAO.createDoctor(doctor);
                request.setAttribute(SUCCESSFUL_REGISTRATION, SUCCESSFUL_REGISTRATION_MSG);
            } else {
                request.setAttribute(DOCTOR_FIRST_NAME, doctorRegisterForm.getFirstName());
                request.setAttribute(DOCTOR_LAST_NAME, doctorRegisterForm.getLastName());
                request.setAttribute(DOCTOR_PHONE, doctorRegisterForm.getPhone());
                request.setAttribute(DOCTOR_EMAIL, doctorRegisterForm.getEmail());
                request.setAttribute(DOCTOR_EXPERIENCE, doctorRegisterForm.getExperience());
            }

        }

        session.setAttribute(ALL_MED_CENTERS_SESSION, medCenterDAO.getApprovedMedCenters());
        session.setAttribute(ALL_PROFESSIONS_SESSION, professionDAO.getAll());
        requestDispatcher = request.getRequestDispatcher(REGISTRATION_PAGE);
        requestDispatcher.forward(request, response);


    }


    private boolean checkRegisterForm(UserRegisterForm registerForm,HttpServletRequest request) throws SQLException {
        boolean valid = true;
        if(!registerForm.isFirstNameValid() && registerForm.getFirstName() != null){
            request.setAttribute(FIRST_NAME_NOT_VALID_ERROR,FIRST_NAME_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isLastNameValid() && registerForm.getLastName() != null) {
            request.setAttribute(LAST_NAME_NOT_VALID_ERROR, LAST_NAME_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isPhoneValid() && registerForm.getPhone() != null) {
            request.setAttribute(PHONE_NOT_VALID_ERROR, PHONE_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (registerForm.isEmailExist() && registerForm.getEmail() != null) {
            request.setAttribute(EMAIL_ALREADY_TAKEN_ERROR, EMAIL_ALREADY_TAKEN_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isEmailValid() && registerForm.getEmail() != null) {
            request.setAttribute(EMAIL_NOT_VALID_ERROR, EMAIL_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isPasswordValid() && registerForm.getPassword() != null) {
            request.setAttribute(PASSWORD_NOT_VALID_ERROR, PASSWORD_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isConfirmPasswordValid()  && registerForm.getConfirmPassword() != null) {
            request.setAttribute(CONFIRM_PASSWORD_NOT_VALID_ERROR, CONFIRM_PASSWORD_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        return valid;
    }


    private boolean checkRegisterForm(DoctorRegisterForm registerForm,HttpServletRequest request) throws SQLException {
        boolean valid = true;
        if(!registerForm.isFirstNameValid()){
            request.setAttribute(DOCTOR_FIRST_NAME_NOT_VALID_ERROR,DOCTOR_FIRST_NAME_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isLastNameValid()) {
            request.setAttribute(DOCTOR_LAST_NAME_NOT_VALID_ERROR, DOCTOR_LAST_NAME_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isPhoneValid()) {
            request.setAttribute(DOCTOR_PHONE_NOT_VALID_ERROR, DOCTOR_PHONE_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (registerForm.isEmailExist()) {
            request.setAttribute(DOCTOR_EMAIL_ALREADY_TAKEN_ERROR, DOCTOR_EMAIL_ALREADY_TAKEN_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isEmailValid()) {
            request.setAttribute(DOCTOR_EMAIL_NOT_VALID_ERROR, DOCTOR_EMAIL_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isPasswordValid()) {
            request.setAttribute(DOCTOR_PASSWORD_NOT_VALID_ERROR, DOCTOR_PASSWORD_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        if (!registerForm.isConfirmPasswordValid()) {
            request.setAttribute(DOCTOR_CONFIRM_PASSWORD_NOT_VALID_ERROR, DOCTOR_CONFIRM_PASSWORD_NOT_VALID_ERROR_MSG);
            valid = false;
        }
        return valid;
    }
}
