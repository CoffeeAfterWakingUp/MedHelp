package com.epam.tcfp.medHelp.dao.factory;


import com.epam.tcfp.medHelp.dao.impl.*;
import com.epam.tcfp.medHelp.dao.interfaces.MainDAO;

public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if(instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public MainDAO getDAO(String type){
        if(type == null){
            return null;
        }
        if(type.equalsIgnoreCase("USER_DAO")){
            return new UserDAOImpl();
        }
        else if(type.equalsIgnoreCase("CITY_DAO")){
            return new CityDAOImpl();
        }
        else if(type.equalsIgnoreCase("MED_CENTER_DAO")){
            return new MedCenterDAOImpl();
        }
        else if(type.equalsIgnoreCase("PROFESSION_DAO")){
            return new ProfessionDAOImpl();
        }
        else if(type.equalsIgnoreCase("DOCTOR_DAO")){
            return new DoctorDAOImpl();
        }
        else if(type.equalsIgnoreCase("PHARMACY_DAO")){
            return new PharmacyDAOImpl();
        }
        else if(type.equalsIgnoreCase("MEDICINE_DAO")){
            return new MedicineDAOImpl();
        }
        else if(type.equalsIgnoreCase("MEDICINE_FOR_DAO")){
            return new MedicineForDAOImpl();
        }
        else if(type.equalsIgnoreCase("MEDICINE_FROM_DAO")){
            return new MedicineFromDAOImpl();
        }
        else if(type.equalsIgnoreCase("MEDICINE_HOW_DAO")){
            return new MedicineHowDAOImpl();
        }
        else if(type.equalsIgnoreCase("MEDICINE_BY_PHARMACY_DAO")){
            return new MedicineByPharmacyDAOImpl();
        }
        else if(type.equalsIgnoreCase("DOCTOR_MEDICINE_COMMENT_DAO")){
            return new DoctorCommentMedicineDAOImpl();
        }
        else if(type.equalsIgnoreCase("USER_COMMENT_MEDICINE_DAO")){
            return new UserCommentMedicineDAOImpl();
        }


        return null;
    }

}
