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
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Andrew
 */
public class GetApptSlotsTest extends BasicJDBCTestCaseAdapter {
    
    public GetApptSlotsTest() {
    }
    
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("SELECT * FROM APPTSLOT");

    }
    
    private void prepareResultSet (){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        MockResultSet result = resultSetHandler.createResultSet();
        result.addRow(new String[] {"1", "2014-11-14", "8", "15", "9"});
        result.addRow(new String[] {"2", "2014-11-14", "8", "30", "4"});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    /**
     * Test of queryDB method, of class GetApptSlots.
     */
    @Test
    public void testQueryDB() throws Exception {
        System.out.println("queryDB");
        prepareResultSet();
        GetApptSlots instance = new GetApptSlots();
        instance.execute();
        assertNotNull(instance.getResult());
        verifySQLStatementExecuted("SELECT * FROM APPTSLOT");
        
        ArrayList<Slot> slots = (ArrayList<Slot>)instance.getResult();
        
        assertEquals(8, slots.get(0).getHour());
        assertEquals(15, slots.get(0).getMinute());
        assertTrue(slots.get(0).isAppointment());
        assertEquals(9, slots.get(0).getAppointmentId());
        
        assertEquals(8, slots.get(1).getHour());
        assertEquals(30, slots.get(1).getMinute());
        assertTrue(slots.get(1).isAppointment());
        assertEquals(4, slots.get(1).getAppointmentId());
    }
    
    @Test
    public void testSQLError()
    {
        System.out.println("queryDB");
        prepareError();
        GetApptSlots instance = new GetApptSlots();
        instance.execute();
        verifySQLStatementNotExecuted("SELECT * FROM APPTSLOT");
    }
    
}
