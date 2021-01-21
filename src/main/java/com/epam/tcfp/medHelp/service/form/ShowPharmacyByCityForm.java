package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.CITY_ID;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.SHOW_PHARMACY_BY_CITY_BUTTON;

public class ShowPharmacyByCityForm {
    private Long id;
    private String button;

    private static ShowPharmacyByCityForm instance;


    private ShowPharmacyByCityForm(){}


    public static ShowPharmacyByCityForm getInstance(){
        if(instance == null){
            instance = new ShowPharmacyByCityForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String cityId = request.getParameter(CITY_ID);
        this.button = request.getParameter(SHOW_PHARMACY_BY_CITY_BUTTON);
        if(cityId != null){
            this.id = Long.parseLong(cityId);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
