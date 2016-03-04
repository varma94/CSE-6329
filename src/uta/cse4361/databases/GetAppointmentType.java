/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.AppointmentType;

/**
 *
 * @author oguni
 */
class GetAppointmentType extends RDBImplCommand {

    private int id;
    private String sqlQuery = "SELECT * FROM APPOINTMENTTYPES WHERE ApTypeID = ?";
    
    public GetAppointmentType(int id){
        this.id = id;
    }
    
    @Override
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            processResult();
        }
        catch (SQLException e) {
            System.out.println("GetAppointmentType failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    
    @Override
    public void processResult(){
        try{
            
            if(resultSet.next()){
                int id = resultSet.getInt("ApTypeID");
                String name = resultSet.getString("ApTypeName");
                result = new AppointmentType(id, name);
            }
            else{
                result = null;
            }
        }
        catch(SQLException e){
            System.out.println("Get AppointmentType process failed");
        }
    }
}
