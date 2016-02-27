/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;

/**
 *
 * @author oguni
 */
public class DeleteAdvisor extends RDBImplCommand  {
    private int id;
    private String sqlQuery = "delete from user Where UserID = ?";

    
    public DeleteAdvisor(int id) {
        super();
        this.id = id;
    }
    
    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            processResult();
        } catch (SQLException e) {
            System.out.println("Delete Account query Failed");
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
