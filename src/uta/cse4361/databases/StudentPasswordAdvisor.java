/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.StudentPasswordAccount;

/**
 *
 * @author Andrew
 */
public class StudentPasswordAdvisor extends RDBImplCommand {
    
    private StudentPasswordAccount sa;
    private String sqlQuery = "Update user set UserPassword = ? where UserEmail=?";
    public StudentPasswordAdvisor(StudentPasswordAccount sa){
        this.sa = sa;
    }
    
    @Override
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, sa.getPassword());
            statement.setString(2, sa.getEmail());
            statement.executeUpdate();
            processResult();
        }
        catch(SQLException e){
        System.out.println("Password Change Failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    
    @Override
    public void processResult(){
        result ="";
    }
}
