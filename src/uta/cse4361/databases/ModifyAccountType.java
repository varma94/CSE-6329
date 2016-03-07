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
class ModifyAccountType extends RDBImplCommand {

    private String name;
    private int id;
    private int privilege;
    private String sqlQuery = "update USERTYPES set TypeName = ?, privilege = ? Where TypeID = ?";

    public ModifyAccountType(int id, String name, int privilege) {
        super();
        this.name = name;
        this.id = id;
        this.privilege = privilege;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setInt(2, privilege);
            statement.setInt(3, id);
            statement.executeUpdate();
            processResult();
        } catch (SQLException e) {
            System.out.println("Edit AccountType query Failed");
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
