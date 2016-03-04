/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;


import java.util.ArrayList;
import java.util.Date;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.Slot;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.StudentAccount;
import uta.cse4361.businessobjects.StudentPasswordAccount;
import uta.cse4361.businessobjects.AppointmentType;

public interface DatabaseImpInterface {
    public String saveSlots(ArrayList<Slot> slots);
    public String saveAppointment(Appointment appt);
    public ArrayList<Appointment> getAppointments();
    public Appointment getAppointment(int apptID);
    public String modifyAppointment(int id, Appointment appt);
    public String modifySlot(Date d, int startHour, int endHour, int startMin, int endMin, int slotID);
    public ArrayList<Slot> getSlot();
    public ArrayList<Slot> getApptSlots();
    public ArrayList<Slot> getAvailSlots();
    public ArrayList<Slot> getAvailSlotsByTime(Date d, int startHour, int endHour, int startMin, int endMin);
    public String register(AdvisorAccount aa);
    public String studentRegister(StudentAccount sa);
    public String validate(String email, String password);
    public String studentValidate(String studentEmail, String studentPassword);
    public AdvisorAccount getAccount(String email);
    public StudentAccount getStudentAccount(String email);
    public String studentPasswordChange(StudentPasswordAccount sa);
    public String deleteAccount(int id);
    public ArrayList<AdvisorAccount> getAccounts();
    public String modifyAccount(int id, AdvisorAccount account);
    public String studentModifyAccount(String id, StudentAccount account);
    public ArrayList<String> getAppointmentTypes();
    public ArrayList<AppointmentType> getAppointmentTypesObjs();
    public AppointmentType getAppointmentType(int id);
    public String deleteAppointmentType(int id);
    public String modifyAppointmentType(int id, String name);
    
}
