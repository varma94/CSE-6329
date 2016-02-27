/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Frank R.
 */
public abstract class Slot implements Serializable, Comparable<Slot>, uta.cse4361.interfaces.Constants
{
    
    
    
    private int id;
    
    private boolean hasAppointment;
    
    private Date date;
    
    private int time;
    
    public Slot(boolean newHasAppointment, int newAppointmentId, Date newDate, int newHour, int newMinute)
            throws IllegalArgumentException
    {
        hasAppointment = newHasAppointment;
        id = newAppointmentId;
        date = newDate;
        
        boolean validTime = this.setTime(newHour, newMinute);
        
        if (validTime == false)
        {
            throw new IllegalArgumentException("The hour and minute provided were not within the ranges defined as valid.\n"
                    + "MAX_HOUR = " + Slot.MAX_HOUR
                    + ", MIN_HOUR = " + Slot.MIN_HOUR
                    + ", MAX_MINUTE = " + Slot.MAX_MINUTE
                    + ", MIN_MINUTE = " + Slot.MIN_MINUTE);
        }
    }
    
    public boolean isAppointment()
    {
        return hasAppointment;
    }
    
    public int getAppointmentId()
    {
        return id;
    }
    
    public int getTime()
    {
        return time;
    }
    
    public int getHour()
    {
        return time/60;
    }
    
    public int getMinute()
    {
        return time%60;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    private void setDate(Date newDate)
    {
        date = newDate;
    }
    
    private void setTime(int newTime)
    {
        time = newTime;
    }
    
    private boolean setTime(int hour, int minute)
    {
        boolean isAccurate = true;
        
        if ((hour < MIN_HOUR) || (hour > MAX_HOUR))
        {
            isAccurate = isAccurate && false;
        }
        
        if ((minute < MIN_MINUTE) || (minute > MAX_MINUTE))
        {
            isAccurate = isAccurate && false;
        }
        
        if(isAccurate)
        {
            setTime((hour * 60) + minute);
        }
        
        return isAccurate;
    }
    
    @Override
    public int compareTo(Slot another) {
        Slot toCompare = another;
        int compare = 0;
        compare = this.getDate().compareTo(toCompare.getDate());
        
        if(compare == 0)
        {
            compare = this.getTime() - toCompare.getTime();
        }
        return compare;
    }
}
