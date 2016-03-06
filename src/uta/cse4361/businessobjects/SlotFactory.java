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

    public int determineNumberOfFlyweights(int startHour, int endHour, int startMinute, int endMinute, int increment) {
        
        int hours = endHour - startHour - 1;
        int totalMinutes = (hours * 60) + endMinute + (60 - startMinute);
        int numOfFlyweights = totalMinutes/increment;
        return numOfFlyweights;
        
    }

    private void updateTimes(int increment ) 
    {
        int additionalHours = increment/60;
        nextHour = nextHour + additionalHours;
        int additionalMinutes = increment%60;
        nextMinute = nextMinute + additionalMinutes;
        
        if (nextMinute >= 60)
        {
            nextMinute = nextMinute%60;
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
    
    
    public ArrayList<Slot> generateSlots(Date date, int startHour, int endHour, int startMinute, int endMinute, int apptId, String key, int appTypeID, int advisorID)
    {
        DatabaseManager dm = new DatabaseManager();
        int appLength = dm.getAppointmentType(appTypeID).getLength();
        
        if(isValidTime(startHour, endHour, startMinute, endMinute))
        {
            return null;
        }
        
        int numberOfFlyweights = determineNumberOfFlyweights(startHour, endHour, startMinute, endMinute, appLength);
        
        ArrayList<Slot> flyweightsToReturn = new ArrayList<>();
        
        nextHour = startHour;
        nextMinute = startMinute;
        
        
        if(key.equals(APPOINTMENT_FLYWEIGHT_KEY))
        {
            for(int i = 0; i < numberOfFlyweights; i++)
            {
                try
                {
                    flyweightsToReturn.add(new AppointmentSlot(apptId, date, nextHour, nextMinute, appTypeID, advisorID));
                }
                catch (IllegalArgumentException e)
                {
                    return null;
                }
                updateTimes(appLength);
            }
        }
        else if(key.equals(AVAILABLE_FLYWEIGHT_KEY))
        {
            for(int i = 0; i < numberOfFlyweights; i++)
            {
                try
                {
                    flyweightsToReturn.add(new AvailableSlot(date, nextHour, nextMinute, 0, appTypeID, advisorID));
                }
                catch (IllegalArgumentException e)
                {
                    return null;
                }
                updateTimes(appLength);
            }
        }
        else
        {
            return null;
        }
        
        return flyweightsToReturn;
    }
    
    
}
