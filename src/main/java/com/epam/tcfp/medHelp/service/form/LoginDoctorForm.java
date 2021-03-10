package com.epam.tcfp.medHelp.service.form;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.entity.Doctor;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class LoginDoctorForm {
    private String email;
    private String password;
    private String button;

    private static LoginDoctorForm instance;

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");

    private LoginDoctorForm(){}

    public static LoginDoctorForm getInstance(){
        if(instance == null){
            instance = new LoginDoctorForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String doctorEmail = request.getParameter(DOCTOR_EMAIL);
        if(doctorEmail != null){
            this.email = doctorEmail.trim();
        }
        this.password = DigestUtils.md5Hex(request.getParameter(DOCTOR_PASSWORD));
        this.button = request.getParameter(LOGIN_AS_DOCTOR_BUTTON);
    }

    public boolean isDoctorExist() throws SQLException {
        Doctor doctor = doctorDAO.getByEmailAndPassword(email,password);
        return doctor != null;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
