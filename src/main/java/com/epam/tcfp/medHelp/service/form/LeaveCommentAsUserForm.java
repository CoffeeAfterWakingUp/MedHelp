package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class LeaveCommentAsUserForm {
    private Long medicineId;
    private Long userId;
    private String info;
    private String button;


    private static LeaveCommentAsUserForm instance;

    private LeaveCommentAsUserForm(){}

    public static LeaveCommentAsUserForm getInstance(){
        if(instance == null){
            instance = new LeaveCommentAsUserForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String commentUserId = request.getParameter(USER_ID);
        String commentMedicineId = request.getParameter(MEDICINE_ID);
        if(commentUserId != null){
            this.userId = Long.parseLong(commentUserId);
        }
        if(commentMedicineId != null){
            this.medicineId = Long.parseLong(commentMedicineId);
        }
        this.info = request.getParameter(INFO);
        this.button = request.getParameter(POST_COMMENT_BUTTON);
    }


    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
