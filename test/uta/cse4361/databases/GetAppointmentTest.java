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
public class GetAppointmentTest extends BasicJDBCTestCaseAdapter{
    
    public GetAppointmentTest() {
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
        result.addColumn("Description", new Object[]{"This is the test after save Appointment"});
        result.addColumn("StudentID", new Object[]{"1000654321"});
        result.addColumn("StudentName", new Object[]{"Name"});
        result.addColumn("StudentEmail", new Object[]{"test@test.com"});
        result.addColumn("AdvisorName", new Object[]{"Advisor Name"});
        result.addColumn("StudentMajor", new Object[]{"Software Engineering"});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("SELECT * FROM APPOINTMENT");

    }

    /**
     * Test of queryDB method, of class GetAppointment.
     */
    @Test
    public void testQueryDB() throws Exception {
        prepareResultSet();
        System.out.println("queryDB");
        int expectedID = 1;
        RDBImplCommand instance = new GetAppointment(expectedID);
        instance.execute();
        verifySQLStatementExecuted("SELECT * FROM APPOINTMENT");
        Appointment newAppt = (Appointment)instance.getResult();
        assertEquals(expectedID, newAppt.getApptID());
        assertEquals("This is the test after save Appointment", newAppt.getDescription());
        assertEquals("Name", newAppt.getStudentName());
    }
    
    @Test
    public void testSQLError()
    {
        prepareError();
        System.out.println("queryDB");
        int expectedID = 1;
        RDBImplCommand instance = new GetAppointment(expectedID);
        instance.execute();
        verifySQLStatementNotExecuted("SELECT * FROM APPOINTMENT");
        
        
    }
    
}
