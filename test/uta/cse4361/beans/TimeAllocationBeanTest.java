/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

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

/**
 *
 * @author Han
 */
public class TimeAllocationBeanTest extends BasicJDBCTestCaseAdapter implements uta.cse4361.interfaces.Constants{
    
    public TimeAllocationBeanTest() {
    }
    
    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();
        
        MockResultSet result = resultSetHandler.createResultSet();
        result.addRow(new Object[] {"1"});
        result.addRow(new Object[] {"2"});
        result.addRow(new Object[] {"3"});

        resultSetHandler.prepareGlobalGeneratedKeys(result);
    }

    /**
     * Test of allocateTime method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testAllocateTime() {
        prepareResultSet();
        TimeAllocationBean instance = new TimeAllocationBean();
        instance.setDate(new Date());
        instance.setStartHour(15);
        instance.setEndHour(15);
        instance.setStartMinute(00);
        instance.setEndMinute(45);
        String expResult = SUCCESS_MESSAGE;
        String result = instance.allocateTime();
        verifySQLStatementExecuted("INSERT INTO \"SLOT\"");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAllocateTimeRepeating() {
        TimeAllocationBean instance = new TimeAllocationBean();
        instance.setDate(new Date());
        instance.setStartHour(15);
        instance.setEndHour(15);
        instance.setStartMinute(30);
        instance.setEndMinute(45);
        String expResult = SUCCESS_MESSAGE;
        String result = instance.allocateTimeRepeat(3);
        verifySQLStatementExecuted("INSERT INTO \"SLOT\"");
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetDate() {
        Date d = new Date();
        TimeAllocationBean instance = new TimeAllocationBean();
        instance.setDate(d);
        assertEquals(d, instance.getDate());
    }

    /**
     * Test of setStartHour method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetStartHour() {
        int sH = 11;
        TimeAllocationBean instance = new TimeAllocationBean();
        instance.setStartHour(sH);
        assertEquals(sH, instance.getStartHour());
    }

    /**
     * Test of setEndHour method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetEndHour() {
        int eH = 12;
        TimeAllocationBean instance = new TimeAllocationBean();
        instance.setEndHour(eH);
        assertEquals(eH, instance.getEndHour());
    }

    /**
     * Test of setStartMinute method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetStartMinute() {
        int sM = 30;
        TimeAllocationBean instance = new TimeAllocationBean();
        instance.setStartMinute(sM);
        assertEquals(sM, instance.getStartMinute());
    }

    /**
     * Test of setEndMinute method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetEndMinute() {
        int eM = 26;
        TimeAllocationBean instance = new TimeAllocationBean();
        instance.setEndMinute(eM);
        assertEquals(eM, instance.getEndMinute());
    }

    /**
     * Test of getDate method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testGetDate() {
        TimeAllocationBean instance = new TimeAllocationBean();
        Date expResult = new Date();
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartHour method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testGetStartHour() {
        TimeAllocationBean instance = new TimeAllocationBean();
        int expResult = 8;
        instance.setStartHour(expResult);
        int result = instance.getStartHour();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndHour method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testGetEndHour() {
        TimeAllocationBean instance = new TimeAllocationBean();
        int expResult = 23;
        instance.setEndHour(expResult);
        int result = instance.getEndHour();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartMinute method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testGetStartMinute() {
        TimeAllocationBean instance = new TimeAllocationBean();
        int expResult = 0;
        instance.setStartMinute(expResult);
        int result = instance.getStartMinute();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndMinute method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testGetEndMinute() {
        TimeAllocationBean instance = new TimeAllocationBean();
        int expResult = 50;
        instance.setEndMinute(expResult);
        int result = instance.getEndMinute();
        assertEquals(expResult, result);
    }
    
}
