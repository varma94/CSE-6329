/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import com.mockrunner.jdbc.PreparedStatementResultSetHandler;
import com.mockrunner.mock.jdbc.MockConnection;
import com.mockrunner.mock.jdbc.MockResultSet;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class EditAppointmentTest extends BasicJDBCTestCaseAdapter{
    
    public EditAppointmentTest() {
    }
    
    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        MockResultSet result = resultSetHandler.createResultSet();
        java.sql.Date date = new java.sql.Date(114, 10, 17);
        result.addColumn("ApptDate", new Object[] {date});
        result.addColumn("ApptStartHour", new Object[]{"13"});
        result.addColumn("ApptStartMin", new Object[]{"0"});
        result.addColumn("ApptEndHour", new Object[]{"14"});
        result.addColumn("ApptEndMin", new Object[]{"0"});
        result.addColumn("ApptType", new Object[]{"Advising Type"});
        result.addColumn("Description", new Object[]{"This is the test after edit Appointment"});
        result.addColumn("StudentID", new Object[]{"1000654321"});
        result.addColumn("StudentName", new Object[]{"Changed Name"});
        result.addColumn("StudentEmail", new Object[]{"test@test.com"});
        result.addColumn("AdvisorName", new Object[]{"Advisor Name"});
        result.addColumn("StudentMajor", new Object[]{"Software Engineering"});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("UPDATE APPOINTMENT");

    }

    /**
     * Test of queryDB method, of class EditAppointment.
     */
    @Test
    public void testQueryDB() throws Exception {
        prepareResultSet();
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
        EditAppointment instance = new EditAppointment(1, appt);
        instance.execute(); 
        verifySQLStatementExecuted("UPDATE APPOINTMENT");
        Appointment newAppt = new DatabaseManager().getAppointment(1);
        verifySQLStatementExecuted("SELECT * FROM APPOINTMENT");
        String result = newAppt.getDescription();
        assertEquals(expectedResult, result);   
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
        EditAppointment instance = new EditAppointment(1, appt);
        instance.execute(); 
        verifySQLStatementNotExecuted("UPDATE APPOINTMENT");
    }
    

}
