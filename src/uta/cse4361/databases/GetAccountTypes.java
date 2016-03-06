/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.AccountType;

/**
 *
 * @author oguni
 */
public class GetAccountTypes extends RDBImplCommand{
    
    private final String sqlQuery = "SELECT * FROM USERTYPES";

    public GetAccountTypes() {
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
        	result = new ArrayList<>();
            while (resultSet.next()) 
            {
                int id = resultSet.getInt("TypeID");
                String name = resultSet.getString("TypeName");
                ((ArrayList<AccountType>) result).add(new AccountType(id, name));
            }
        } catch (SQLException e) {
            System.out.println("GetAccountType process result failed");
            e.printStackTrace();
        }
    }
    
}
