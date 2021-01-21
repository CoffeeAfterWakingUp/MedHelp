package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class ShowPharmacyOfMedicineByCityForm {
    private Long cityId;
    private Long medicineId;
    private String button;


    private static ShowPharmacyOfMedicineByCityForm instance;

    private ShowPharmacyOfMedicineByCityForm(){}


    public static ShowPharmacyOfMedicineByCityForm getInstance(){
        if(instance == null){
            instance = new ShowPharmacyOfMedicineByCityForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String idOfCity = request.getParameter(CITY_ID);
        String idOfMedicine = request.getParameter(MEDICINE_ID);
        this.button = request.getParameter(SHOW_PHARMACY_BY_CITY_BUTTON);
        if(idOfCity != null){
            cityId = Long.parseLong(idOfCity);
        }
        if(idOfMedicine != null){
            medicineId = Long.parseLong(idOfMedicine);
        }
    }


    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
