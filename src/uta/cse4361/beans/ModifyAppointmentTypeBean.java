/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;
import uta.cse4361.databases.DatabaseManager;
import uta.cse4361.interfaces.Constants;

/**
 *
 * @author oguni
 */
public class ModifyAppointmentTypeBean implements Constants{
    private int appTypeID;
    private String appTypeName;
    private boolean remove = false;
    private int appTypeLength;
    
    public ModifyAppointmentTypeBean(){
        
    }
    
    public String modifyAppointmentType(){
        String returnMessage;
        DatabaseManager databaseManager = new DatabaseManager();
        boolean result = false;
        
        if(remove == true)
        {
            returnMessage = databaseManager.deleteAppointmentType(appTypeID);
        } else {
            returnMessage = databaseManager.modifyAppointmentType(appTypeID, appTypeName, appTypeLength);
        }
        return returnMessage;
    }
    
    public void setRemove(boolean newRemove)
    {
        this.remove = newRemove;
    }
    
    public void setAppTypeID(int newAppTypeId)
    {
        this.appTypeID = newAppTypeId;
    }
    
    public void setAppTypeName(String newName) {
        this.appTypeName = newName;
    }
    
    public void setAppTypeLength(int length){
        this.appTypeLength = length;
    }
    
    public boolean getRemove()
    {
        return this.remove;
    }
    
    public int getAppTypeID()
    {
        return this.appTypeID;
    }
    
    public String getAppTypeName() {
        return this.appTypeName;
    }
    
    public int getAppTypeLength(){
        return appTypeLength;
    }
}
