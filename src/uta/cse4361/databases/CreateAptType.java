/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.AdvisorAccount;

/**
 *
 * @author oguni
 */
class CreateAptType extends RDBImplCommand {

    private String name;
    private String sqlQuery = "INSERT INTO APPOINTMENTTYPES(ApTypeName) VALUES (?)";
    public CreateAptType(String name){
        this.name = name;
    }
    
    @Override
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.executeUpdate();
            processResult();
        }
        catch(SQLException e){
        System.out.println("CreatAptType Failed");
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
