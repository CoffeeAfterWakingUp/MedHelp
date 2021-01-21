package com.epam.tcfp.medHelp.service.form;

import javax.servlet.http.HttpServletRequest;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class ChangeUserForm {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private Boolean exist;
    private String button;

    private static ChangeUserForm instance;

    private ChangeUserForm(){}

    public static ChangeUserForm getInstance(){
        if(instance == null){
            instance = new ChangeUserForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String userExist = request.getParameter(EXIST);
        this.id = Long.parseLong(request.getParameter(USER_ID));
        exist = userExist.equals(USER_EXIST);
        this.email = request.getParameter(EMAIL);
        this.password = request.getParameter(PASSWORD);
        this.firstName = request.getParameter(FIRST_NAME);
        this.lastName = request.getParameter(LAST_NAME);
        this.phone = request.getParameter(PHONE);
        this.role = request.getParameter(ROLE);
        this.button = request.getParameter(CHANGE_USER_BUTTON);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
