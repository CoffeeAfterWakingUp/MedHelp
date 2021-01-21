package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.MEDICINE_HOW_ID;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.SHOW_MEDICINE_HOW_BUTTON;

public class ShowMedicineByMedicineHowIdForm {
    private Long id;
    private String button;

    private static ShowMedicineByMedicineHowIdForm instance;

    private ShowMedicineByMedicineHowIdForm(){};

    public static ShowMedicineByMedicineHowIdForm getInstance(){
        if(instance == null){
            instance = new ShowMedicineByMedicineHowIdForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String medicineHowId = request.getParameter(MEDICINE_HOW_ID);
        this.button = request.getParameter(SHOW_MEDICINE_HOW_BUTTON);
        if(medicineHowId != null){
            this.id = Long.parseLong(medicineHowId);
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
