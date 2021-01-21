package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DATA_EXIST;

public class MedCenterForm {
    private Long id;
    private String name;
    private Long cityId;
    private String phone;
    private String address;
    private String medInstitution;
    private Boolean approved;
    private Boolean exist;
    private String button;

    private static MedCenterForm instance;

    private MedCenterForm(){}

    public static MedCenterForm getInstance(){
        if(instance == null){
            instance = new MedCenterForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String medCenterId = request.getParameter(FORM_ID);
        String cityIdAsString = request.getParameter(CITY_ID);
        if(medCenterId != null){
            this.id = Long.parseLong(medCenterId);
        }
        if(cityIdAsString != null){
            this.cityId = Long.parseLong(cityIdAsString);
        }
        this.name = request.getParameter(NAME);
        this.phone = request.getParameter(PHONE);
        this.address = request.getParameter(ADDRESS);
        this.medInstitution = request.getParameter(MED_INSTITUTION);
        this.approved = request.getParameter(FORM_APPROVED).equals(APPROVED);;
        this.exist = request.getParameter(FORM_EXIST).equals(DATA_EXIST);
        this.button = request.getParameter(FORM_DO_BUTTON);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedInstitution() {
        return medInstitution;
    }

    public void setMedInstitution(String medInstitution) {
        this.medInstitution = medInstitution;
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
