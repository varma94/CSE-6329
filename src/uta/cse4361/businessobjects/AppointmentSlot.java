/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.util.Date;

/**
 *
 * @author Frank R.
 */
public class AppointmentSlot extends Slot{
    
    public AppointmentSlot(int newAppointmentId, Date newDate, 
            int newHour, int newMinute) throws IllegalArgumentException      
    {
        super(true, newAppointmentId, newDate, newHour, newMinute); 
    }
}
