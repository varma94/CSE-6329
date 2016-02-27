/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.AvailableSlot;
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Andrew
 */
public class GetAvailSlotsByTime extends RDBImplCommand {
    
    int startHour;
    int startMin;
    int endHour;
    int endMin;
    java.sql.Date date;
    
    private String sqlQuery = "SELECT * FROM AVAILSLOT WHERE "
            + "(SlotDate = ? AND SlotStartHour = ? AND SlotStartMin >= ? )" //slots during start hour
            + "OR (SlotDate = ? AND SlotStartHour > ? AND SlotStartHour < ?)"//slots between start and end hour
            + "OR (SlotDate = ? AND SlotStartHour = ? AND SlotStartMin < ?)"; // slots during end hour
    
    public GetAvailSlotsByTime(java.util.Date date, int startHour, int endHour, int startMin, int endMin){
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.date = new java.sql.Date(date.getTime());
    }
    
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setDate(1, date);
            statement.setInt(2, startHour);
            statement.setInt(3, startMin); //slots during start hour
            statement.setDate(4, date);
            statement.setInt(5, startHour);
            statement.setInt(6, endHour); //slots between start and end hour
            statement.setDate(7,date);
            statement.setInt(8, endHour);
            statement.setInt(9, endMin); // slots during end hour
            resultSet = statement.executeQuery();
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
        try {
            result = new ArrayList<Slot>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                java.util.Date d = new java.util.Date(((java.sql.Date)resultSet.getDate(2)).getTime());
                int startHour = resultSet.getInt(3);
                int startMin = resultSet.getInt(4);
                Slot s = new AvailableSlot(d, startHour, startMin, id);
                ((ArrayList<Slot>) result).add(s);
            }
        } catch (SQLException e) {
            System.out.println("Get Avail Slots Failed");
            result = null;
        }
    }
    
}