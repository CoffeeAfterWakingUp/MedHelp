package com.epam.tcfp.medHelp.service.form;



import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.PHARMACY_ID;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.SHOW_PHARMACY_BY_ID;

public class PharmacyForm {
    private Long id;
    private String button;

    private static PharmacyForm instance;

    private PharmacyForm(){}

    public static PharmacyForm getInstance(){
        if(instance == null){
            instance = new PharmacyForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String pharmacyId = request.getParameter(PHARMACY_ID);
        this.button = request.getParameter(SHOW_PHARMACY_BY_ID);
        if(pharmacyId != null){
            this.id = Long.parseLong(pharmacyId);
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
