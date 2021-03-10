package com.epam.tcfp.medHelp.service.form;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.UserDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.epam.tcfp.medHelp.util.constants.FormParameterName.*;

public class LoginUserForm {
    private String email;
    private String password;
    private String button;

    private static LoginUserForm instance;

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");

    private LoginUserForm(){}

    public static LoginUserForm getInstance(){
        if(instance == null){
            instance = new LoginUserForm();
        }
        return instance;
    }

    public void setFormParameters(HttpServletRequest request){
        String userEmail = request.getParameter(EMAIL);
        if(userEmail != null){
            this.email = userEmail.trim();
        }
        this.password = DigestUtils.md5Hex(request.getParameter(PASSWORD));
        this.button = request.getParameter(LOGIN_AS_USER_BUTTON);
    }

    public boolean isUserExist() throws SQLException {
        User user = userDAO.getUserByEmailAndPassword(email,password);
        return user != null;
    }

    public boolean isAdmin() throws SQLException{
        User user = userDAO.getUserByEmailAndPassword(email,password);
        return user.getRole().equalsIgnoreCase(ADMIN);
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

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
