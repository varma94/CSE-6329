/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;
import java.util.*;
import java.sql.*;
import uta.cse4361.businessobjects.AvailableSlot;
import uta.cse4361.businessobjects.Slot;
/**
 *
 * @author Han
 */

public class SaveSlots extends RDBImplCommand {
    ArrayList<Slot> slots;
    String sqlQuery = "INSERT INTO SLOT (SlotDate, SlotStartHour, SlotStartMin) VALUES (?, ?, ?)";
    
    public SaveSlots(ArrayList<Slot> slots){
        this.slots = slots;
    }
    
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery,  new String[]{"SlotID"});
            result = new ArrayList<Integer>();
            for(Slot s: slots){
                statement.setDate(1, new java.sql.Date(s.getDate().getTime()));
                statement.setInt(2, (s.getTime()/60));
                statement.setInt(3, (s.getTime()%60));
                statement.executeUpdate();
                resultSet = statement.getGeneratedKeys();
                if(resultSet.next() == true){
                    ((ArrayList<Integer>)result).add(resultSet.getInt(1));
                }
            }
        }
        catch (SQLException e){
            System.out.println("failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    //Result is the id's of inserted values
    //Must be done with each insert
    public void processResult(){
    }
}
