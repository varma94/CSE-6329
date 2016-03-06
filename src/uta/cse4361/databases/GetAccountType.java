/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.AccountType;

/**
 *
 * @author oguni
 */
public class GetAccountType extends RDBImplCommand{
    
    String sqlQuery= "SELECT * FROM USERTYPES WHERE TypeID = ?";
    private int id;
    
    public GetAccountType(int id){
        this.id = id;
    }

    @Override
    public void queryDB() throws SQLException {
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            processResult();
        }
        catch (SQLException e) {
            System.out.println("GetUserType failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        try{
            
            if(resultSet.next()){
                String name = resultSet.getString("TypeName");
                int ID = resultSet.getInt("TypeID");
                result = new AccountType(ID, name);
            }
            else{
                result = null;
            }
        }
        catch(SQLException e){
            System.out.println("Get Account Type process failed");
        }
    }
    
}
