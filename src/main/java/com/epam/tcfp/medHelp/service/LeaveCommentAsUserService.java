package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.MedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.UserCommentMedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.UserDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.UserCommentMedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.UserDAO;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.entity.UserCommentMedicine;
import com.epam.tcfp.medHelp.service.form.LeaveCommentAsUserForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.COMMENT_AS_USER_PAGE_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.LOGIN_FOR_COMMENT_ERROR;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.LOGIN_FOR_COMMENT_ERROR_MSG;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class LeaveCommentAsUserService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private UserDAO userDAO = (UserDAOImpl) daoFactory.getDAO("USER_DAO");
    private UserCommentMedicineDAO userCommentMedicineDAO = (UserCommentMedicineDAOImpl) daoFactory.getDAO("USER_COMMENT_MEDICINE_DAO");



    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        UserCommentMedicine comment;
        Medicine medicine;
        User user;
        LeaveCommentAsUserForm form = LeaveCommentAsUserForm.getInstance();
        form.setFormParameters(request);
        User currentUser = (User)session.getAttribute(CURRENT_USER_SESSION);
        if(currentUser != null) {
            if (form.getButton() != null) {
                comment = new UserCommentMedicine();
                medicine = medicineDAO.getMedicineById(form.getMedicineId());
                user = userDAO.getById(form.getUserId());
                comment.setMedicine(medicine);
                comment.setUser(user);
                comment.setInfo(form.getInfo());
                userCommentMedicineDAO.create(comment);
                request.setAttribute(SUCCESSFUL_POST_COMMENT, SUCCESSFUL_POST_COMMENT_MSG);
                serviceFactory.getService(COMMENT_AS_USER_PAGE_SERVICE).perform(request,response);
            }
        }else{
            request.setAttribute(LOGIN_FOR_COMMENT_ERROR,LOGIN_FOR_COMMENT_ERROR_MSG);
            serviceFactory.getService(COMMENT_AS_USER_PAGE_SERVICE).perform(request,response);
        }
    }
}
