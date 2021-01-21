package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.CityDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedCenterDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.CityDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.entity.City;
import com.epam.tcfp.medHelp.entity.MedCenter;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.MedCenterForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_MED_CENTERS_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.CURRENT_ADMIN_SESSION;

public class ChangeMedCenterService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User admin = (User)session.getAttribute(CURRENT_ADMIN_SESSION);
        if(admin != null){
            MedCenterForm form = MedCenterForm.getInstance();
            form.setFormParameters(request);
            if(form.getButton() != null){
                MedCenter medCenter = new MedCenter();
                City city = cityDAO.getCityById(form.getCityId());
                medCenter.setId(form.getId());
                medCenter.setName(form.getName());
                medCenter.setCity(city);
                medCenter.setPhone(form.getPhone());
                medCenter.setAddress(form.getAddress());
                medCenter.setMedInstitution(form.getMedInstitution());
                medCenter.setApproved(form.getApproved());
                medCenter.setExist(form.getExist());
                medCenterDAO.update(medCenter);
                response.sendRedirect(SHOW_ALL_MED_CENTERS_SERVICE);
            }
        }
        else{
            response.sendRedirect(MAIN_PAGE);
        }

    }
}
