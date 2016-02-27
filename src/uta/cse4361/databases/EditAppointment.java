/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class EditAppointment extends RDBImplCommand{
    
    private int id;
    private Appointment appointment;
    private String sqlQuery = "UPDATE APPOINTMENT SET ApptDate = ?, ApptStartHour = ?, ApptStartMin = ?, "
                                                        + "ApptEndHour = ?, ApptEndMin = ?, ApptType = ?, "
                                                        + "Description = ?, StudentID = ?, StudentName = ?, "
                                                        + "StudentMajor = ?, StudentEmail = ?, AdvisorName = ? "
                                                        + "WHERE ApptID = ?";
    
    
    
    public EditAppointment (int apptID, Appointment appt) {
        super();
        this.id = apptID;
        this.appointment = appt;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            statement.setInt(2, appointment.getStartHour());
            statement.setInt(3, appointment.getStartMinute());
            statement.setInt(4, appointment.getEndHour());
            statement.setInt(5, appointment.getEndMinute());
            statement.setString(6, appointment.getType());
            statement.setString(7, appointment.getDescription());
            statement.setString(8, appointment.getStudentID());
            statement.setString(9, appointment.getStudentName());
            statement.setString(10, appointment.getStudentMajor());
            statement.setString(11, appointment.getStudentEmail());
            statement.setString(12, appointment.getAdvisorName());
            statement.setInt(13, id);
            statement.executeUpdate();
            processResult();
        } catch (SQLException e) {
            System.out.println("EditAppointment query failed");
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        result = "";
    }
    
}
