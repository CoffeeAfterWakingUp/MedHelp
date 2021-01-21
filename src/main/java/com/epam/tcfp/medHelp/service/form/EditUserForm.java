package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.EDIT_USER_BUTTON;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.USER_ID;

public class EditUserForm {
    private Long id;
    private String button;

    private static EditUserForm instance;

    private EditUserForm(){}

    public static EditUserForm getInstance(){
        if(instance == null){
            instance = new EditUserForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String userId = request.getParameter(USER_ID);
        if(userId != null){
            this.id = Long.parseLong(userId);
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
