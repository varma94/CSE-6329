/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;

import uta.cse4361.businessobjects.StudentAccount;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class EditStudent extends RDBImplCommand {

    private StudentAccount student;
    private String id;
    private String sqlQuery = "update user set UserEmail = ?, UserName = ?, UserDepartment= ?, UserRank = ? Where UserEmail = ?";

    public EditStudent(String id, StudentAccount student) {
        super();
        this.student = student;
        this.id = id;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, student.getEmail());
            statement.setString(2, student.getName());
            statement.setString(3, student.getDepartment());
            statement.setInt(4, student.getRank());
            statement.setString(5, id);
            statement.executeUpdate();
            processResult();
        } catch (SQLException e) {
            System.out.println("Edit Account query Failed");
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