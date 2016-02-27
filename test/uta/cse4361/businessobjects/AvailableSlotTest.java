/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frank R.
 */
public class AvailableSlotTest implements uta.cse4361.interfaces.Constants{
    
    public AvailableSlotTest() {
    }

    @Test
    public void testHasAppointment() 
    {
        AvailableSlot availableF = new AvailableSlot(new Date(), MIN_HOUR, MIN_MINUTE, 0);
        
        boolean hasAppt = availableF.isAppointment();
        
        assertEquals("This AvailableFlyweight should not show as having an appointment.",
                false, hasAppt);
    }
    
    @Test
    public void testGetAppointmentId()
    {
        AvailableSlot availableF = new AvailableSlot(new Date(), MIN_HOUR, MIN_MINUTE, 0);
        
        int apptId = availableF.getAppointmentId();
        
        assertEquals("This AvailableFlyweight should have the invalid appointment Id.",
                ILLEGAL_APPT_ID, apptId);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIncorrectLowHourValue()
    {
        AvailableSlot availableF = new AvailableSlot(new Date(), MIN_HOUR - 1, MIN_MINUTE, 0);
        
        fail();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIncorrectHighHourValue()
    {
        AvailableSlot availableF = new AvailableSlot(new Date(), MAX_HOUR + 1, MIN_MINUTE, 0);
        
        fail();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIncorrectLowMinuteValue()
    {
        AvailableSlot availableF = new AvailableSlot(new Date(), MIN_HOUR, MIN_MINUTE - 1, 0);
        
        fail();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIncorrectHighMinuteValue()
    {
        AvailableSlot availableF = new AvailableSlot(new Date(), MAX_HOUR, MAX_MINUTE + 1, 0);
        
        fail();
    }
}
