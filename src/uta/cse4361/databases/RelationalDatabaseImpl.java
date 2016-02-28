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
import uta.cse4361.businessobjects.Slot;

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
    public Appointment getAppointment(int apptID) {
        RDBImplCommand getAppointment = new GetAppointment(apptID);
        getAppointment.execute();
        return (Appointment) getAppointment.getResult();
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
    
      public StudentAccount getStudentAccount(String email){
        RDBImplCommand getAccount = new GetStudent(email);
        getAccount.execute();
        return (StudentAccount)getAccount.getResult();
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
	public String studentModifyAccount(String id, StudentAccount studentAccount) {
		RDBImplCommand studentModifyAccount = new EditStudent(id, studentAccount);
		studentModifyAccount.execute();
		return (String)studentModifyAccount.getResult();
	}
        @Override
    public String studentValidate(String studentEmail, String studentPassword){
        RDBImplCommand validate = new StudentValidateLogin(studentEmail, studentPassword);
        validate.execute();
        return (String)validate.getResult();
    }
}
