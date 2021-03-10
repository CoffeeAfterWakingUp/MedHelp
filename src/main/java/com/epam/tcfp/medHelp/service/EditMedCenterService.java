package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.CityDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedCenterDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.MedCenter;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.EditDoctorForm;
import com.epam.tcfp.medHelp.service.form.IdForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.EDIT_MED_CENTER_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class EditMedCenterService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null){
            IdForm form = IdForm.getInstance();
            form.setFormParameters(request);
            if(form.getButton() != null){
                RequestDispatcher requestDispatcher;
                MedCenter medCenter = medCenterDAO.getByID(form.getId());
                List<City> cities = cityDAO.getAll();
                request.setAttribute(ALL_CITY,cities);
                request.setAttribute(MED_CENTER, medCenter);
                requestDispatcher = request.getRequestDispatcher(EDIT_MED_CENTER_PAGE);
                requestDispatcher.forward(request, response);
            }
        } else{
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
