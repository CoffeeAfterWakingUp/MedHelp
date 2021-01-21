package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.DOCTOR_ID;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.EDIT_USER_BUTTON;

public class EditDoctorForm {
    private Long id;
    private String button;

    private static EditDoctorForm instance;

    private EditDoctorForm(){}

    public static EditDoctorForm getInstance(){
        if(instance == null){
            instance = new EditDoctorForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String doctorId = request.getParameter(DOCTOR_ID);
        if(doctorId != null){
            this.id = Long.parseLong(doctorId);
        }
        this.button = request.getParameter(EDIT_USER_BUTTON);
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
