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
public class GetAvailSlots extends RDBImplCommand{
    private int advisorID =-1;
    private int apptType = -1;
    String sqlQuery = "SELECT * FROM AVAILSLOT";
    
    public GetAvailSlots(){}
    
    public GetAvailSlots(int advisorID, int apptType){
        this.advisorID = advisorID;
        this.apptType = apptType;
        sqlQuery = "SELECT * FROM AVAILSLOT WHERE advisorID = ? AND appointmentTypeID = ?";
    }
    
    public void queryDB() throws SQLException{
        try {
            statement = conn.prepareStatement(sqlQuery);
            if (advisorID != -1){
                statement.setInt(1, advisorID);
                statement.setInt(2, apptType);
            }
            
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("failed");
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
                int appTypeID = resultSet.getInt("appointmentTypeID");
                int advisorID = resultSet.getInt("advisorID");
                Slot s = new AvailableSlot(d, startHour, startMin, id, appTypeID, advisorID);
                ((ArrayList<Slot>) result).add(s);
            }
        } catch (SQLException e) {
            System.out.println("Get Avail Slots Failed");
            result = null;
        }
    }
    
}
