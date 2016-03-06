/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import uta.cse4361.businessobjects.StudentAppointment;

/**
 *
 * @author Han
 */
public class GetStudentAppointments extends RDBImplCommand {

    private String sqlQuery = "select * from appointment inner join user where user.useremail = appointment.StudentEmail and user.UserEmail = ?";
    private String email;

    public GetStudentAppointments(String email) {
        super();
        this.email = email;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        try {
            result = new ArrayList<StudentAppointment>();
            while (resultSet.next()) {
                StudentAppointment appt = new StudentAppointment();
                int id = resultSet.getInt("ApptID");
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
                if (appt.initialize(sMajor, sName, sID, sEmail, aName, type, description, date, sHour, eHour, sMinute, eMinute)) {
                    ((ArrayList<StudentAppointment>) result).add(appt);
                }
            }
        } catch (SQLException e) {
            System.out.println("GetAppointments Failed");
        }
    }

}
