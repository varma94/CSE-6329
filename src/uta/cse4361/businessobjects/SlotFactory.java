/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.util.ArrayList;
import java.util.Date;
import uta.cse4361.databases.DatabaseManager;

/**
 *
 * @author Frank R.
 */
public class SlotFactory implements uta.cse4361.interfaces.Constants{

    private int nextHour;
    private int nextMinute;
    
    private SlotFactory() {
    }
    
    public static SlotFactory getInstance() {
        return AppointmentFlyweightFactoryHolder.INSTANCE;
    }

    public int determineNumberOfFlyweights(int startHour, int endHour, int startMinute, int endMinute) {
        
        int hours = endHour - startHour;
        int numOfFlyweights = hours * (60 / INCREMENT_GAP);
        int minutes = endMinute - startMinute;
        numOfFlyweights = numOfFlyweights + (minutes / INCREMENT_GAP);
        return numOfFlyweights;
    }

    private void updateTimes() 
    {
        nextMinute = nextMinute + INCREMENT_GAP;
        
        if (nextMinute >= 60)
        {
            nextMinute = 0;
            nextHour++;
        }
        
    }
    
    private static class AppointmentFlyweightFactoryHolder {

        private static final SlotFactory INSTANCE = new SlotFactory();
    }
    
    private boolean isValidTime(int startHour, int endHour, int startMinute, int endMinute)
    {
        return (startHour > endHour) || ((startHour == endHour) && (startMinute > endMinute));
    }
    
    
    public ArrayList<Slot> generateSlots(Date date, int startHour, int endHour, int startMinute, int endMinute, int apptId, String key)
    {
        if(isValidTime(startHour, endHour, startMinute, endMinute))
        {
            return null;
        }
        
        int numberOfFlyweights = determineNumberOfFlyweights(startHour, endHour, startMinute, endMinute);
        
        ArrayList<Slot> flyweightsToReturn = new ArrayList<Slot>();
        
        nextHour = startHour;
        nextMinute = startMinute;
        
        
        if(key.equals(APPOINTMENT_FLYWEIGHT_KEY))
        {
            for(int i = 0; i < numberOfFlyweights; i++)
            {
                try
                {
                    flyweightsToReturn.add(new AppointmentSlot(apptId, date, nextHour, nextMinute));
                }
                catch (IllegalArgumentException e)
                {
                    return null;
                }
                updateTimes();
            }
        }
        else if(key.equals(AVAILABLE_FLYWEIGHT_KEY))
        {
            for(int i = 0; i < numberOfFlyweights; i++)
            {
                try
                {
                    flyweightsToReturn.add(new AvailableSlot(date, nextHour, nextMinute, 0));
                }
                catch (IllegalArgumentException e)
                {
                    return null;
                }
                updateTimes();
            }
        }
        else
        {
            return null;
        }
        
        return flyweightsToReturn;
    }
    
    
}
