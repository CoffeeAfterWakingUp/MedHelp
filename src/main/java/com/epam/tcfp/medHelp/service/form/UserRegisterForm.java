package com.epam.tcfp.medHelp.service.form;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.util.resourceManager.RegexResourceManager;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class UserRegisterForm {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String confirmPassword;
    private String button;


    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO("USER_DAO");

    private static RegexResourceManager regexResourceManager = RegexResourceManager.getInstance();
    private static final String EMAIL_REGEXP = regexResourceManager.getValue("email.regexp");
    private static final String PASSWORD_REGEXP = regexResourceManager.getValue("password.regexp");
    private static final String PHONE_REGEXP = regexResourceManager.getValue("phone.regexp");







    public void setFormParameters(HttpServletRequest request){
        String userEmail = request.getParameter(EMAIL);
        String userFirstName = request.getParameter(FIRST_NAME);
        String userLastName = request.getParameter(LAST_NAME);
        String userPhone = request.getParameter(PHONE);
        if(userEmail != null){
            this.email = userEmail.trim();
        }
        if(userFirstName != null){
            this.firstName = userFirstName.trim();
        }
        if(userLastName != null){
            this.lastName = userLastName.trim();
        }
        if(userPhone!=null){
            this.phone = userPhone.trim();
        }

        this.password = request.getParameter(PASSWORD);
        this.confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        this.button = request.getParameter(REGISTER_BUTTON);
    }


    public boolean isEmailExist() throws SQLException {
        if(userDAO.getUserByEmail(email) == null){
            return false;
        }
        return true;
    }

    public boolean isEmailValid(){
        if(email.length() == 0){
            return false;
        }
        else if(!email.matches(EMAIL_REGEXP)){
            return false;
        }
       return true;
    }

    public boolean isPhoneValid(){
        if(phone.length() == 0){
            return false;
        }
        else if(!phone.matches(PHONE_REGEXP)){
            return false;
        }
        return true;
    }

    public boolean isPasswordValid(){
        return password.matches(PASSWORD_REGEXP);
    }

    public boolean isConfirmPasswordValid(){
        return password.equals(confirmPassword);
    }

    public boolean isFirstNameValid(){
        if(firstName.length() == 0){
            return false;
        }
        return true;
    }

    public boolean isLastNameValid(){
        if(lastName.length() == 0){
            return false;
        }
        return true;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
