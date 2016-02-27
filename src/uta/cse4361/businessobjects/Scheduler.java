/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;
import java.util.*;
import uta.cse4361.databases.DatabaseManager;
/**
 *
 * @author Nabin
 */
public class Scheduler implements uta.cse4361.interfaces.Constants{
    DatabaseManager databaseManager = new DatabaseManager();
    Date date = new Date();
    SlotFactory aff =  SlotFactory.getInstance();
    Appointment appointment =new Appointment();
    

    
    public Scheduler()
    {
        
    }

    
    public String schedule(Appointment newAppointment){
        databaseManager = new DatabaseManager();
        appointment = newAppointment;
        String msg = SUCCESS_MESSAGE;
        boolean isFree = databaseManager.isFree(appointment.getDate(), 
                appointment.getStartHour(), appointment.getEndHour(), 
                appointment.getStartMinute(),appointment.getEndMinute());
        if (isFree == true )
        {
            databaseManager.saveAppointment(appointment);
        }
        else
        {
            msg = TIME_IS_NOT_FREE_FAULT;
        }
        return msg;
    }
    
}
