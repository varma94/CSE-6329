/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.AdvisorAccount;

/**
 *
 * @author oguni
 */
public class GetAppointmentTypes extends RDBImplCommand{
    private final String sqlQuery = "SELECT ApTypeName FROM APPOINTMENTTYPES";
    
    public GetAppointmentTypes(){
    }
    
    @Override
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            processResult();
        }
        catch (SQLException e) {
            System.out.println("Get Appointment Types failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    
    @Override
    public void processResult(){
        try{ 
            result = new ArrayList<String>();
            while(resultSet.next()){
                String name = resultSet.getString("ApTypeName");
                ((ArrayList<String>) result).add(name);
            }
        }
        catch(SQLException e){
            System.out.println("Get Advisor process failed");
        }
    }
}
