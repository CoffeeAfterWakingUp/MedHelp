package com.epam.tcfp.medHelp.service.form;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;
import static com.epam.tcfp.medHelp.util.constants.FormParameterName.CHANGE_USER_BUTTON;

public class AddUserForm {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String role;
    private Boolean exist;
    private String button;

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO("USER_DAO");

    private static AddUserForm instance;

    private AddUserForm(){}

    public static AddUserForm getInstance(){
        if(instance == null){
            instance = new AddUserForm();
        }
        return instance;
    }


    public void setFormParameters(HttpServletRequest request){
        String userExist = request.getParameter(EXIST);
        exist = userExist.equals(USER_EXIST);
        this.email = request.getParameter(EMAIL);
        this.password = request.getParameter(PASSWORD);
        this.firstName = request.getParameter(FIRST_NAME);
        this.lastName = request.getParameter(LAST_NAME);
        this.phone = request.getParameter(PHONE);
        this.role = request.getParameter(ROLE);
        this.button = request.getParameter(ADD_USER_BUTTON);
    }

    public boolean isEmailExist() throws SQLException {
        return userDAO.getUserByEmail(email) != null;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
