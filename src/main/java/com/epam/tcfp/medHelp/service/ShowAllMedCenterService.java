package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.MedCenterDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.entity.MedCenter;
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

import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MED_CENTERS_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.ALL_MED_CENTER;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class ShowAllMedCenterService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null) {
            List<MedCenter> medCenters = medCenterDAO.getAll();
            RequestDispatcher requestDispatcher;
            request.setAttribute(ALL_MED_CENTER,medCenters);
            requestDispatcher = request.getRequestDispatcher(MED_CENTERS_PAGE);
            requestDispatcher.forward(request, response);
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }

    }
}
