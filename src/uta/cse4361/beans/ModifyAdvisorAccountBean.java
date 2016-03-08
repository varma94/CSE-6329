/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.databases.DatabaseManager;
import uta.cse4361.databases.RelationalDatabaseImpl;
import uta.cse4361.interfaces.Constants;

/**
 *
 * @author Abhijeet
 */
public class ModifyAdvisorAccountBean implements Constants{
    private String name = null;
    private String email= null;
    private String department = null;
    private int ID = 0;
    private String tempPassword = null;
    private int rank = 0;
    private int lognum = 0;

    public ModifyAdvisorAccountBean() {
    }

    
    public String Advisor(){
       String returnMessage = SUCCESS_MESSAGE;
       
       AdvisorAccount AA = new AdvisorAccount();
       
      //boolean a = AA.initialize(this.name, this.email, this.department, this.tempPassword, this.rank);
      //initialize(String name, String email, String department, int ID, int rank, int lognum)
       boolean a=AA.initialize(this.name, this.email, this.department, this.ID, this.rank, 0);
       if (a == false)
       return this.CREATE_ADVISOR_FAIL;
       DatabaseManager dm = new DatabaseManager();
       //abhijeet 06-Mar-2016
       //System.out.println("here id="+this.getID());
       returnMessage = dm.modifyAccount(this.ID, AA);
       return returnMessage;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public int getID() {
        return ID;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public int getRank() {
        return rank;
    }
    
    public int getLogNum() {
        return lognum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public void setLogNum(int lognum) {
        this.lognum = lognum;
    }
    
}
