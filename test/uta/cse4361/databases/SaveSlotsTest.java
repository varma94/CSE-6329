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
import uta.cse4361.businessobjects.Slot;
import uta.cse4361.businessobjects.AvailableSlot;
import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class SaveSlotsTest extends BasicJDBCTestCaseAdapter{
    
    public SaveSlotsTest() {
    }

    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();
        
        MockResultSet result = resultSetHandler.createResultSet();
        result.addRow(new Object[] {"1"});
        result.addRow(new Object[] {"2"});
        result.addRow(new Object[] {"3"});
        result.addRow(new Object[] {"4"});
        result.addRow(new Object[] {"5"});
        result.addRow(new Object[] {"6"});
        
        
        resultSetHandler.prepareGlobalGeneratedKeys(result);
    }
    
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("INSERT INTO SLOT");

    }
    
    /**
     * Test of queryDB method, of class SaveSlots.
     */
    @Test
    public void testQueryDB() throws Exception {
        prepareResultSet();
        System.out.println("queryDB");
        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 15, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 30, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 45, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 15, 0));
        SaveSlots instance = new SaveSlots(slots);
        //instance.execute();
        instance.execute();
        verifySQLStatementExecuted("INSERT INTO SLOT");
        //must have inserted all instances to result
        ArrayList<Integer> savedSlots = (ArrayList<Integer>)instance.getResult();
        assertEquals(6, savedSlots.size());
        
        
    }
    
    @Test
    public void testSQLError() throws Exception {
        prepareError();
        System.out.println("queryDB");
        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 15, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 30, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 45, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 15, 0));
        SaveSlots instance = new SaveSlots(slots);
        //instance.execute();
        instance.execute();
        verifySQLStatementNotExecuted("INSERT INTO SLOT");
    }
    

    
    
}
