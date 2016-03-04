/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.AdvisorAccount;

/**
 *
 * @author oguni
 */
class GetAdvisors extends RDBImplCommand {
    
    private String sqlQuery = "SELECT * FROM USER WHERE UserRank = ?";

    public GetAdvisors() {
        super();
    }
    
    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, 0);
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("GetAdvisors query failed");
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

                account.setID(id);
                if (account.initialize(userName, userEmail, userDepartment, id, userRank)) 
                {
                    ((ArrayList<AdvisorAccount>) result).add(account);
                }
            }
        } catch (SQLException e) {
            System.out.println("GetAdvisors process result failed");
            e.printStackTrace();
        }

    }
}
