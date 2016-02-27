/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Han
 */
public class DeleteSlot extends RDBImplCommand{
    
    int startHour;
    int startMin;
    int endHour;
    int endMin;
    int slotID;
    java.sql.Date date;
    
    private String sqlQuery = "DELETE FROM SLOT WHERE "
            + "(SlotDate = ? AND SlotStartHour = ? AND SlotStartMin >= ? AND SlotStartHour = ? AND SlotStartMin < ?)"
            + "OR (SlotDate = ? AND SlotStartHour = ? AND SlotStartMin >= ? )" //slots during start hour
            + "OR (SlotDate = ? AND SlotStartHour > ? AND SlotStartHour < ?)"//slots between start and end hour
            + "OR (SlotDate = ? AND SlotStartHour = ? AND SlotStartMin < ?)" // slots during end hour
            +" OR (SlotID = ?)";

    public DeleteSlot(java.util.Date date, int startHour, int endHour, int startMin, int endMin, int slotID){
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.slotID = slotID;
        this.date = new java.sql.Date(date.getTime());
    }
    
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setDate(1, date);
            statement.setInt(2, startHour);
            statement.setInt(3, startMin); //slots during start hour
            statement.setInt(4, endHour);
            statement.setInt(5, endMin);
            statement.setDate(6, date);
            statement.setInt(7, startHour);
            statement.setInt(8, startMin); //slots between start and end hour
            statement.setDate(9,date);
            statement.setInt(10, startHour);
            statement.setInt(11, endHour); // slots during end hour
            statement.setDate(12, date);
            statement.setInt(13, endHour);
            statement.setInt(14, endMin);
            statement.setInt(15, slotID);
            statement.executeUpdate();
            processResult();
        }
        catch (SQLException e){
            System.out.println("failed");
            e.printStackTrace();
            conn.close();
        } finally {
            statement.close();
        }
    }
    
    public void processResult(){
            result = "";
    }
}