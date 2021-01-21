package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.MedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.service.form.CommentAsUserForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.util.constants.PageName.USER_COMMENT_MEDICINE_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class CommentAsUserPageService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        CommentAsUserForm form = CommentAsUserForm.getInstance();
        form.setFormParameters(request);
        request.setAttribute(MEDICINE_ID,form.getMedicineId());
        request.setAttribute(MEDICINE_NAME,medicineDAO.getMedicineById(form.getMedicineId()).getName());
        request.setAttribute(USER_ID,form.getUserId());
        requestDispatcher = request.getRequestDispatcher(USER_COMMENT_MEDICINE_PAGE);
        requestDispatcher.forward(request,response);
    }
}
