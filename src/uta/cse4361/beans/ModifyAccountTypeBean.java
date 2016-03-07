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
public class ModifyAccountTypeBean implements Constants{
    private int acctTypeID;
    private String acctTypeName;
    private boolean remove = false;
    
    public ModifyAccountTypeBean(){
        
    }
    
    public String modifyAccountType(){
        String returnMessage;
        DatabaseManager databaseManager = new DatabaseManager();
        
        if(remove == true)
        {
            returnMessage = databaseManager.deleteAccountType(acctTypeID);
        } else {
            returnMessage = databaseManager.modifyAccountType(acctTypeID, acctTypeName);
        }
        return returnMessage;
    }
    
    public void setRemove(boolean newRemove)
    {
        this.remove = newRemove;
    }
    
    public void setAcctTypeID(int newAcctTypeId)
    {
        this.acctTypeID = newAcctTypeId;
    }
    
    public void setAcctTypeName(String newName) {
        this.acctTypeName = newName;
    }
    
    
    public boolean getRemove()
    {
        return this.remove;
    }
    
    public int getAcctTypeID()
    {
        return this.acctTypeID;
    }
    
    public String getAcctTypeName() {
        return this.acctTypeName;
    }
    
}
