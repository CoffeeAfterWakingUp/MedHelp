package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.MEDICINE_FOR_ID;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.SHOW_MEDICINE_FOR_BUTTON;

public class ShowMedicineByMedicineForIdForm {
    private Long id;
    private String button;

    private static ShowMedicineByMedicineForIdForm instance;

    private ShowMedicineByMedicineForIdForm(){};

    public static ShowMedicineByMedicineForIdForm getInstance(){
        if(instance == null){
            instance = new ShowMedicineByMedicineForIdForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String medicineForId = request.getParameter(MEDICINE_FOR_ID);
        this.button = request.getParameter(SHOW_MEDICINE_FOR_BUTTON);
        if(medicineForId != null){
            this.id = Long.parseLong(medicineForId);
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
