/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import uta.cse4361.businessobjects.StudentAccount;
import uta.cse4361.businessobjects.StudentPasswordAccount;
import uta.cse4361.databases.DatabaseManager;

import static uta.cse4361.interfaces.Constants.SUCCESS_MESSAGE;

/**
 *
 * @author varma
 */
public class UpdatePassword {

    private String email = null;
    private String newPassword = null;
    private int rank = 2;

    public UpdatePassword() {
    }

    
    public String Advisor(){
       String returnMessage = SUCCESS_MESSAGE;
       
       StudentPasswordAccount AA = new StudentPasswordAccount();
       boolean a = AA.initialize(this.email, this.newPassword);
       if (a == false)
           return "Password Update Failed";
       DatabaseManager dm = new DatabaseManager();
       returnMessage = dm.studentPasswordChange(AA);
       return returnMessage;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = StudentAccount.hashPassword(newPassword); 
    }
     public String getNewPassword() {
        return newPassword; 
    }
      public void setEmail(String email) {
        this.email = email; 
    }
       public String getEmail() {
        return email; 
    }
   
   
}
