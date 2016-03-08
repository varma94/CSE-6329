/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;

import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class EditAdvisor extends RDBImplCommand {

    private AdvisorAccount advisor;
    private int id;
    private String sqlQuery = "update user set UserEmail = ?, UserName = ?, UserDepartment= ?, UserRank = ?, lognum = ? Where UserID = ?";

    public EditAdvisor(int id, AdvisorAccount advisor) {
        super();
        this.advisor = advisor;
        this.id = id;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, advisor.getEmail());
            statement.setString(2, advisor.getName());
            statement.setString(3, advisor.getDepartment());
            statement.setInt(4, advisor.getRank());
            statement.setInt(5, advisor.getLogNum()); // Abhijeet Chopra Mar 8: Changed sequence of SQL command
            statement.setInt(6, id);
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