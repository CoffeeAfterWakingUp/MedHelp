package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.DELETE_USER_BUTTON;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.USER_ID;

public class DeleteUserForm {
    private Long id;
    private String button;

    private static DeleteUserForm instance;

    private DeleteUserForm(){}

    public static DeleteUserForm getInstance(){
        if(instance == null){
            instance = new DeleteUserForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        this.id = Long.parseLong(request.getParameter(USER_ID));
        this.button = request.getParameter(DELETE_USER_BUTTON);
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
