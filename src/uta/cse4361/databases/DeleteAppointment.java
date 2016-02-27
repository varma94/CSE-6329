/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;

/**
 *
 * @author Han
 */
public class DeleteAppointment extends RDBImplCommand{
    
    private int id;
    private String sqlQuery = "DELETE FROM APPOINTMENT WHERE ApptID = ?";
    
    public DeleteAppointment(int apptID) {
        super();
        this.id = apptID;
    }

    @Override
    public void queryDB() throws SQLException {
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            processResult();
        }
        catch (SQLException e){
            System.out.println("DeleteAppointment query failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        result = "";
    }
    
}
