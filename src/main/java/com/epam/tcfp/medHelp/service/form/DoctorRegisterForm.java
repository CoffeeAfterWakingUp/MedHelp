package com.epam.tcfp.medHelp.service.form;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.util.resourceManager.RegexResourceManager;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class DoctorRegisterForm{
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;
    private Long medCenterId;
    private Long professionId;
    private Integer experience;
    private String button;


    private static DoctorRegisterForm instance;
    private static RegexResourceManager regexResourceManager = RegexResourceManager.getInstance();
    private static final String EMAIL_REGEXP = regexResourceManager.getValue("email.regexp");
    private static final String PASSWORD_REGEXP = regexResourceManager.getValue("password.regexp");
    private static final String PHONE_REGEXP = regexResourceManager.getValue("phone.regexp");

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");





    public void setFormParameters(HttpServletRequest request){
        String doctorFirstName = request.getParameter(DOCTOR_FIRST_NAME);
        String doctorLastName = request.getParameter(DOCTOR_LAST_NAME);
        String doctorPhone = request.getParameter(DOCTOR_PHONE);
        String doctorEmail = request.getParameter(DOCTOR_EMAIL);
        String doctorMedCenterId = request.getParameter(MED_CENTER_ID);
        String doctorProfessionId = request.getParameter(PROFESSION_ID);
        String doctorExperience = request.getParameter(EXPERIENCE);
        this.password = request.getParameter(DOCTOR_PASSWORD);
        this.confirmPassword = request.getParameter(DOCTOR_CONFIRM_PASSWORD);
        this.button = request.getParameter(DOCTOR_REGISTER_BUTTON);
        if(doctorFirstName != null){
            this.firstName = doctorFirstName.trim();
        }
        if (doctorLastName != null) {
            this.lastName = doctorLastName.trim();
        }
        if (doctorPhone != null) {
            this.phone = doctorPhone.trim();
        }
        if (doctorEmail != null) {
            this.email = doctorEmail.trim();
        }
        if(doctorMedCenterId != null){
            this.medCenterId = Long.parseLong(doctorMedCenterId);
        }
        if(doctorProfessionId != null){
            this.professionId = Long.parseLong(doctorProfessionId);
        }
        if(doctorExperience != null){
            this.experience = Integer.parseInt(doctorExperience);
        }

    }



    public boolean isEmailExist() throws SQLException {
        if(doctorDAO.getByEmail(email) != null){
            return true;
        }
        return false;
    }

    public boolean isEmailValid(){
        if(email.length() == 0){
            return false;
        }
        else if(!email.matches(EMAIL_REGEXP)){
            return false;
        }
        return true;
    }

    public boolean isPhoneValid(){
        if(phone.length() == 0){
            return false;
        }
        else if(!phone.matches(PHONE_REGEXP)){
            return false;
        }
        return true;
    }

    public boolean isPasswordValid(){
        return password.matches(PASSWORD_REGEXP);
    }

    public boolean isConfirmPasswordValid(){
        return password.equals(confirmPassword);
    }


    public boolean isFirstNameValid(){
        if(firstName.length() == 0){
            return false;
        }
        return true;
    }

    public boolean isLastNameValid(){
        if(lastName.length() == 0){
            return false;
        }
        return true;
    }





    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getMedCenterId() {
        return medCenterId;
    }

    public void setMedCenterId(Long medCenterId) {
        this.medCenterId = medCenterId;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }


    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
