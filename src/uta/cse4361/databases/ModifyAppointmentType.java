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
class ModifyAppointmentType extends RDBImplCommand {

    private String name;
    private int id;
    private int length;
    private String sqlQuery = "update APPOINTMENTTYPES set ApTypeName = ?, ApTypeLength = ? Where ApTypeID = ?";

    public ModifyAppointmentType(int id, String name, int length) {
        super();
        this.name = name;
        this.id = id;
        this.length = length;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setInt(2, length);
            statement.setInt(3, id);
            statement.executeUpdate();
            processResult();
        } catch (SQLException e) {
            System.out.println("Edit AppointmentType query Failed");
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
