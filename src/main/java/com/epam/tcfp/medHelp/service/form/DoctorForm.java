package com.epam.tcfp.medHelp.service.form;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.util.resourceManager.RegexResourceManager;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.EXPERIENCE;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DATA_EXIST;

public class DoctorForm {
    private Long id;
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String phone;
    private Long medCenterId;
    private Long professionId;
    private Integer experience;
    private Boolean approved = false;
    private Boolean exist = true;
    private String button;

    private static DoctorForm instance;

    private static RegexResourceManager regexResourceManager = RegexResourceManager.getInstance();
    private static final String EMAIL_REGEXP = regexResourceManager.getValue("email.regexp");
    private static final String PASSWORD_REGEXP = regexResourceManager.getValue("password.regexp");
    private static final String PHONE_REGEXP = regexResourceManager.getValue("phone.regexp");

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");


    private DoctorForm(){}

    public static DoctorForm getInstance(){
        if(instance == null){
            instance = new DoctorForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String doctorId = request.getParameter(FORM_ID);
        String doctorEmail = request.getParameter(EMAIL);
        String doctorFirstName = request.getParameter(FIRST_NAME);
        String doctorLastName = request.getParameter(LAST_NAME);
        String doctorPhone = request.getParameter(PHONE);
        this.password = request.getParameter(PASSWORD);
        this.confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        this.medCenterId = Long.parseLong(request.getParameter(MED_CENTER_ID));
        this.professionId = Long.parseLong(request.getParameter(PROFESSION_ID));
        this.experience = Integer.parseInt(request.getParameter(EXPERIENCE));
        this.approved = request.getParameter(FORM_APPROVED).equals(APPROVED);;
        this.exist = request.getParameter(FORM_EXIST).equals(DATA_EXIST);
        this.button = request.getParameter(FORM_DO_BUTTON);

        if(doctorId != null){
            this.id = Long.parseLong(doctorId);
        }
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
    }

    public boolean isEmailExist() throws SQLException {
        return doctorDAO.getByEmail(email) != null;
    }

    public boolean isEmailValid(){
        if(email.length() == 0){
            return false;
        }
        else return email.matches(EMAIL_REGEXP);
    }

    public boolean isPhoneValid(){
        if(phone.length() == 0){
            return false;
        }
        else return phone.matches(PHONE_REGEXP);
    }

    public boolean isPasswordValid(){
        return password.matches(PASSWORD_REGEXP);
    }

    public boolean isConfirmPasswordValid(){
        return password.equals(confirmPassword);
    }


    public boolean isFirstNameValid(){
        return firstName.length() != 0;
    }

    public boolean isLastNameValid(){
        return lastName.length() != 0;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
