/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.StudentAppointment;
import uta.cse4361.businessobjects.Slot;
import uta.cse4361.businessobjects.SlotFactory;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.StudentPasswordAccount;
import uta.cse4361.businessobjects.StudentAccount;
import uta.cse4361.businessobjects.AppointmentType;
import uta.cse4361.businessobjects.AccountType;

/**
 *
 * @author Han
 */
public class DatabaseManager {
    DatabaseImpInterface imp = new RelationalDatabaseImpl();
    
    public DatabaseManager() {
        
    }
    
    public boolean isFree(Date date, int startHour, int endHour, int startMinute, int endMinute, int appType) {
        ArrayList<Slot> slots = imp.getAvailSlotsByTime(date, startHour, endHour, startMinute, endMinute);
        SlotFactory f = SlotFactory.getInstance();
        int appLength = imp.getAppointmentType(appType).getLength();
        int apptSize = f.determineNumberOfFlyweights(startHour, endHour, startMinute, endMinute, appLength);
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
    
    public ArrayList<StudentAppointment> getStudentAppointments(String email) {
        
        ArrayList<StudentAppointment> appointments = imp.getStudentAppointments(email);
       // Collections.sort(appointments);
        return appointments;
    }
    
   
    
  
    public Appointment getAppointment(int apptID) {
        return imp.getAppointment(apptID);
    }
    
    public StudentAppointment getStudentAppointment(int apptID) {
        return imp.getStudentAppointment(apptID);
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
    public String modifyStudentAppointment(int id, StudentAppointment appt) {
        return imp.modifyStudentAppointment(id, appt);
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
    public ArrayList<Slot> getTypeSlots(int advisorID, int apptTypeID){
        ArrayList<Slot> avail = imp.getAvailSlots(advisorID, apptTypeID);
        ArrayList<Slot> appt = imp.getApptSlots(advisorID, apptTypeID);
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
    
    public String studentPasswordChange(StudentPasswordAccount sa){
        return imp.studentPasswordChange(sa);
    }
    
    
    
    public String validate(String email, String password){
        return imp.validate(email, password);
    }
    
    // TODO 
    public AdvisorAccount getAccount(String email){
        return imp.getAccount(email);
    }
    
    //public StudentAccount getStudentAccount(String email){
    //    return imp.getStudentAccount(email);
    //}
     
    
    public ArrayList<AdvisorAccount> getAccounts(){
    	return imp.getAccounts();
    }
    public String studentValidate(String studentEmail, String studentPassword)
    {
        return imp.studentValidate(studentEmail, studentPassword);
    }
    
    public ArrayList<String> getAdvisingTypes(){
        return imp.getAppointmentTypes();
    }
    
    public ArrayList<AppointmentType> getAppointmentTypesObj(){
        return imp.getAppointmentTypesObjs();
    }
    
    public AppointmentType getAppointmentType(int id){
        return imp.getAppointmentType(id);
    }
    
    public String deleteAppointmentType(int id){
        return imp.deleteAppointmentType(id);
    }
    
    public String modifyAppointmentType(int id, String name, int length){
        return imp.modifyAppointmentType(id, name, length);
    }

    public String createAptType(String name) {
        return imp.createAptType(name);
    }
    
    public ArrayList<AdvisorAccount> getAdvisors(){
        return imp.getAdvisors();
    }
    
    public AdvisorAccount getAdvisor(int id){
        return imp.getAdvisor(id);
    }
    
    public ArrayList<AccountType> getAccountTypes(){
        return imp.getAccountTypes();
    }
    
    public AccountType getAccountType(int id){
        return imp.getAccountType(id);
    }
    
    public String createAccountType(String name, int privilege){
        return imp.createAccountType(name, privilege);
    }
    
    public String deleteAccountType(int id){
        return imp.deleteAccountType(id);
    }
    
    public String modifyAccountType(int id, String name, int privilege){
        return imp.modifyAccountType(id, name, privilege);
    }
}
