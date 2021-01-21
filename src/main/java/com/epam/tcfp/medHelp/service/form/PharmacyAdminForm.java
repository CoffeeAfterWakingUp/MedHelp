package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.FORM_DO_BUTTON;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DATA_EXIST;

public class PharmacyAdminForm {
    private Long id;
    private String name;
    private Long cityId;
    private String address;
    private String phone;
    private Boolean approved;
    private Boolean exist;
    private String button;


    private static PharmacyAdminForm instance;

    private PharmacyAdminForm(){}

    public static PharmacyAdminForm getInstance(){
        if(instance == null){
            instance = new PharmacyAdminForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String idAsString = request.getParameter(FORM_ID);
        String cityIdAsString = request.getParameter(CITY_ID);
        if(idAsString != null){
            this.id = Long.parseLong(idAsString);
        }
        if(cityIdAsString != null){
            this.cityId = Long.parseLong(cityIdAsString);
        }
        this.name = request.getParameter(NAME);
        this.phone = request.getParameter(PHONE);
        this.address = request.getParameter(ADDRESS);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
