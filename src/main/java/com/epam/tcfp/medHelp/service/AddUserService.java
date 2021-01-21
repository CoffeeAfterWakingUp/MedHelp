package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.UserDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.AddUserForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_USERS_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.EMAIL_ALREADY_TAKEN_ERROR;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.EMAIL_ALREADY_TAKEN_ERROR_MSG;
import static com.epam.tcfp.medHelp.util.constants.PageName.ADD_USER_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class AddUserService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        AddUserForm form = AddUserForm.getInstance();
        form.setFormParameters(request);
        if(admin != null) {
            if (form.getButton() != null) {
                if (form.isEmailExist()) {
                    request.setAttribute(EMAIL_ALREADY_TAKEN_ERROR, EMAIL_ALREADY_TAKEN_ERROR_MSG);
                    requestDispatcher = request.getRequestDispatcher(ADD_USER_PAGE);
                    requestDispatcher.forward(request, response);
                } else {
                    User user = new User();
                    user.setEmail(form.getEmail());
                    user.setRole(form.getRole());
                    user.setPassword(form.getPassword());
                    user.setPhone(form.getPhone());
                    user.setFirstName(form.getFirstName());
                    user.setLastName(form.getLastName());
                    user.setExist(form.getExist());
                    userDAO.createUser(user);
                    response.sendRedirect(SHOW_ALL_USERS_SERVICE);
                }
            }
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
