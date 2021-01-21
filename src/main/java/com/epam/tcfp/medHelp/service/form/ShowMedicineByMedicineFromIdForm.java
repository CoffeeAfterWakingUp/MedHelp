package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.MEDICINE_FROM_ID;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.SHOW_MEDICINE_FROM_BUTTON;

public class ShowMedicineByMedicineFromIdForm {
    private Long id;
    private String button;

    private static ShowMedicineByMedicineFromIdForm instance;

    private ShowMedicineByMedicineFromIdForm(){};

    public static ShowMedicineByMedicineFromIdForm getInstance(){
        if(instance == null){
            instance = new ShowMedicineByMedicineFromIdForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String medicineFromId = request.getParameter(MEDICINE_FROM_ID);
        this.button = request.getParameter(SHOW_MEDICINE_FROM_BUTTON);
        if(medicineFromId != null){
            this.id = Long.parseLong(medicineFromId);
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
