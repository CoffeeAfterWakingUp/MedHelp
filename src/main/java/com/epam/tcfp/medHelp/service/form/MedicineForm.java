package com.epam.tcfp.medHelp.service.form;


import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.MEDICINE_ID;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.SHOW_MEDICINE_BUTTON;

public class MedicineForm {
    private Long id;
    private String button;

    private static MedicineForm instance;

    private MedicineForm(){}

    public static MedicineForm getInstance(){
        if(instance == null){
            instance = new MedicineForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String medicineId = request.getParameter(MEDICINE_ID);
        this.button = request.getParameter(SHOW_MEDICINE_BUTTON);
        if(medicineId != null){
            this.id = Long.parseLong(medicineId);
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
