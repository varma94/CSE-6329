/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.util.Date;

/**
 *
 * @author Han
 */
public class Appointment implements java.io.Serializable, Comparable<Appointment>{

    private int apptID = 0;
    private String studentMajor = null;
    private String studentName = null;
    private String studentID = null;
    private String studentEmail = null;
    private String advisorName = null;
    private String description = null;
    private String type = null;
    private int startHour = 0;
    private int startMinute = 0;
    private int endHour = 0;
    private int endMinute = 0;
    private Date date = null;

    public Appointment() {

    }
    
    public boolean initialize(String sMajor, String sName, String sID, String sEmail, String aName, String type, String dp, Date date, int sH, int eH, int sM, int eM) {
        this.setStudentMajor(sMajor);
        this.setStudentName(sName);
        if(this.setStudentID(sID) == false)
            return false;
        this.setStudentEmail(sEmail);
        this.setAdvisorName(aName);
        this.setType(type);
        this.setDescription(dp);
        this.setDate(date);
        this.setStartHour(sH);
        this.setEndHour(eH);
        this.setStartMinute(sM);
        this.setEndMinute(eM);
        return true;
    }
    
    // Setters
    public void setStudentMajor(String sMajor) {
        this.studentMajor = sMajor;
    }
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }
    public void setStudentName(String sName) {
        this.studentName = sName;
    }
    public boolean setStudentID(String sID) {
        if (sID.length() != 10)
          return false;
      else if (!sID.startsWith("1000") && !sID.startsWith("6000"))
          return false;
        this.studentID = sID;
        return true;
    }
    public void setStudentEmail(String sEmail) {
        this.studentEmail = sEmail;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setAdvisorName(String aName) {
        this.advisorName = aName;
    }
    public void setDescription(String dp) {
        this.description = dp;
    }
    public void setDate(Date d) {
        this.date = d;
    }
    public void setStartHour(int sH) {
        this.startHour = sH;
    }
    public void setEndHour(int eH) {
        this.endHour = eH;
    }
    public void setStartMinute(int sM) {
        this.startMinute = sM;
    }
    public void setEndMinute(int eM) {
        this.endMinute = eM;
    }

    // Getters
    public String getStudentMajor() {
        return this.studentMajor;
    }
    public int getApptID() {
        return this.apptID;
    }
    public String getType() {
        return this.type;
    }
    public String getStudentName() {
        return this.studentName;
    }
    public String getStudentID() {
        return this.studentID;
    }
    public String getStudentEmail() {
        return this.studentEmail;
    }
    public String getAdvisorName() {
        return this.advisorName;
    }
    public String getDescription() {
        return this.description;
    }
    public int getStartHour() {
        return this.startHour;
    }
    public int getEndHour() {
        return this.endHour;
    }
    public int getStartMinute() {
        return this.startMinute;
    }
    public int getEndMinute() {
        return this.endMinute;
    }
    public Date getDate() {
        return this.date;
    }

    @Override
    public int compareTo(Appointment other) {
        Appointment toCompare = other;
        int compare = 0;
        compare = this.getDate().compareTo(toCompare.getDate());
        
        if(compare == 0)
        {
            compare = this.getStartHour() - toCompare.getStartHour();
        }
        if(compare == 0)
        {
            compare = this.getStartMinute() - toCompare.getStartMinute();
        }
        return compare;
        
    }
}
