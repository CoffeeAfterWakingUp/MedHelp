package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.UserDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.EditUserForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.util.constants.PageName.EDIT_USER_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.USER;

public class EditUserService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        EditUserForm form = EditUserForm.getInstance();
        form.setFormParameters(request);
        User user;
        if(admin != null) {
            if (form.getButton() != null) {
                user = userDAO.getById(form.getId());
                request.setAttribute(USER, user);
                requestDispatcher = request.getRequestDispatcher(EDIT_USER_PAGE);
                requestDispatcher.forward(request, response);
            }
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
