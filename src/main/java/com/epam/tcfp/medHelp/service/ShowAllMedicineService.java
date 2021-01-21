package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.*;
import com.epam.tcfp.medHelp.dao.interfaces.*;
import com.epam.tcfp.medHelp.entity.Medicine;
import com.epam.tcfp.medHelp.entity.MedicineFor;
import com.epam.tcfp.medHelp.entity.MedicineFrom;
import com.epam.tcfp.medHelp.entity.MedicineHow;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.ALL_MEDICINE_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class ShowAllMedicineService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private MedicineForDAO medicineForDAO = (MedicineForDAOImpl) daoFactory.getDAO("MEDICINE_FOR_DAO");
    private MedicineFromDAO medicineFromDAO = (MedicineFromDAOImpl) daoFactory.getDAO("MEDICINE_FROM_DAO");
    private MedicineHowDAO medicineHowDAO = (MedicineHowDAOImpl) daoFactory.getDAO("MEDICINE_HOW_DAO");
    private DoctorCommentMedicineDAO doctorCommentMedicineDAO = (DoctorCommentMedicineDAOImpl) daoFactory.getDAO("DOCTOR_MEDICINE_COMMENT_DAO");




    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher requestDispatcher;
        List<Medicine> medicines;
        List<MedicineFor> medicineForGroup;
        List<MedicineHow> medicineHowGroup;
        List<MedicineFrom> medicineFromGroup;
        medicines = medicineDAO.getAll();
        medicineForGroup = medicineForDAO.getAll();
        medicineHowGroup = medicineHowDAO.getAll();
        medicineFromGroup = medicineFromDAO.getAll();


        request.setAttribute(ALL_MEDICINE,medicines);
        request.setAttribute(ALL_MEDICINE_FOR_GROUP,medicineForGroup);
        request.setAttribute(ALL_MEDICINE_HOW_GROUP,medicineHowGroup);
        request.setAttribute(ALL_MEDICINE_FROM_GROUP,medicineFromGroup);
        requestDispatcher = request.getRequestDispatcher(ALL_MEDICINE_PAGE);
        requestDispatcher.forward(request,response);
    }
}
