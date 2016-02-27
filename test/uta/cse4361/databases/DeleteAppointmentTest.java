/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import com.mockrunner.jdbc.PreparedStatementResultSetHandler;
import com.mockrunner.mock.jdbc.MockConnection;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Frank R.
 */
public class DeleteAppointmentTest extends BasicJDBCTestCaseAdapter{
    
    public DeleteAppointmentTest() {
    }

    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("DELETE FROM APPOINTMENT");

    }
    
    @Test
    public void testQueryDB() throws Exception {
        System.out.println("queryDB");
        String sName = "Changed Name";
        String sID = "1000654321";
        String sEmail = "test@test.com";
        String sMajor = "Software Engineering";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test after edit Appointment";
        Date date = new Date(114, 10, 17);
        int sH = 13;
        int eH = 0;
        int sM = 14;
        int eM = 0;
        String expectedResult = "This is the test after edit Appointment";
        Appointment appt = new Appointment();
        appt.initialize(sMajor, sName, sID, sEmail, aName, type, dp, date, sH, eH, sM, eM);
        SaveAppointment instance = new SaveAppointment(appt);
        instance.execute();
        verifySQLStatementExecuted("INSERT INTO APPOINTMENT");
        
        DeleteAppointment deleteInstance = new DeleteAppointment(1);
        deleteInstance.execute();
        verifySQLStatementExecuted("DELETE FROM APPOINTMENT");
    }
    
    @Test
    public void testSQLError()
    {
        prepareError();
        System.out.println("queryDB");
        String sName = "Changed Name";
        String sID = "1000654321";
        String sEmail = "test@test.com";
        String sMajor = "Software Engineering";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test after edit Appointment";
        Date date = new Date(114, 10, 17);
        int sH = 13;
        int eH = 0;
        int sM = 14;
        int eM = 0;
        String expectedResult = "This is the test after edit Appointment";
        Appointment appt = new Appointment();
        appt.initialize(sMajor, sName, sID, sEmail, aName, type, dp, date, sH, eH, sM, eM);
        SaveAppointment instance = new SaveAppointment(appt);
        instance.execute();
        verifySQLStatementExecuted("INSERT INTO APPOINTMENT");
        
        DeleteAppointment deleteInstance = new DeleteAppointment(1);
        verifySQLStatementNotExecuted("DELETE FROM APPOINTMENT");        
    }
}
