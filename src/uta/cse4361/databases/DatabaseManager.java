/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.Slot;
import uta.cse4361.businessobjects.SlotFactory;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.StudentAccount;

/**
 *
 * @author Han
 */
public class DatabaseManager {
    DatabaseImpInterface imp = new RelationalDatabaseImpl();
    
    public DatabaseManager() {
        
    }
    
    public boolean isFree(Date date, int startHour, int endHour, int startMinute, int endMinute) {
        ArrayList<Slot> slots = imp.getAvailSlotsByTime(date, startHour, endHour, startMinute, endMinute);
        SlotFactory f = SlotFactory.getInstance();
        int apptSize = f.determineNumberOfFlyweights(startHour, endHour, startMinute, endMinute);
        if(slots.size() >= apptSize){
            return true;
        }
        return false;
    }
    
    public ArrayList<Appointment> getAppointments() {
        
        ArrayList<Appointment> appointments = imp.getAppointments();
        Collections.sort(appointments);
        return appointments;
    }
    
    public Appointment getAppointment(int apptID) {
        return imp.getAppointment(apptID);
    }
    
    public String saveSlots(ArrayList<Slot> slots) {
        return imp.saveSlots(slots);
    }
    public String saveAppointment(Appointment appt) {
        return imp.saveAppointment(appt);
    }
    public String modifyAppointment(int id, Appointment appt) {
        return imp.modifyAppointment(id, appt);
    }
    
    public String modifyAccount(int id, AdvisorAccount account)
    {
    	return imp.modifyAccount(id,account);
    }
     public String studentModifyAccount(String id, StudentAccount account)
    {
    	return imp.studentModifyAccount(id,account);
    }
     public String deleteAccount(int id){
        return imp.deleteAccount(id);
    }
    public String modifySlot(Date d, int startHour, int startMin, int endHour, int endMin, int slotID) {
        return imp.modifySlot(d, startHour, startMin, endHour, endMin, slotID);
    }
    
    public ArrayList<Slot> getSlots(){
        return imp.getSlot();
    }
    
    public ArrayList<Slot> getTypeSlots(){
        ArrayList<Slot> avail = imp.getAvailSlots();
        ArrayList<Slot> appt = imp.getApptSlots();
        avail.addAll(appt);
        Collections.sort(avail);
        return avail;
    }
    public ArrayList<Slot> getAvailableSlots() {
         return imp.getAvailSlots();
     }
    
    public String register(AdvisorAccount aa){
        return imp.register(aa);
    }
    
    public String studentRegister(StudentAccount sa){
        return imp.studentRegister(sa);
    }
    
    
    
    public String validate(String email, String password){
        return imp.validate(email, password);
    }
    
    // TODO 
    public AdvisorAccount getAccount(String email){
        return imp.getAccount(email);
    }
    
     public StudentAccount getStudentAccount(String email){
        return imp.getStudentAccount(email);
    }
     
    
    public ArrayList<AdvisorAccount> getAccounts(){
    	return imp.getAccounts();
    }
    public String studentValidate(String studentEmail, String studentPassword)
    {
        return imp.studentValidate(studentEmail, studentPassword);
    }
}
