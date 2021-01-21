package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.UserDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.LoginUserForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_USERS_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.*;
import static com.epam.tcfp.medHelp.util.constants.PageName.*;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class LoginUserService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        LoginUserForm form = LoginUserForm.getInstance();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(CURRENT_USER_SESSION);
        form.setFormParameters(request);


        if (form.getButton() != null) {
            if (!form.isUserExist()) {
                request.setAttribute(USER_EMAIL, form.getEmail());
                request.setAttribute(AUTH_NOT_VALID_ERROR, AUTH_NOT_VALID_ERROR_MSG);
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
                requestDispatcher.forward(request, response);
            }
            else {
                if(form.isAdmin()){
                    session.setAttribute(CURRENT_ADMIN_SESSION,userDAO.getUserByEmail(form.getEmail()));
                    response.sendRedirect(SHOW_ALL_USERS_SERVICE);
                }
                else{
                    session.setAttribute(CURRENT_USER_SESSION, userDAO.getUserByEmail(form.getEmail()));
                    response.sendRedirect(MAIN_PAGE);
                }
            }
        } else {
            System.out.println("error " + getClass().getName());
        }

    }
}
