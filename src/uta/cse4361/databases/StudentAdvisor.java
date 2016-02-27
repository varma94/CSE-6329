/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.StudentAccount;

/**
 *
 * @author Andrew
 */
public class StudentAdvisor extends RDBImplCommand {
    
    private StudentAccount sa;
    private String sqlQuery = "INSERT INTO USER(UserEmail, UserPassword,"
            + " UserName, UserDepartment, UserRank) VALUES (?, ?, ?, ?, ?)";
    public StudentAdvisor(StudentAccount sa){
        this.sa = sa;
    }
    
    @Override
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, sa.getEmail());
            statement.setString(2, sa.getTempPassword());
            statement.setString(3, sa.getName());
            statement.setString(4, sa.getDepartment());
            statement.setInt(5, sa.getRank());
            statement.executeUpdate();
            processResult();
        }
        catch(SQLException e){
        System.out.println("RegisterStudent Failed");
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
