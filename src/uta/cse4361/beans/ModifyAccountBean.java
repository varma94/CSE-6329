/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import uta.cse4361.databases.DatabaseManager;
import uta.cse4361.interfaces.Constants;
import uta.cse4361.businessobjects.AdvisorAccount;

/**
 *
 * @author oguni
 */
public class ModifyAccountBean implements Constants{
    
    private boolean remove = false;
    private String name;
    private String email;
    private String department;
    private int ID;
    private int rank;
    
    public ModifyAccountBean(){
        
    }
    
    public String modifyAccount(){
        String returnMessage = SUCCESS_MESSAGE;
        AdvisorAccount acct = new AdvisorAccount();
        DatabaseManager databaseManager = new DatabaseManager();
        boolean result = false;
        
        if(remove == true){
            returnMessage = databaseManager.deleteAccount(this.ID);
        } else {
            result = acct.initialize(this.name, this.email, this.department, this.ID, this.rank);
            if (result == true){
                returnMessage = databaseManager.modifyAccount(this.ID, acct);
            }else 
            {
                returnMessage = MODIFY_ACCOUNT_FAIL;
            }
            
        }
        return returnMessage;
    }
    
    //getters
    public boolean getRemove(){
        return remove;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getDepartment(){
        return department;
    }
    public int getID(){
        return ID;
    }
    public int getRank(){
        return rank;
    }
    
    //setters
    public void setRemove(boolean remove){
        this.remove = remove;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public void setID (int ID){
        this.ID = ID;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    
}


