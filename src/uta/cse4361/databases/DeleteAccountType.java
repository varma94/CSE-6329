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
class DeleteAccountType extends RDBImplCommand {

    private int id;
    private String sqlQuery = "delete from USERTYPES Where TypeID = ?";
    private boolean success = true;
    private String failureMessage = "Delete AccountType query Failed";

    
    public DeleteAccountType(int id) {
        super();
        this.id = id;
    }
    
    @Override
    public void queryDB() throws SQLException {
        if (id ==0 || id == 1 || id == 2){  //Administrator, Advisor, and Student user stypes cannot be deleted
            failureMessage = "Administrator, Advisor, and Student user types cannot be deleted";
            success = false;
            processResult();
            return;
        }
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            processResult();
        } catch (SQLException e) {
            System.out.println("Delete AccountType query Failed");
            success = false;
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        result = "";
        if (!success){
            result = failureMessage;
        }
    }
    
}
