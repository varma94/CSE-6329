/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import uta.cse4361.businessobjects.Slot;

import uta.cse4361.businessobjects.SlotFactory;
import uta.cse4361.databases.DatabaseManager;

/**
 *
 * @author Han
 */
public class TimeAllocationBean implements uta.cse4361.interfaces.Constants, java.io.Serializable {
    //final static String SUCCESS_MSG = ""; 
    
    
    private Date date;
    private int startHour = 0;
    private int endHour = 0;
    private int startMinute = 0;
    private int endMinute = 0;
    private int appType;
    private int isRepeat = 0;
    private int advisingType;
    private int advisor;
    
    public TimeAllocationBean(){
        
    }
    
    public String allocateTime() {
        SlotFactory aff = SlotFactory.getInstance();
        ArrayList<Slot> slots = aff.generateSlots(date, this.startHour, 
                this.endHour, this.startMinute, this.endMinute, 0, 
                AVAILABLE_FLYWEIGHT_KEY, advisingType, advisor);
        DatabaseManager databaseManager = new DatabaseManager();
        String msg = databaseManager.saveSlots(slots);
        return msg;
    }
    
    
    public String allocateTimeRepeat(int r) {
        String msg = SUCCESS_MESSAGE;
        Date rDate = date;
        int currDayOfWeek;
        int selectedDayOfWeek;
        int daysTillRepeatDay;        
        int year;
        int nextYear;



        //Get the day of the week of the selected appointment date
        Calendar cal = Calendar.getInstance();
        currDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        Calendar selectedCal = Calendar.getInstance();
        selectedCal.setTime(rDate);
        selectedDayOfWeek = selectedCal.get(Calendar.DAY_OF_WEEK);
        daysTillRepeatDay = selectedDayOfWeek - currDayOfWeek;        
        year = cal.get(Calendar.YEAR);
        nextYear = year+1;
        allocateTime();
            
        for (int i=1; i<r; i++)
        {
            selectedCal.add(Calendar.DAY_OF_WEEK, 7);
            date = selectedCal.getTime();
            allocateTime();
        }  
        return msg;
    }
    
    
    // Setters
    public void setDate(Date d){
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
    public void setAdvisingType(int type){
        this.advisingType = type;
    }
    public void setAdvisor(int advisor){
        this.advisor = advisor;
    }
    public void setAppType(int appType){
        this.appType = appType;
    }
    
    // Getters
    public Date getDate(){
        return this.date;
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
    public int getAdvisingType(){
        return this.advisingType;
    }
    public int getAdvisor(){
        return this.advisor;
    }
    public int getAppType(){
        return this.appType;
    }
}
