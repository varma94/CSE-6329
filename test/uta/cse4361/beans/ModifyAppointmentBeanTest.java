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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frank R.
 */
public class ModifyAppointmentBeanTest extends BasicJDBCTestCaseAdapter implements uta.cse4361.interfaces.Constants{

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
    
    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        MockResultSet result = resultSetHandler.createResultSet();
        java.sql.Date date = new java.sql.Date(114, 10, 17);
        result.addColumn("ApptDate", new Object[] {date});
        result.addColumn("ApptStartHour", new Object[]{"11"});
        result.addColumn("ApptStartMin", new Object[]{"0"});
        result.addColumn("ApptEndHour", new Object[]{"11"});
        result.addColumn("ApptEndMin", new Object[]{"30"});
        result.addColumn("ApptType", new Object[]{sMajor});
        result.addColumn("Description", new Object[]{dp});
        result.addColumn("StudentID", new Object[]{sID});
        result.addColumn("StudentName", new Object[]{sName});
        result.addColumn("StudentEmail", new Object[]{sEmail});
        result.addColumn("AdvisorName", new Object[]{aName});
        result.addColumn("StudentMajor", new Object[]{sMajor});
        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    
    public ModifyAppointmentBeanTest() {
    }

    @Test
    public void testScheduleAppointmentValidModify()
    {
        prepareResultSet();
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setStudentId(sID);
        instance.setStudentName(sName);
        instance.setAdvisorName(aName);
        instance.setType(type);
        instance.setDate(d);
        instance.setDescription(dp);
        instance.setStartHour(sH);
        instance.setEndHour(eH);
        instance.setStartMinute(sM);
        instance.setEndMinute(eM);
        instance.setApptID(1);
        String result = instance.scheduleAppointment();
        verifySQLStatementExecuted("UPDATE \"APPOINTMENT\"");
        assertEquals("", result);  
    }
    
    @Test
    public void testScheduleAppointmentInvalidModify()
    {
        prepareResultSet();
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setStudentId(wrongID);
        instance.setStudentName(sName);
        instance.setAdvisorName(aName);
        instance.setType(type);
        instance.setDate(d);
        instance.setDescription(dp);
        instance.setStartHour(sH);
        instance.setEndHour(eH);
        instance.setStartMinute(sM);
        instance.setEndMinute(eM);
        instance.setApptID(1);
        String result = instance.scheduleAppointment();
        verifySQLStatementNotExecuted("UPDATE \"APPOINTMENT\"");
        assertEquals(INITIALIZE_APPOINTMENT_FAIL, result);  
    }
    
    @Test
    public void testScheduleAppointmentValidRemove()
    {
        prepareResultSet();
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setRemove(true);
        instance.setApptID(1);
        String result = instance.scheduleAppointment();
        verifySQLStatementExecuted("DELETE FROM \"APPOINTMENT\"");
        assertEquals("", result);  
    }
    
    @Test
    public void testSetRemove()
    {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setRemove(true);
        assertEquals(true, instance.getRemove());
    }
    
    @Test
    public void testSetApptId() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setApptID(1);
        assertEquals(1, instance.getApptID());
    }
    
    @Test
    public void testSetStudentMajor() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setStudentMajor(sMajor);
        assertEquals(sMajor, instance.getStudentMajor());
    }
    /**
     * Test of setStudentName method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStudentName() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setStudentName(sName);
        assertEquals(sName, instance.getStudentName());
    }

    /**
     * Test of setStudentID method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStudentID() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setStudentId(sID);
        assertEquals(sID, instance.getStudentId());
    }

    /**
     * Test of setAdvisorName method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetAdvisorName() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setAdvisorName(aName);
        assertEquals(aName, instance.getAdvisorName());
    }
    
    /**
     * Test of setType method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetType() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setType(type);
        assertEquals(type, instance.getType());
    }
    
    /**
     * Test of setDescription method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetDescription() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setDescription(dp);
        assertEquals(dp, instance.getDescription());
    }

    /**
     * Test of setDate method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetDate() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setDate(d);
        assertEquals(d, instance.getDate());
    }

    /**
     * Test of setStartHour method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStartHour() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setStartHour(sH);
        assertEquals(sH, instance.getStartHour());
    }

    /**
     * Test of setEndHour method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetEndHour() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setEndHour(eH);
        assertEquals(eH, instance.getEndHour());
    }

    /**
     * Test of setStartMinute method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetStartMinute() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setStartMinute(sM);
        assertEquals(sM, instance.getStartMinute());
    }

    /**
     * Test of setEndMinute method, of class ScheduleAppointmentControllerBean.
     */
    @Test
    public void testSetEndMinute() {
        ModifyAppointmentBean instance = new ModifyAppointmentBean();
        instance.setEndMinute(eM);
        assertEquals(eM, instance.getEndMinute());
    }

}