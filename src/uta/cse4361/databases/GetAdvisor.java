/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.AdvisorAccount;

/**
 *
 * @author Andrew
 */
public class GetAdvisor extends RDBImplCommand{
    private String email;
    private String sqlQuery = "SELECT * FROM USER WHERE UserEmail = ?";
    
    public GetAdvisor(String email){
        this.email = email;
    }
    
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            processResult();
        }
        catch (SQLException e) {
            System.out.println("GetAdvisor failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    
    public void processResult(){
        try{
            
            if(resultSet.next()){
                String name = resultSet.getString("UserName");
                String email = resultSet.getString("UserEmail");
                String department = resultSet.getString("UserDepartment");
                int ID = resultSet.getInt("UserID");
                int rank = resultSet.getInt("UserRank");
                result = new AdvisorAccount();
                ((AdvisorAccount)result).initialize(name, email, department, ID, rank);
            }
            else{
                result = null;
            }
        }
        catch(SQLException e){
            System.out.println("Get Advisor process failed");
        }
    }
}
