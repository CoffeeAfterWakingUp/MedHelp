package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.CityDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.ADD_MED_CENTER_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.ALL_CITY;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class GoAddMedCenterService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null){
            RequestDispatcher requestDispatcher;
            List<City> cities = cityDAO.getAll();
            request.setAttribute(ALL_CITY,cities);
            requestDispatcher = request.getRequestDispatcher(ADD_MED_CENTER_PAGE);
            requestDispatcher.forward(request, response);
        } else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
