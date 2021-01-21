package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorCommentMedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedicineDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorCommentMedicineDAO;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedicineDAO;
import com.epam.tcfp.medHelp.entity.Doctor;
import com.epam.tcfp.medHelp.entity.DoctorCommentMedicine;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.User;
import com.epam.tcfp.medHelp.service.form.LeaveCommentAsDoctorForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.COMMENT_AS_DOCTOR_PAGE_SERVICE;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.LOGIN_FOR_COMMENT_ERROR;
import static com.epam.tcfp.medHelp.util.constants.ErrorName.LOGIN_FOR_COMMENT_ERROR_MSG;
import static com.epam.tcfp.medHelp.util.constants.PageName.MAIN_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class LeaveCommentAsDoctorService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorCommentMedicineDAO doctorCommentMedicineDAO = (DoctorCommentMedicineDAOImpl) daoFactory.getDAO("DOCTOR_MEDICINE_COMMENT_DAO");
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();




    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        LeaveCommentAsDoctorForm form = LeaveCommentAsDoctorForm.getInstance();
        form.setFormParameters(request);
        DoctorCommentMedicine doctorCommentMedicine;
        Medicine medicine;
        Doctor doctor;
        Doctor currentDoctor = (Doctor) session.getAttribute(CURRENT_DOCTOR_SESSION);
        if(currentDoctor != null) {
            if (form.getButton() != null) {
                doctorCommentMedicine = new DoctorCommentMedicine();
                medicine = medicineDAO.getMedicineById(form.getMedicineId());
                doctor = doctorDAO.getById(form.getDoctorId());
                doctorCommentMedicine.setMedicine(medicine);
                doctorCommentMedicine.setDoctor(doctor);
                doctorCommentMedicine.setCons(form.getCons());
                doctorCommentMedicine.setInfo(form.getInfo());
                doctorCommentMedicine.setPros(form.getPros());
                doctorCommentMedicine.setEfficiency_rating(form.getEfficiencyRating());
                doctorCommentMedicine.setPriceAndQuality_rating(form.getPriceAndQualityRating());
                doctorCommentMedicine.setSideEffects_rating(form.getSideEffectsRating());
                doctorCommentMedicineDAO.create(doctorCommentMedicine);
                request.setAttribute(SUCCESSFUL_POST_COMMENT, SUCCESSFUL_POST_COMMENT_MSG);
                serviceFactory.getService(COMMENT_AS_DOCTOR_PAGE_SERVICE).perform(request,response);
            }
        }
        else {
            request.setAttribute(LOGIN_FOR_COMMENT_ERROR,LOGIN_FOR_COMMENT_ERROR_MSG);
            serviceFactory.getService(COMMENT_AS_DOCTOR_PAGE_SERVICE).perform(request,response);
        }

    }
}













