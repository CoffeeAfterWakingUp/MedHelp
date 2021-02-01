package com.epam.tcfp.medHelp.service;


import java.util.HashMap;
import java.util.Map;

import static com.epam.tcfp.medHelp.service.ServiceName.*;


public class ServiceFactory {
    private static ServiceFactory instance;
    private static Map<String,Service> SERVICES = new HashMap<>();

    private ServiceFactory(){}

    static{
        SERVICES.put(REGISTER_USER_SERVICE,new RegisterUserService());
        SERVICES.put(SHOW_ALL_PHARMACY_SERVICE,new ShowAllPharmacyService());
        SERVICES.put(SHOW_PHARMACY_BY_CITY_SERVICE,new ShowPharmacyByCityService());
        SERVICES.put(PHARMACY_SERVICE,new PharmacyService());
        SERVICES.put(MEDICINE_SERVICE,new MedicineService());
        SERVICES.put(SHOW_PHARMACY_OF_MEDICINE_BY_CITY_SERVICE,new ShowPharmacyOfMedicineByCityService());
        SERVICES.put(SHOW_ALL_MEDICINE_SERVICE, new ShowAllMedicineService());
        SERVICES.put(SHOW_MEDICINE_BY_MEDICINE_FOR_ID_SERVICE,new ShowMedicineByMedicineForIdService());
        SERVICES.put(SHOW_MEDICINE_BY_MEDICINE_FROM_ID_SERVICE,new ShowMedicineByMedicineFromIdService());
        SERVICES.put(SHOW_MEDICINE_BY_MEDICINE_HOW_ID_SERVICE,new ShowMedicineByMedicineHowIdService());
        SERVICES.put(LOGIN_USER_SERVICE,new LoginUserService());
        SERVICES.put(LOGOUT_USER_SERVICE,new LogOutUserService());
        SERVICES.put(LOGIN_DOCTOR_SERVICE,new LoginDoctorService());
        SERVICES.put(LOGOUT_DOCTOR_SERVICE,new LogOutDoctorService());
        SERVICES.put(COMMENT_AS_DOCTOR_PAGE_SERVICE,new CommentAsDoctorPageService());
        SERVICES.put(LEAVE_COMMENT_AS_DOCTOR_SERVICE,new LeaveCommentAsDoctorService());
        SERVICES.put(COMMENT_AS_USER_PAGE_SERVICE,new CommentAsUserPageService());
        SERVICES.put(LEAVE_COMMENT_AS_USER_SERVICE,new LeaveCommentAsUserService());
        SERVICES.put(SHOW_ALL_USERS_SERVICE,new ShowAllUsersService());
        SERVICES.put(EDIT_USER_SERVICE,new EditUserService());
        SERVICES.put(CHANGE_USER_SERVICE,new ChangeUserService());
        SERVICES.put(DELETE_USER_SERVICE,new DeleteUserService());
        SERVICES.put(GO_ADD_USER_SERVICE,new GoAddUserService());
        SERVICES.put(ADD_USER_SERVICE,new AddUserService());
        SERVICES.put(SHOW_ALL_DOCTOR_SERVICE,new ShowAllDoctorService());
        SERVICES.put(EDIT_DOCTOR_SERVICE,new EditDoctorService());
        SERVICES.put(CHANGE_DOCTOR_SERVICE,new ChangeDoctorService());
        SERVICES.put(DELETE_DOCTOR_SERVICE,new DeleteDoctorService());
        SERVICES.put(APPROVE_DOCTOR_SERVICE,new ApproveDoctorService());
        SERVICES.put(DISAPPROVE_DOCTOR_SERVICE,new DisapproveDoctorService());
        SERVICES.put(GO_ADD_DOCTOR_SERVICE,new GoAddDoctorService());
        SERVICES.put(ADD_DOCTOR_SERVICE,new AddDoctorService());
        SERVICES.put(LOGOUT_ADMIN_SERVICE,new LogOutAdminService());
        SERVICES.put(SHOW_ALL_MED_CENTERS_SERVICE,new ShowAllMedCenterService());
        SERVICES.put(SHOW_ALL_PHARMACY_FOR_ADMIN_SERVICE,new ShowAllPharmacyForAdminService());
        SERVICES.put(SHOW_ALL_MEDICINE_FOR_ADMIN_SERVICE,new ShowAllMedicineForAdminService());
        SERVICES.put(SHOW_ALL_MEDICINE_BY_PHARMACY_SERVICE,new ShowAllMedicineByPharmacyService());
        SERVICES.put(EDIT_MED_CENTER_SERVICE,new EditMedCenterService());
        SERVICES.put(CHANGE_MED_CENTER_SERVICE,new ChangeMedCenterService());
        SERVICES.put(DELETE_MED_CENTER_SERVICE,new DeleteMedCenterService());
        SERVICES.put(APPROVE_MED_CENTER_SERVICE,new ApproveMedCenterService());
        SERVICES.put(DISAPPROVE_MED_CENTER_SERVICE,new DisapproveMedCenterService());
        SERVICES.put(GO_ADD_MED_CENTER_SERVICE,new GoAddMedCenterService());
        SERVICES.put(ADD_MED_CENTER_SERVICE,new AddMedCenterService());
        SERVICES.put(EDIT_PHARMACY_SERVICE,new EditPharmacyService());
        SERVICES.put(CHANGE_PHARMACY_SERVICE,new ChangePharmacyService());
        SERVICES.put(DELETE_PHARMACY_SERVICE,new DeletePharmacyService());
        SERVICES.put(APPROVE_PHARMACY_SERVICE,new ApprovePharmacyService());
        SERVICES.put(DISAPPROVE_PHARMACY_SERVICE,new DisapprovePharmacyService());
        SERVICES.put(GO_ADD_PHARMACY_SERVICE,new GoAddPharmacyService());
        SERVICES.put(ADD_PHARMACY_SERVICE,new AddPharmacyService());
        SERVICES.put(EDIT_MEDICINE_BY_PHARMACY_SERVICE,new EditMedicineByPharmacyService());
        SERVICES.put(CHANGE_MEDICINE_BY_PHARMACY_SERVICE,new ChangeMedicineByPharmacyService());
        SERVICES.put(DELETE_MEDICINE_BY_PHARMACY_SERVICE,new DeleteMedicineByPharmacyService());
        SERVICES.put(GO_ADD_MEDICINE_BY_PHARMACY_SERVICE,new GoAddMedicineByPharmacyService());
        SERVICES.put(ADD_MEDICINE_BY_PHARMACY_SERVICE,new AddMedicineByPharmacyService());
        SERVICES.put(CHANGE_LANGUAGE_SERVICE,new ChangeLanguageService());
        SERVICES.put(GO_PAGE_NOT_FOUND_ERROR_SERVICE,new GoPageNotFoundErrorService());
    }

    public Service getService(String serviceName){
        Service service = SERVICES.get(GO_PAGE_NOT_FOUND_ERROR_SERVICE);

        if(serviceName != null){
            for(Map.Entry<String,Service> entry : SERVICES.entrySet()){
                if(serviceName.equals(entry.getKey())){
                    service = SERVICES.get(entry.getKey());
                }
            }
        }

        return service;
    }

    public static ServiceFactory getInstance(){
        if(instance == null){
            instance = new ServiceFactory();
        }
        return instance;
    }


}
