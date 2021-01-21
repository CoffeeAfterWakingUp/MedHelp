package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.FORM_DO_BUTTON;
import static com.epam.tcfp.medHelp.util.constants.ParametersName.DATA_EXIST;

public class MedicineByPharmacyForm {
    private Long id;
    private Long medicineId;
    private Long pharmacyId;
    private Integer price;
    private Boolean exist;
    private String button;


    private static MedicineByPharmacyForm instance;

    private MedicineByPharmacyForm(){}

    public static MedicineByPharmacyForm getInstance(){
        if(instance == null){
            instance = new MedicineByPharmacyForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String idAsString = request.getParameter(FORM_ID);
        String medicineIdAsString = request.getParameter(MEDICINE_ID);
        String pharmacyIdAsString = request.getParameter(PHARMACY_ID);
        if(idAsString != null){
            this.id = Long.parseLong(idAsString);
        }
        if(medicineIdAsString != null){
            this.medicineId = Long.parseLong(medicineIdAsString);
        }
        if(pharmacyIdAsString != null){
            this.pharmacyId = Long.parseLong(pharmacyIdAsString);
        }
        this.price = Integer.parseInt(request.getParameter(PRICE));
        this.exist = request.getParameter(FORM_EXIST).equals(DATA_EXIST);
        this.button = request.getParameter(FORM_DO_BUTTON);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
