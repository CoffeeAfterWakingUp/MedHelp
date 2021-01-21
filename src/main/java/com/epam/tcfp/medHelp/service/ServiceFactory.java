package com.epam.tcfp.medHelp.service;


import static com.epam.tcfp.medHelp.service.ServiceName.*;


public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){

    }

    public Service getService(String serviceName){
        Service service = null;
        if(serviceName != null){
            if(serviceName.equals(REGISTER_USER_SERVICE)){
                service = new RegisterUserService();
            }
            else if(serviceName.equals(SHOW_ALL_PHARMACY_SERVICE)){
                service = new ShowAllPharmacyService();
            }
            else if(serviceName.equals(SHOW_PHARMACY_BY_CITY_SERVICE)){
                service = new ShowPharmacyByCityService();
            }
            else if(serviceName.equals(PHARMACY_SERVICE)){
                service = new PharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(MEDICINE_SERVICE)){
                service = new MedicineService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_PHARMACY_OF_MEDICINE_BY_CITY_SERVICE)){
                service = new ShowPharmacyOfMedicineByCityService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_ALL_MEDICINE_SERVICE)){
                service = new ShowAllMedicineService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_MEDICINE_BY_MEDICINE_FOR_ID_SERVICE)){
                service = new ShowMedicineByMedicineForIdService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_MEDICINE_BY_MEDICINE_FROM_ID_SERVICE)){
                service = new ShowMedicineByMedicineFromIdService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_MEDICINE_BY_MEDICINE_HOW_ID_SERVICE)){
                service = new ShowMedicineByMedicineHowIdService();
            }
            else if(serviceName.equalsIgnoreCase(LOGIN_USER_SERVICE)){
                service = new LoginUserService();
            }
            else if(serviceName.equalsIgnoreCase(LOGOUT_USER_SERVICE)){
                service = new LogOutUserService();
            }
            else if(serviceName.equalsIgnoreCase(LOGIN_DOCTOR_SERVICE)){
                service = new LoginDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(LOGOUT_DOCTOR_SERVICE)){
                service = new LogOutDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(COMMENT_AS_DOCTOR_PAGE_SERVICE)){
                service = new CommentAsDoctorPageService();
            }
            else if(serviceName.equalsIgnoreCase(LEAVE_COMMENT_AS_DOCTOR_SERVICE)){
                service = new LeaveCommentAsDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(COMMENT_AS_USER_PAGE_SERVICE)){
                service = new CommentAsUserPageService();
            }
            else if(serviceName.equalsIgnoreCase(LEAVE_COMMENT_AS_USER_SERVICE)){
                service = new LeaveCommentAsUserService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_ALL_USERS_SERVICE)){
                service = new ShowAllUsersService();
            }
            else if(serviceName.equalsIgnoreCase(EDIT_USER_SERVICE)){
                service = new EditUserService();
            }
            else if(serviceName.equalsIgnoreCase(CHANGE_USER_SERVICE)){
                service = new ChangeUserService();
            }
            else if(serviceName.equalsIgnoreCase(DELETE_USER_SERVICE)){
                service = new DeleteUserService();
            }
            else if(serviceName.equalsIgnoreCase(GO_ADD_USER_SERVICE)){
                service = new GoAddUserService();
            }
            else if(serviceName.equalsIgnoreCase(ADD_USER_SERVICE)){
                service = new AddUserService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_ALL_DOCTOR_SERVICE)){
                service = new ShowAllDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(EDIT_DOCTOR_SERVICE)){
                service = new EditDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(CHANGE_DOCTOR_SERVICE)){
                service = new ChangeDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(DELETE_DOCTOR_SERVICE)){
                service = new DeleteDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(APPROVE_DOCTOR_SERVICE)){
                service = new ApproveDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(DISAPPROVE_DOCTOR_SERVICE)){
                service = new DisapproveDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(GO_ADD_DOCTOR_SERVICE)){
                service = new GoAddDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(ADD_DOCTOR_SERVICE)){
                service = new AddDoctorService();
            }
            else if(serviceName.equalsIgnoreCase(LOGOUT_ADMIN_SERVICE)){
                service = new LogOutAdminService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_ALL_MED_CENTERS_SERVICE)){
                service = new ShowAllMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_ALL_PHARMACY_FOR_ADMIN_SERVICE)){
                service = new ShowAllPharmacyForAdminService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_ALL_MEDICINE_FOR_ADMIN_SERVICE)){
                service = new ShowAllMedicineForAdminService();
            }
            else if(serviceName.equalsIgnoreCase(SHOW_ALL_MEDICINE_BY_PHARMACY_SERVICE)){
                service = new ShowAllMedicineByPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(EDIT_MED_CENTER_SERVICE)){
                service = new EditMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(CHANGE_MED_CENTER_SERVICE)){
                service = new ChangeMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(DELETE_MED_CENTER_SERVICE)){
                service = new DeleteMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(APPROVE_MED_CENTER_SERVICE)){
                service = new ApproveMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(DISAPPROVE_MED_CENTER_SERVICE)){
                service = new DisapproveMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(GO_ADD_MED_CENTER_SERVICE)){
                service = new GoAddMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(ADD_MED_CENTER_SERVICE)){
                service = new AddMedCenterService();
            }
            else if(serviceName.equalsIgnoreCase(EDIT_PHARMACY_SERVICE)){
                service = new EditPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(CHANGE_PHARMACY_SERVICE)){
                service = new ChangePharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(DELETE_PHARMACY_SERVICE)){
                service = new DeletePharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(APPROVE_PHARMACY_SERVICE)){
                service = new ApprovePharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(DISAPPROVE_PHARMACY_SERVICE)){
                service = new DisapprovePharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(GO_ADD_PHARMACY_SERVICE)){
                service = new GoAddPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(ADD_PHARMACY_SERVICE)){
                service = new AddPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(EDIT_MEDICINE_BY_PHARMACY_SERVICE)){
                service = new EditMedicineByPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(CHANGE_MEDICINE_BY_PHARMACY_SERVICE)){
                service = new ChangeMedicineByPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(DELETE_MEDICINE_BY_PHARMACY_SERVICE)){
                service = new DeleteMedicineByPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(GO_ADD_MEDICINE_BY_PHARMACY_SERVICE)){
                service = new GoAddMedicineByPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(ADD_MEDICINE_BY_PHARMACY_SERVICE)){
                service = new AddMedicineByPharmacyService();
            }
            else if(serviceName.equalsIgnoreCase(CHANGE_LANGUAGE_SERVICE)){
                service = new ChangeLanguageService();
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
