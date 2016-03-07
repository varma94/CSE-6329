/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;

/**
 *
 * @author oguni
 */
public class CreateAccountType extends RDBImplCommand{
    private String name;
    private int privilege;
    private String sqlQuery = "INSERT INTO USERTYPES(TypeName, privilege) VALUES (?, ?)";
    
    public CreateAccountType(String name, int privilege){
        this.name = name;
        this.privilege = privilege;
    }
    
    @Override
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setInt(2, privilege);
            statement.executeUpdate();
            processResult();
        }
        catch(SQLException e){
        System.out.println("CreatAccountType Failed");
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
