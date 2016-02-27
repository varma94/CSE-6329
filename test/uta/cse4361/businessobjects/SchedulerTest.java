/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

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
import static uta.cse4361.interfaces.Constants.TIME_IS_NOT_FREE_FAULT;

/**
 *
 * @author Nabin
 */
public class SchedulerTest extends BasicJDBCTestCaseAdapter{
    
    public SchedulerTest() {
    }
    
    private long time;
    
    @BeforeClass
    public void setUpOnce()
    {
        time = System.currentTimeMillis();
    }
    
    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();
        
        MockResultSet result = resultSetHandler.createResultSet();
        java.sql.Date date = new java.sql.Date(time);
        result.addRow(new Object[] {"1", date, "8", "15"});
        result.addRow(new Object[] {"2", date, "8", "30"});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }


    /**
     * Test of schedule method, of class Scheduler.
     */
    @Test
    public void testSchedule() {
        prepareResultSet();
        String sName = "Name";
        String sID = "1000654321";
        String sEmail = "test@test.com";
        String sMajor = "Software Engineering";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test of Scheduler";
        Date date = new Date(time);
        int sH = 8;
        int eH = 8;
        int sM = 15;
        int eM = 30;
        Appointment appt = new Appointment();
        appt.initialize(sMajor, sName, sID, sEmail, aName, type, dp, date, sH, eH, sM, eM);
        
        Scheduler instance = new Scheduler();
        String expResult = "";
        String result = instance.schedule(appt);
        verifySQLStatementExecuted("SELECT * FROM \"AVAILSLOT\"");
        verifySQLStatementExecuted("INSERT INTO \"APPOINTMENT\"");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNotFreeSchedule() {
        prepareResultSet();
        String sName = "Name";
        String sID = "1000654321";
        String sEmail = "test@test.com";
        String sMajor = "Software Engineering";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test of Scheduler";
        Date date = new Date(time);
        int sH = 8;
        int eH = 9;
        int sM = 15;
        int eM = 00;
        Appointment appt = new Appointment();
        appt.initialize(sMajor, sName, sID, sEmail, aName, type, dp, date, sH, eH, sM, eM);
        
        Scheduler instance = new Scheduler();
        String expResult = TIME_IS_NOT_FREE_FAULT;
        String result = instance.schedule(appt);
        verifySQLStatementExecuted("SELECT * FROM \"AVAILSLOT\"");
        verifySQLStatementNotExecuted("INSERT INTO \"APPOINTMENT\"");
        assertEquals(expResult, result);
    }
    
}
