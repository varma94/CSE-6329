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
    private int isRepeat = 0;
    
    public TimeAllocationBean(){
        
    }
    
    public String allocateTime() {
        String msg = SUCCESS_MESSAGE;
        SlotFactory aff = SlotFactory.getInstance();
        ArrayList<Slot> slots = aff.generateSlots(date, this.startHour, this.endHour, this.startMinute, this.endMinute, 0, AVAILABLE_FLYWEIGHT_KEY);
        DatabaseManager databaseManager = new DatabaseManager();
        msg = databaseManager.saveSlots(slots);
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
}
