/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.AppointmentType;

/**
 *
 * @author oguni
 */
public class GetAppointmentTypesObjs extends RDBImplCommand{
    
    private int id;
    private String sqlQuery = "SELECT * FROM APPOINTMENTTYPES";
    
    public GetAppointmentTypesObjs(){
        super();
    }
    
    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("GetAppointmentTypes query failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        try {
            result = new ArrayList<AppointmentType>();
            while (resultSet.next()) 
            {
                int id = resultSet.getInt("ApTypeID");
                String name = resultSet.getString("ApTypeName");
                AppointmentType app = new AppointmentType(id, name);
                ((ArrayList<AppointmentType>) result).add(app);
            }
        } catch (SQLException e) {
            System.out.println("GetAppointmentType process result failed");
            e.printStackTrace();
        }

    }
    
}
