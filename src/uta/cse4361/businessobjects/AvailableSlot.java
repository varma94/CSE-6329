/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.util.Date;

/**
 *
 * @author The Doctor
 */
public class AvailableSlot extends Slot{
    
    public AvailableSlot(Date newDate, int newHour, int newMinute, int slotId) throws IllegalArgumentException
    {
        super(false, slotId, newDate, newHour, newMinute);
 
    }
    

    
}
