/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import uta.cse4361.businessobjects.StudentAccount;
import uta.cse4361.databases.DatabaseManager;

import static uta.cse4361.interfaces.Constants.SUCCESS_MESSAGE;

/**
 *
 * @author varma
 */
public class CreateStudentAccountBean {

    private String name = null;
    private String email= null;
    private String department = null;
    private int ID= 0;
    private String tempPassword = null;
    private int rank = 2;
    private int lognum =0;
    public CreateStudentAccountBean() {
    }

    
    public String Advisor(){
       String returnMessage = SUCCESS_MESSAGE;
       
       StudentAccount AA = new StudentAccount();
       boolean a = AA.initialize(this.name, this.email, this.department, this.tempPassword, this.rank, this.lognum);
       if (a == false)
           return "Create Advisor Fail";
       DatabaseManager dm = new DatabaseManager();
       returnMessage = dm.studentRegister(AA);
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
    public int getLognum() {
        return lognum;
    }
    
    public String getTempPassword() {
        return tempPassword;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLognum(int lognum) {
        this.lognum = lognum;
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
    
    
   
}
