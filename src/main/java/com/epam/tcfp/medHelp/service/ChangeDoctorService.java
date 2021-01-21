package com.epam.tcfp.medHelp.service;

import com.epam.tcfp.medHelp.dao.factory.DAOFactory;
import com.epam.tcfp.medHelp.dao.impl.DoctorDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.MedCenterDAOImpl;
import com.epam.tcfp.medHelp.dao.impl.ProfessionDAOImpl;
import com.epam.tcfp.medHelp.dao.interfaces.DoctorDAO;
import com.epam.tcfp.medHelp.dao.interfaces.MedCenterDAO;
import com.epam.tcfp.medHelp.dao.interfaces.ProfessionDAO;
import com.epam.tcfp.medHelp.entity.Doctor;
import com.epam.tcfp.medHelp.entity.MedCenter;
import com.epam.tcfp.medHelp.entity.Profession;
import com.epam.tcfp.medHelp.service.form.ChangeDoctorForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.medHelp.service.ServiceName.SHOW_ALL_DOCTOR_SERVICE;

public class ChangeDoctorService implements Service{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DoctorDAO doctorDAO = (DoctorDAOImpl) daoFactory.getDAO("DOCTOR_DAO");
    private MedCenterDAO medCenterDAO = (MedCenterDAOImpl) daoFactory.getDAO("MED_CENTER_DAO");
    private ProfessionDAO professionDAO = (ProfessionDAOImpl) daoFactory.getDAO("PROFESSION_DAO");

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        ChangeDoctorForm form = ChangeDoctorForm.getInstance();
        form.setFormParameters(request);

        if(form.getButton() != null){
            Doctor doctor = new Doctor();
            MedCenter medCenter;
            Profession profession;
            doctor.setId(form.getId());
            doctor.setEmail(form.getEmail());
            doctor.setPassword(form.getPassword());
            doctor.setFirstName(form.getFirstName());
            doctor.setLastName(form.getLastName());
            doctor.setPhone(form.getPhone());
            medCenter = medCenterDAO.getByID(form.getMedCenterId());
            doctor.setMedCenter(medCenter);
            profession = professionDAO.getById(form.getProfessionId());
            doctor.setProfession(profession);
            doctor.setExperience(form.getExperience());
            doctor.setApproved(form.getApproved());
            doctor.setExist(form.getExist());
            doctorDAO.updateDoctor(doctor);
            response.sendRedirect(SHOW_ALL_DOCTOR_SERVICE);
        }
    }
}
