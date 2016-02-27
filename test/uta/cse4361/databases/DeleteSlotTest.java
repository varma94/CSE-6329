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
public class DeleteSlotTest extends BasicJDBCTestCaseAdapter{
    
    public DeleteSlotTest() {
    }

    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("DELETE FROM SLOT");

    }
    
    /**
     * Test of queryDB method, of class DeleteSlot.
     */
    @Test
    public void testQueryDB() throws Exception {
        System.out.println("queryDB");
        Slot slot = new AvailableSlot(new Date(System.currentTimeMillis()), 8, 0, 0);
        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(slot);
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 15, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 30, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 45, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 15, 0));
        SaveSlots saveInstance = new SaveSlots(slots);
        saveInstance.execute();
        verifySQLStatementExecuted("INSERT INTO SLOT");
        // FIXME - Does not match method signature!
        DeleteSlot deleteInstance = new DeleteSlot(new Date(System.currentTimeMillis()), 8, 9, 0, 30); // 
        deleteInstance.execute();
        verifySQLStatementExecuted("DELETE FROM SLOT");
    }
    
    @Test
    public void testSQLError()
    {
        prepareError();
        System.out.println("queryDB");
        Slot slot = new AvailableSlot(new Date(System.currentTimeMillis()), 8, 0, 0);
        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(slot);
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 15, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 30, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 45, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 15, 0));
        SaveSlots saveInstance = new SaveSlots(slots);
        saveInstance.execute();
        verifySQLStatementExecuted("INSERT INTO SLOT");
        // FIXME - Does not match method signature!
        DeleteSlot deleteInstance = new DeleteSlot(new Date(System.currentTimeMillis()), 8, 9, 0, 30); // 
        deleteInstance.execute();
        verifySQLStatementNotExecuted("DELETE FROM SLOT");
    }
}
