package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class CommentAsDoctorForm {
    private Long medicineId;
    private Long doctorId;
    private String button;


    private static CommentAsDoctorForm instance;

    private CommentAsDoctorForm(){}

    public static CommentAsDoctorForm getInstance(){
        if(instance == null){
            instance = new CommentAsDoctorForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String commentDoctorId = request.getParameter(DOCTOR_ID);
        String commentMedicineId = request.getParameter(MEDICINE_ID);
        if(commentDoctorId != null){
            this.doctorId = Long.parseLong(commentDoctorId);
        }
        if(commentMedicineId != null){
            this.medicineId = Long.parseLong(commentMedicineId);
        }
        this.button = request.getParameter(COMMENT_AS_DOCTOR_BUTTON);
    }


    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
