package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.FORM_DO_BUTTON;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.FORM_ID;


public class IdForm {
    private Long id;
    private String button;


    private static IdForm instance;

    private IdForm(){}

    public static IdForm getInstance(){
        if(instance == null){
            instance = new IdForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String idAsString = request.getParameter(FORM_ID);
        if(idAsString != null){
            this.id = Long.parseLong(idAsString);
        }
        this.button = request.getParameter(FORM_DO_BUTTON);
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
