package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class LeaveCommentAsDoctorForm {
    private Long medicineId;
    private Long doctorId;
    private String pros;
    private String cons;
    private String info;
    private Integer efficiencyRating;
    private Integer priceAndQualityRating;
    private Integer sideEffectsRating;
    private String button;

    private static LeaveCommentAsDoctorForm instance;

    private LeaveCommentAsDoctorForm(){}

    public static LeaveCommentAsDoctorForm getInstance(){
        if(instance == null){
            instance = new LeaveCommentAsDoctorForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String commentMedicineId = request.getParameter(MEDICINE_ID);
        String commentDoctorId = request.getParameter(DOCTOR_ID);
        String commentEfficiencyRating = request.getParameter(EFFICIENCY_RATING);
        String commentPriceAndQualityRating = request.getParameter(PRICE_AND_QUALITY_RATING);
        String commentSideEffectsRating = request.getParameter(SIDE_EFFECTS_RATING);
        if(commentMedicineId != null){
            this.medicineId = Long.parseLong(commentMedicineId);
        }
        if(commentDoctorId != null){
            this.doctorId = Long.parseLong(commentDoctorId);
        }
        if(commentEfficiencyRating != null){
            this.efficiencyRating = Integer.parseInt(commentEfficiencyRating);
        }
        if(commentPriceAndQualityRating != null){
            this.priceAndQualityRating = Integer.parseInt(commentPriceAndQualityRating);
        }
        if(commentSideEffectsRating != null){
            this.sideEffectsRating = Integer.parseInt(commentSideEffectsRating);
        }
        this.pros = request.getParameter(PROS);
        this.cons = request.getParameter(CONS);
        this.info = request.getParameter(INFO);
        this.button = request.getParameter(POST_COMMENT_BUTTON);
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

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getEfficiencyRating() {
        return efficiencyRating;
    }

    public void setEfficiencyRating(Integer efficiencyRating) {
        this.efficiencyRating = efficiencyRating;
    }

    public Integer getPriceAndQualityRating() {
        return priceAndQualityRating;
    }

    public void setPriceAndQualityRating(Integer priceAndQualityRating) {
        this.priceAndQualityRating = priceAndQualityRating;
    }

    public Integer getSideEffectsRating() {
        return sideEffectsRating;
    }

    public void setSideEffectsRating(Integer sideEffectsRating) {
        this.sideEffectsRating = sideEffectsRating;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
