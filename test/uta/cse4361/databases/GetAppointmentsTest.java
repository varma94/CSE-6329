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
import java.util.ArrayList;
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
public class GetAppointmentsTest extends BasicJDBCTestCaseAdapter{
    
    public GetAppointmentsTest() {
    }
    
    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        MockResultSet result = resultSetHandler.createResultSet();
        java.sql.Date date = new java.sql.Date(114, 10, 17);
        result.addColumn("ApptID", new Object[] {"1", "2"});
        result.addColumn("ApptDate", new Object[] {date, date});
        result.addColumn("ApptStartHour", new Object[]{"13", "10"});
        result.addColumn("ApptStartMin", new Object[]{"0", "15"});
        result.addColumn("ApptEndHour", new Object[]{"14", "10"});
        result.addColumn("ApptEndMin", new Object[]{"0", "30"});
        result.addColumn("ApptType", new Object[]{"Advising Type", "Type2"});
        result.addColumn("Description", new Object[]{"This is the test after save Appointment", "This is then other description"});
        result.addColumn("StudentID", new Object[]{"1000654321", "1000424242"});
        result.addColumn("StudentName", new Object[]{"Name", "SuperName"});
        result.addColumn("StudentEmail", new Object[]{"test@test.com", "testing@testing.com"});
        result.addColumn("AdvisorName", new Object[]{"Advisor Name", "Other Advisor"});
        result.addColumn("StudentMajor", new Object[]{"Software Engineering", "Computer Science"});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("SELECT * FROM APPOINTMENT");

    }

    /**
     * Test of queryDB method, of class GetAppointments.
     */
    @Test
    public void testQueryDB() throws Exception {
        prepareResultSet();
        System.out.println("queryDB");
        GetAppointments instance = new GetAppointments();
        instance.execute();
        verifySQLStatementExecuted("SELECT * FROM APPOINTMENT");
        ArrayList<Appointment> appts = (ArrayList<Appointment>)instance.getResult();
        
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(2, appts.size());
        
        assertEquals("Name", appts.get(0).getStudentName());
        assertEquals("SuperName", appts.get(1).getStudentName());
    }
    
    @Test
    public void testSQLError()
    {
        prepareError();
        System.out.println("queryDB");
        int expectedID = 1;
        RDBImplCommand instance = new GetAppointments();
        instance.execute();
        verifySQLStatementNotExecuted("SELECT * FROM APPOINTMENT");
    }
    
}
