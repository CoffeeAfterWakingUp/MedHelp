package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_USER_SESSION;

public class LogOutUserService implements Service {

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_USER_SESSION,null);
        response.sendRedirect(MAIN_PAGE);
    }
}
