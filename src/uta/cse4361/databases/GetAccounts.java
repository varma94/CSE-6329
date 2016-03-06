/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class GetAccounts extends RDBImplCommand {

    private int id;
    private String sqlQuery = "SELECT * FROM USER";

    public GetAccounts() {
        super();

    }

    @Override
    public void queryDB() throws SQLException {
        try {
        	statement = conn.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("GetAccounts query failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        try {
        	result = new ArrayList<AdvisorAccount>();
            while (resultSet.next()) 
            {
                AdvisorAccount account = new AdvisorAccount();
                int id = resultSet.getInt("UserID");
                String userEmail = resultSet.getString("UserEmail");
                //String userPassword = resultSet.getString("UserPassword");
                String userName = resultSet.getString("UserName");
                String userDepartment = resultSet.getString("UserDepartment");
                int userRank = resultSet.getInt("UserRank");
                int lognum = resultSet.getInt("lognum");
                account.setID(id);
                if (account.initialize(userName, userEmail, userDepartment, id, userRank, lognum)) 
                {
                    ((ArrayList<AdvisorAccount>) result).add(account);
                }
            }
        } catch (SQLException e) {
            System.out.println("GetAppintment process result failed");
            e.printStackTrace();
        }

    }

}
