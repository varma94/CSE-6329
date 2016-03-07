/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.util.ArrayList;
import java.util.Date;

import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.StudentAccount;
import uta.cse4361.businessobjects.StudentPasswordAccount;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.AppointmentType;
import uta.cse4361.businessobjects.Slot;
import uta.cse4361.businessobjects.StudentAppointment;
import uta.cse4361.businessobjects.AccountType;

/**
 *
 * @author Han
 */
public class RelationalDatabaseImpl implements DatabaseImpInterface{
    
    public RelationalDatabaseImpl() {
        
    }

    @Override
    public String saveSlots(ArrayList<Slot> slots) {
        RDBImplCommand saveSlots = new SaveSlots(slots);
        saveSlots.execute();
        ArrayList<Integer> savedSlots = (ArrayList<Integer>)saveSlots.getResult();
        if(savedSlots.size() == slots.size()){
            return "";
        }
        return "failed";
    }

    @Override
    public String saveAppointment(Appointment appt) {
        RDBImplCommand saveAppointment = new SaveAppointment(appt);
        saveAppointment.execute();
        return (String)saveAppointment.getResult();
    }

    @Override
    public String modifyAppointment(int id, Appointment appt) {
        if (appt == null) {
            RDBImplCommand deleteAppointment = new DeleteAppointment(id);
            deleteAppointment.execute();
            return (String)deleteAppointment.getResult();
        } else {
            RDBImplCommand editAppointment = new EditAppointment(id, appt);
            editAppointment.execute();
            return (String)editAppointment.getResult();
        }
    }

    @Override
    public String modifyStudentAppointment(int id, StudentAppointment appt) {
        if (appt == null) {
            RDBImplCommand deleteAppointment = new DeleteAppointment(id);
            deleteAppointment.execute();
            return (String)deleteAppointment.getResult();
        } else {
            RDBImplCommand editAppointment = new EditStudentAppointment(id, appt);
            editAppointment.execute();
            return (String)editAppointment.getResult();
        }
    }
    
   

    @Override
    public String modifySlot(Date d, int startHour, int endHour, int startMin, int endMin, int slotID) {
        RDBImplCommand modifySlot = new DeleteSlot(d, startHour, endHour, startMin, endMin, slotID);
        modifySlot.execute();
        return (String) modifySlot.getResult();
    }

    @Override
    public ArrayList<Appointment> getAppointments() {
        RDBImplCommand getAppointments = new GetAppointments();
        getAppointments.execute();
        return (ArrayList<Appointment>) getAppointments.getResult();
        
    }

    @Override
    public ArrayList<StudentAppointment> getStudentAppointments(String email) {
        RDBImplCommand getAppointments = new GetStudentAppointments(email);
        getAppointments.execute();
        return (ArrayList<StudentAppointment>) getAppointments.getResult();
        
    }
    
   

    @Override
    public Appointment getAppointment(int apptID) {
        RDBImplCommand getAppointment = new GetAppointment(apptID);
        getAppointment.execute();
        return (Appointment) getAppointment.getResult();
    }
    @Override
    public StudentAppointment getStudentAppointment(int apptID) {
  RDBImplCommand getAppointment = new GetStudentAppointment(apptID);
        getAppointment.execute();
        return (StudentAppointment) getAppointment.getResult();    
    }

    
    
    
    @Override
    public ArrayList<Slot> getSlot(){
        RDBImplCommand getSlots = new GetSlot();
        getSlots.execute();
        return (ArrayList<Slot>) getSlots.getResult();
    }
    @Override
    public ArrayList<Slot> getApptSlots(){
        RDBImplCommand getApptSlot = new GetApptSlots();
        getApptSlot.execute();
        return (ArrayList<Slot>) getApptSlot.getResult(); 
    }
    
    @Override
    public ArrayList<Slot> getAvailSlots(){
        RDBImplCommand getAvailSlot = new GetAvailSlots();
        getAvailSlot.execute();
        return(ArrayList<Slot>) getAvailSlot.getResult();
    }
    
    @Override
    public ArrayList<Slot> getApptSlots(int advisorID, int apptType){
        RDBImplCommand getApptSlot = new GetApptSlots(advisorID, apptType);
        getApptSlot.execute();
        return(ArrayList<Slot>)getApptSlot.getResult();
    }
    
    @Override
    public ArrayList<Slot> getAvailSlots(int advisorID, int apptType){
        RDBImplCommand getAvailSlot = new GetAvailSlots(advisorID, apptType);
        getAvailSlot.execute();
        return(ArrayList<Slot>)getAvailSlot.getResult();
    }
    
    @Override
    public ArrayList<Slot> getAvailSlotsByTime(Date d, int startHour, int endHour, int startMin, int endMin){
        RDBImplCommand getAvailSlot = new GetAvailSlotsByTime(d, startHour, endHour, startMin, endMin);
        getAvailSlot.execute();
        return(ArrayList<Slot>) getAvailSlot.getResult();
    }
    
    @Override
    public String register(AdvisorAccount aa){
        RDBImplCommand register = new RegisterAdvisor(aa);
        register.execute();
        return (String)register.getResult();
    }
    
