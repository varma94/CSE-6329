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
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse4361.businessobjects.Slot;
import uta.cse4361.businessobjects.SlotFactory;
import uta.cse4361.databases.DatabaseManager;

/**
 *
 * @author Han
 */
public class ScheduleAppointmentBeanTest extends BasicJDBCTestCaseAdapter implements uta.cse4361.interfaces.Constants{

    private final String sMajor = "Software Engineering";
    private final String sName = "First Last";
    private final String sID = "1000123456";
    private final String sEmail = "hzhang@mavs.uta.edu";
    private final String wrongID = "30123";
    private final String aName = "Advisor Name";
    private final String type = "Advising Type";
    private final String dp = "This is a description test";
    private final Date d = new Date(114, 10, 17);
    private final int sH = 11;
    private final int eH = 11;
    private final int sM = 0;
    private final int eM = 30;

    public ScheduleAppointmentBeanTest() {
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
        result.addRow(new Object[] {"1", date, "11", "0"});
        result.addRow(new Object[] {"2", date, "11", "15"});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    
    /**
     * Test of scheduleAppointment method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testScheduleAppointmentFail() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setStudentMajor(sMajor);
        instance.setStudentID(wrongID);
        instance.setStudentName(sName);
        instance.setStudentEmail(sEmail);
        instance.setAdvisorName(aName);
        instance.setType(type);
        instance.setDate(d);
        instance.setDescription(dp);
        instance.setStartHour(sH);
        instance.setEndHour(eH);
        instance.setStartMinute(sM);
        instance.setEndMinute(eM);
        String expResult = INITIALIZE_APPOINTMENT_FAIL;
        String result = instance.scheduleAppointment();
        assertEquals(expResult, result);
    }

    @Test
    public void testScheduleAppointmentSuccess() {
        prepareResultSet();
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        SlotFactory aff = SlotFactory.getInstance();
        DatabaseManager dbMgr = new DatabaseManager();
        ArrayList<Slot> slots = aff.generateSlots(d, sH, eH, sM, eM, 0, AVAILABLE_FLYWEIGHT_KEY);
        dbMgr.saveSlots(slots);
        instance.setStudentID(sID);
        instance.setStudentName(sName);
        instance.setAdvisorName(aName);
        instance.setType(type);
        instance.setDate(d);
        instance.setDescription(dp);
        instance.setStartHour(sH);
        instance.setEndHour(eH);
        instance.setStartMinute(sM);
        instance.setEndMinute(eM);
        String expResult = SUCCESS_MESSAGE;
        String result = instance.scheduleAppointment();
        assertEquals(expResult, result);
        verifySQLStatementExecuted("SELECT * FROM \"AVAILSLOT\"");
        verifySQLStatementExecuted("INSERT INTO \"APPOINTMENT\"");
    }
    
    @Test
    public void testGenerageMessage() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setStudentMajor(sMajor);
        instance.setStudentID(sID);
        instance.setStudentName(sName);
        instance.setStudentEmail(sEmail);
        instance.setAdvisorName(aName);
        instance.setType(type);
        instance.setDate(d);
        instance.setDescription(dp);
        instance.setStartHour(sH);
        instance.setEndHour(eH);
        instance.setStartMinute(sM);
        instance.setEndMinute(eM);
        String expectedResult = "You have an appointment with Advisor Name at 11/17/2014 from 11:0 to 11:30";
        // FIXME - Does not match method signature!
        String result = instance.generateMessage();
        System.out.println(result);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testSetStudentMajor() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setStudentMajor(sMajor);
        assertEquals(sMajor, instance.getStudentMajor());
    }
    /**
     * Test of setStudentName method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStudentName() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setStudentName(sName);
        assertEquals(sName, instance.getStudentName());
    }

    /**
     * Test of setStudentID method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStudentID() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setStudentID(sID);
        assertEquals(sID, instance.getStudentID());
    }

    /**
     * Test of setAdvisorName method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetAdvisorName() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setAdvisorName(aName);
        assertEquals(aName, instance.getAdvisorName());
    }
    
    /**
     * Test of setType method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetType() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setType(type);
        assertEquals(type, instance.getType());
    }
    
    /**
     * Test of setDescription method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetDescription() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setDescription(dp);
        assertEquals(dp, instance.getDescription());
    }

    /**
     * Test of setDate method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetDate() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setDate(d);
        assertEquals(d, instance.getDate());
    }

    /**
     * Test of setStartHour method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStartHour() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setStartHour(sH);
        assertEquals(sH, instance.getStartHour());
    }

    /**
     * Test of setEndHour method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetEndHour() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setEndHour(eH);
        assertEquals(eH, instance.getEndHour());
    }

    /**
     * Test of setStartMinute method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStartMinute() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setStartMinute(sM);
        assertEquals(sM, instance.getStartMinute());
    }

    /**
     * Test of setEndMinute method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetEndMinute() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        instance.setEndMinute(eM);
        assertEquals(eM, instance.getEndMinute());
    }

    /**
     * Test of getStudentName method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetStudentName() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        String expResult = "First Last";
        instance.setStudentName(expResult);
        String result = instance.getStudentName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStudentID method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetStudentID() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        String expResult = "1000111122";
        instance.setStudentID(expResult);
        String result = instance.getStudentID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdvisorName method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetAdvisorName() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        String expResult = "Advisor Name";
        instance.setAdvisorName(expResult);
        String result = instance.getAdvisorName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetDescription() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        String expResult = "The test of description";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartHour method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetStartHour() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        int expResult = 3;
        instance.setStartHour(expResult);
        int result = instance.getStartHour();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndHour method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetEndHour() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        int expResult = 5;
        instance.setEndHour(expResult);
        int result = instance.getEndHour();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartMinute method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetStartMinute() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        int expResult = 27;
        instance.setStartMinute(expResult);
        int result = instance.getStartMinute();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndMinute method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetEndMinute() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        int expResult = 51;
        instance.setEndMinute(expResult);
        int result = instance.getEndMinute();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testGetDate() {
        ScheduleAppointmentBean instance = new ScheduleAppointmentBean();
        Date expResult = new Date();
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }
}
