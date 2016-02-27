/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.Date;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class GetAppointment extends RDBImplCommand {

    private int id;
    private String sqlQuery = "SELECT * FROM APPOINTMENT WHERE ApptID = ?";

    public GetAppointment(int apptID) {
        super();
        id = apptID;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("GetAppointment query failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        try {
            resultSet.next();
            result = new Appointment();
            Appointment appt = new Appointment();
            Date date = resultSet.getDate("ApptDate");
            int sHour = resultSet.getInt("ApptStartHour");
            int sMinute = resultSet.getInt("ApptStartMin");
            int eHour = resultSet.getInt("ApptEndHour");
            int eMinute = resultSet.getInt("ApptEndMin");
            String type = resultSet.getString("ApptType");
            String description = resultSet.getString("Description");
            String sID = resultSet.getString("StudentID");
            String sName = resultSet.getString("StudentName");
            String sEmail = resultSet.getString("StudentEmail");
            String aName = resultSet.getString("AdvisorName");
            String sMajor = resultSet.getString("StudentMajor");
            appt.setApptID(id);
            appt.initialize(sMajor, sName, sID, sEmail, aName, type, description, date, sHour, eHour, sMinute, eMinute);
            result = appt;
        } catch (SQLException e) {
            System.out.println("GetAppintment process result failed");
            e.printStackTrace();
        }

    }

}