     @Override
    public String studentRegister(StudentAccount sa){
        RDBImplCommand studentRegister = new StudentAdvisor(sa);
        studentRegister.execute();
        return (String)studentRegister.getResult();
    }
    
     @Override
    public String studentPasswordChange(StudentPasswordAccount sa){
        RDBImplCommand studentRegister = new StudentPasswordAdvisor(sa);
        studentRegister.execute();
        return (String)studentRegister.getResult();
    }
    
    @Override
    public String validate(String email, String password){
        RDBImplCommand validate = new ValidateLogin(email, password);
        validate.execute();
        return (String)validate.getResult();
    }
    
    @Override
    public AdvisorAccount getAccount(String email){
        RDBImplCommand getAccount = new GetAdvisor(email);
        getAccount.execute();
        return (AdvisorAccount)getAccount.getResult();
    }
    @Override
        public String deleteAccount(int id){
            RDBImplCommand deleteAccount = new DeleteAdvisor(id);
            deleteAccount.execute();
            return (String)deleteAccount.getResult();
        }

	@Override
	public ArrayList<AdvisorAccount> getAccounts() {
		RDBImplCommand getAccounts = new GetAccounts();
		getAccounts.execute();
		return (ArrayList<AdvisorAccount>)getAccounts.getResult();
	}

	@Override
	public String modifyAccount(int id, AdvisorAccount account) {
		RDBImplCommand modifyAccount = new EditAdvisor(id, account);
		modifyAccount.execute();
		return (String)modifyAccount.getResult();
	}
        
        @Override
	public String studentModifyAccount(String id, StudentAccount account) {
		RDBImplCommand modifyAccount = new EditStudent(id, account);
		modifyAccount.execute();
		return (String)modifyAccount.getResult();
	}
        
        
        @Override
    public String studentValidate(String studentEmail, String studentPassword){
        RDBImplCommand validate = new StudentValidateLogin(studentEmail, studentPassword);
        validate.execute();
        return (String)validate.getResult();
    }
    
    @Override
    public ArrayList<String> getAppointmentTypes(){
        RDBImplCommand getAdvisingTypes = new GetAppointmentTypes();
        getAdvisingTypes.execute();
        return (ArrayList<String>)getAdvisingTypes.getResult();
    }
    
    @Override
    public ArrayList<uta.cse4361.businessobjects.AppointmentType> getAppointmentTypesObjs(){
        RDBImplCommand getAppointmentTypes = new GetAppointmentTypesObjs();
        getAppointmentTypes.execute();
        return (ArrayList<AppointmentType>)getAppointmentTypes.getResult();
    }

    @Override
    public AppointmentType getAppointmentType(int id) {
        RDBImplCommand getAppointmentType = new GetAppointmentType(id);
        getAppointmentType.execute();
        return (AppointmentType)getAppointmentType.getResult();
    }

    @Override
    public String deleteAppointmentType(int id) {
        RDBImplCommand deleteAppointmentType = new DeleteAppointmentType(id);
        deleteAppointmentType.execute();
        return (String)deleteAppointmentType.getResult();
    }

    @Override
    public String modifyAppointmentType(int id, String name, int length) {
        RDBImplCommand modifyAppointmentType = new ModifyAppointmentType(id, name, length);
        modifyAppointmentType.execute();
        return (String)modifyAppointmentType.getResult();
    }

    @Override
    public String createAptType(String name) {
        RDBImplCommand createAptType = new CreateAptType(name);
        createAptType.execute();
        return (String)createAptType.getResult();
    }
    
    @Override
    public ArrayList<AdvisorAccount> getAdvisors(){
        RDBImplCommand getAdvisors = new GetAdvisors();
        getAdvisors.execute();
        return (ArrayList<AdvisorAccount>)getAdvisors.getResult();
    }
    
    @Override
    public AdvisorAccount getAdvisor(int id){
        RDBImplCommand getAdvisor = new GetAdvisor(id);
        getAdvisor.execute();
        return (AdvisorAccount)getAdvisor.getResult();
                
    }
    
    @Override
    public ArrayList<AccountType> getAccountTypes(){
        RDBImplCommand getAccountTypes = new GetAccountTypes();
        getAccountTypes.execute();
        return (ArrayList<AccountType>)getAccountTypes.getResult();
    }
    
    @Override 
    public AccountType getAccountType(int id){
        RDBImplCommand getAccountType = new GetAccountType(id);
        getAccountType.execute();
        return (AccountType)getAccountType.getResult();
    }
    
    @Override
    public String createAccountType(String name, int privilege){
        RDBImplCommand createAccountType = new CreateAccountType(name, privilege);
        createAccountType.execute();
        return (String)createAccountType.getResult();
    }
    
    @Override
    public String deleteAccountType(int id){
        RDBImplCommand deleteAccountType = new DeleteAccountType(id);
        deleteAccountType.execute();
        return (String)deleteAccountType.getResult();
    }
    
    @Override
    public String modifyAccountType(int id, String name, int privilege){
        RDBImplCommand modifyAccountType = new ModifyAccountType(id, name, privilege);
        modifyAccountType.execute();
        return (String)modifyAccountType.getResult();
    }
}
