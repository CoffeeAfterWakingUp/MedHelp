package com.epam.tcfp.medHelp.service.form;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DATA_EXIST;


public class ChangeDoctorForm {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Long medCenterId;
    private Long professionId;
    private Integer experience;
    private Boolean approved;
    private Boolean exist;
    private String button;

    private static ChangeDoctorForm instance;
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");

    private ChangeDoctorForm(){}

    public static ChangeDoctorForm getInstance(){
        if(instance == null){
            instance = new ChangeDoctorForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        this.id = Long.parseLong(request.getParameter(FORM_ID));
        this.email = request.getParameter(EMAIL);
        this.password = request.getParameter(PASSWORD);
        this.firstName = request.getParameter(FIRST_NAME);
        this.lastName = request.getParameter(LAST_NAME);
        this.phone = request.getParameter(PHONE);
        this.medCenterId = Long.parseLong(request.getParameter(MED_CENTER_ID));
        this.professionId = Long.parseLong(request.getParameter(PROFESSION_ID));
        this.experience = Integer.parseInt(request.getParameter(EXPERIENCE));
        this.approved = request.getParameter(FORM_APPROVED).equals(APPROVED);
        this.exist = request.getParameter(FORM_EXIST).equals(DATA_EXIST);
        this.button = request.getParameter(FORM_DO_BUTTON);
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

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
