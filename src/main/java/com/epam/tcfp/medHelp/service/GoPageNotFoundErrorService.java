package com.epam.tcfp.medHelp.service;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.util.constants.PageName.PAGE_NOT_FOUND_ERROR_PAGE;

public class GoPageNotFoundErrorService implements Service {
    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher(PAGE_NOT_FOUND_ERROR_PAGE);
        requestDispatcher.forward(request,response);
    }
}
