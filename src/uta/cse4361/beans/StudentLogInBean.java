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
public class StudentLogInBean {
    private String studentEmail= null;
    private String studentPassword= null;

    public StudentLogInBean() {
    }

    public String LogIn(){
        String Msg = SUCCESS_MESSAGE;
        DatabaseManager DM = new DatabaseManager();
        Msg = DM.validate(this.studentEmail, this.studentPassword);           
        return Msg;
    }
    public String getEmail() {
        return studentEmail;
    }

    public String getPassword() {
        return studentPassword;
    }

    public void setEmail(String email) {
        this.studentEmail = email;
    }

    public void setPassword(String password) {
        this.studentPassword = StudentAccount.hashPassword(password);
    }
    
    
}
