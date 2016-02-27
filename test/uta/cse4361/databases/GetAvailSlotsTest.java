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
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Andrew
 */
public class GetAvailSlotsTest extends BasicJDBCTestCaseAdapter{
    
    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        MockResultSet result = resultSetHandler.createResultSet();
        result.addRow(new String[] {"1", "2014-11-14", "8", "15"});
        result.addRow(new String[] {"2", "2014-11-14", "8", "30"});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("SELECT * FROM AVAILSLOT");

    }
    /**
     * Test of queryDB method, of class GetAvailSlots.
     */
    @Test
    public void testQueryDB() throws Exception {
        System.out.println("queryDB");
        prepareResultSet();
        GetAvailSlots instance = new GetAvailSlots();
        instance.execute();
        verifySQLStatementExecuted("SELECT * FROM AVAILSLOT");
        assertNotNull(instance.getResult());
        
        ArrayList<Slot> slots = (ArrayList<Slot>)instance.getResult();
        
        assertEquals(8, slots.get(0).getHour());
        assertEquals(15, slots.get(0).getMinute());
        assertFalse(slots.get(0).isAppointment());
        
        assertEquals(8, slots.get(1).getHour());
        assertEquals(30, slots.get(1).getMinute());
        assertFalse(slots.get(1).isAppointment());
    }
    
    @Test
    public void testSQLError() throws Exception {
        System.out.println("queryDB");
        prepareError();
        GetAvailSlots instance = new GetAvailSlots();
        instance.execute();
        verifySQLStatementNotExecuted("SELECT * FROM AVAILSLOT");
    }

}
