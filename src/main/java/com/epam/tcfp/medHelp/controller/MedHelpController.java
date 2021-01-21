package com.epam.tcfp.medHelp.controller;


import com.epam.tcfp.medHelp.service.Service;
import com.epam.tcfp.medHelp.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class MedHelpController extends HttpServlet {
    private static final long serialVersionUID =1L;

    private final static Logger LOGGER = LogManager.getLogger();

    public MedHelpController(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestString = request.getRequestURI();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        Service service = serviceFactory.getService(requestString);
        try{
            service.perform(request,response);
        } catch (ParseException | SQLException e) {
            LOGGER.error(e);
        }
    }
}
