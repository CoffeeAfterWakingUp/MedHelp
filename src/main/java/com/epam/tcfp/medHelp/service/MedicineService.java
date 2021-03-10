package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.*;
import com.epam.tcfp.medHelp.dao.interfaces.*;

import com.epam.tcfp.medHelp.entity.*;
import com.epam.tcfp.medHelp.service.form.MedicineForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.tcfp.medHelp.util.constants.PageName.INTERNAL_SERVER_ERROR_PAGE;
import static com.epam.tcfp.medHelp.util.constants.PageName.MEDICINE_PAGE;
import static com.epam.tcfp.medHelp.util.constants.RequestParameterName.*;

public class MedicineService implements Service {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MedicineDAO medicineDAO = (MedicineDAOImpl) daoFactory.getDAO("MEDICINE_DAO");
    private MedicineByPharmacyDAO medicineByPharmacyDAO = (MedicineByPharmacyDAOImpl) daoFactory.getDAO("MEDICINE_BY_PHARMACY_DAO");
    private CityDAO cityDAO = (CityDAOImpl) daoFactory.getDAO("CITY_DAO");
    private DoctorCommentMedicineDAO doctorCommentMedicineDAO = (DoctorCommentMedicineDAOImpl) daoFactory.getDAO("DOCTOR_MEDICINE_COMMENT_DAO");
    private UserCommentMedicineDAO userCommentMedicineDAO = (UserCommentMedicineDAOImpl) daoFactory.getDAO("USER_COMMENT_MEDICINE_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        MedicineForm medicineForm = MedicineForm.getInstance();
        Medicine medicine;
        List<MedicineByPharmacy> medicineByPharmacies;
        List<City> cities;
        List<DoctorCommentMedicine> doctorComments;
        List<UserCommentMedicine> userComments;
        Integer avgEfficiencyRating;
        Integer avgSideEffectsRating;
        Integer avgPriceAndQualityRating;
        RequestDispatcher requestDispatcher;
        medicineForm.setFormParameters(request);

        medicine = medicineDAO.getMedicineById(medicineForm.getId());
        medicineByPharmacies = medicineByPharmacyDAO.getPharmacyByMedicineId(medicineForm.getId());
        cities = cityDAO.getAll();
        doctorComments = doctorCommentMedicineDAO.getByMedicineId(medicineForm.getId());
        userComments = userCommentMedicineDAO.getByMedicineId(medicineForm.getId());
        avgEfficiencyRating = doctorCommentMedicineDAO.getAverageEfficiencyRating(medicineForm.getId());
        avgSideEffectsRating = doctorCommentMedicineDAO.getAverageSideEffectsRating(medicineForm.getId());
        avgPriceAndQualityRating = doctorCommentMedicineDAO.getAveragePriceAndQualityRating(medicineForm.getId());

        request.setAttribute(AVG_EFFICIENCY_RATING, avgEfficiencyRating);
        request.setAttribute(AVG_SIDE_EFFECTS_RATING, avgSideEffectsRating);
        request.setAttribute(AVG_PRICE_AND_QUALITY_RATING, avgPriceAndQualityRating);
        request.setAttribute(DOCTOR_COMMENTS_ON_MEDICINE, doctorComments);
        request.setAttribute(USER_COMMENTS_ON_MEDICINE, userComments);
        request.setAttribute(MEDICINE, medicine);
        request.setAttribute(PHARMACY_OF_MEDICINE, medicineByPharmacies);
        request.setAttribute(ALL_CITY, cities);
        requestDispatcher = request.getRequestDispatcher(MEDICINE_PAGE);
        requestDispatcher.forward(request, response);
    }
}

