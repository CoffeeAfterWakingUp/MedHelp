package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.UserDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.ChangeUserForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_USERS_SERVICE;

public class ChangeUserService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        ChangeUserForm form = ChangeUserForm.getInstance();
        form.setFormParameters(request);
        User user;
        if(form.getButton() != null){
            user = new User();
            user.setId(form.getId());
            user.setLastName(form.getLastName());
            user.setFirstName(form.getFirstName());
            user.setExist(form.getExist());
            user.setPhone(form.getPhone());
            user.setPassword(form.getPassword());
            user.setRole(form.getRole());
            user.setEmail(form.getEmail());
            userDAO.updateUser(user);
            response.sendRedirect(SHOW_ALL_USERS_SERVICE);
        }

    }
}
