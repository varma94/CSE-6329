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
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.AvailableSlot;
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Frank R.
 */
public class DatabaseManagerTest extends BasicJDBCTestCaseAdapter{
    
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
        
        resultSetHandler.prepareResultSet("SELECT * FROM AVAILSLOT", result);
        
        result = resultSetHandler.createResultSet();
        java.sql.Date date2 = new java.sql.Date(114, 10, 17);
        result.addColumn("ApptID", new Object[] {"1", "2"});
        result.addColumn("ApptDate", new Object[] {date2, date2});
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
        
        resultSetHandler.prepareResultSet("SELECT * FROM APPOINTMENT", result);
        
        result = resultSetHandler.createResultSet();
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
        
        resultSetHandler.prepareResultSet("SELECT * FROM APPOINTMENT", result);
        
        result = resultSetHandler.createResultSet();
        result.addRow(new Object[] {"1"});
        result.addRow(new Object[] {"2"});
        result.addRow(new Object[] {"3"});
        result.addRow(new Object[] {"4"});
        result.addRow(new Object[] {"5"});
        result.addRow(new Object[] {"6"});
        
        
        resultSetHandler.prepareGeneratedKeys("INSERT INTO SLOT", result);
        
        result = resultSetHandler.createResultSet();
        result.addColumn("ApptDate", new Object[] {date});
        result.addColumn("ApptStartHour", new Object[]{"13"});
        result.addColumn("ApptStartMin", new Object[]{"0"});
        result.addColumn("ApptEndHour", new Object[]{"14"});
        result.addColumn("ApptEndMin", new Object[]{"0"});
        result.addColumn("ApptType", new Object[]{"Advising Type"});
        result.addColumn("Description", new Object[]{"This is the test after edit Appointment"});
        result.addColumn("StudentID", new Object[]{"1000654321"});
        result.addColumn("StudentName", new Object[]{"Changed Name"});
        result.addColumn("StudentEmail", new Object[]{"test@test.com"});
        result.addColumn("AdvisorName", new Object[]{"Advisor Name"});
        result.addColumn("StudentMajor", new Object[]{"Software Engineering"});
        
        resultSetHandler.prepareResultSet("UPDATE APPOINTMENT", result);
        
        result = resultSetHandler.createResultSet();
        result.addRow(new String[] {"1", "2014-11-14", "8", "15"});
        result.addRow(new String[] {"2", "2014-11-14", "8", "30"});
        
        resultSetHandler.prepareResultSet("SELECT * FROM SLOT", result);
        
        result = resultSetHandler.createResultSet();
        result.addRow(new String[] {"1", "2014-11-14", "8", "15", "9"});
        result.addRow(new String[] {"2", "2014-11-14", "8", "30", "4"});
        
        resultSetHandler.prepareResultSet("SELECT * FROM APPTSLOT", result);
        
        result = resultSetHandler.createResultSet();
        result.addRow(new String[] {"1", "2014-11-14", "8", "15"});
        result.addRow(new String[] {"2", "2014-11-14", "8", "30"});
        
        resultSetHandler.prepareResultSet("SELECT * FROM AVAILSLOT", result);
        
        result = resultSetHandler.createResultSet();
        result.addColumn("UserName", new Object[] {"admin"});
        result.addColumn("UserEmail", new Object[]{"admin@mavs.uta.edu"});
        result.addColumn("UserDepartment", new Object[]{"CSE"});
        result.addColumn("UserID", new Object[]{"1"});
        result.addColumn("UserRank", new Object[]{"0"});

        
        resultSetHandler.prepareResultSet("SELECT * FROM \"USER\"", result);
        
        result = resultSetHandler.createResultSet();
        result.addColumn("UserName", new Object[] {"admin"});
        result.addColumn("UserEmail", new Object[]{"admin@mavs.uta.edu"});
        result.addColumn("UserDepartment", new Object[]{"CSE"});
        result.addColumn("UserID", new Object[]{"1"});
        result.addColumn("UserRank", new Object[]{"0"});

        
        resultSetHandler.prepareResultSet("SELECT * FROM USER", result);
    }
    
    private void prepareSaveSlotsError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("INSERT INTO SLOT");

    }
    
    @Test 
    public void testIsFree()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        boolean result = dbmgr.isFree(new Date(time), 8, 8, 15, 30);
        verifySQLStatementExecuted("SELECT * FROM AVAILSLOT");
        assertEquals(true, result);
    }
    @Test
    public void testGetAppointments()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        ArrayList<Appointment> appts = dbmgr.getAppointments();
        verifySQLStatementExecuted("SELECT * FROM APPOINTMENT");
        assertEquals(2, appts.size());

    }
    
    @Test
    public void testGetAppointment()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        Appointment appt = dbmgr.getAppointment(1);
        verifySQLStatementExecuted("SELECT * FROM APPOINTMENT");
        assertEquals("Name", appt.getStudentName());
    }
    
    @Test
    public void testSaveSlots()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 15, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 30, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 45, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 15, 0));
        String result = dbmgr.saveSlots(slots);
        verifySQLStatementExecuted("INSERT INTO SLOT");
        assertEquals("", result);
    }
    
    @Test
    public void testSaveSlotsFailure()
    {
        prepareSaveSlotsError();
        DatabaseManager dbmgr = new DatabaseManager();
        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 15, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 30, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 8, 45, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 0, 0));
        slots.add(new AvailableSlot(new Date(System.currentTimeMillis()), 9, 15, 0));
        String result = dbmgr.saveSlots(slots);
        verifySQLStatementNotExecuted("INSERT INTO SLOT");
        assertEquals("failed", result);
    }
    
    @Test
    public void testSaveAppointment()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        String sName = "Changed Name";
        String sID = "1000654321";
        String sEmail = "test@test.com";
        String sMajor = "Software Engineering";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test after edit Appointment";
        Date date = new Date(114, 10, 17);
        int sH = 13;
        int eH = 0;
        int sM = 14;
        int eM = 0;
        Appointment appt = new Appointment();
        appt.initialize(sMajor, sName, sID, sEmail, aName, type, dp, date, sH, eH, sM, eM);
        String result = dbmgr.saveAppointment(appt);
        verifySQLStatementExecuted("INSERT INTO APPOINTMENT");
        assertEquals("", result);
    }
    
    @Test
    public void testModifyAppointmentUpdate()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        String sName = "Changed Name";
        String sID = "1000654321";
        String sEmail = "test@test.com";
        String sMajor = "Software Engineering";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test after edit Appointment";
        Date date = new Date(114, 10, 17);
        int sH = 13;
        int eH = 0;
        int sM = 14;
        int eM = 0;
        Appointment appt = new Appointment();
        appt.initialize(sMajor, sName, sID, sEmail, aName, type, dp, date, sH, eH, sM, eM);
        String result = dbmgr.modifyAppointment(1, appt);
        verifySQLStatementExecuted("UPDATE APPOINTMENT");
        assertEquals("", result);
    }
    
    @Test
    public void testModifyAppointmentDelete()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        Appointment appt = null;
        String result = dbmgr.modifyAppointment(1, appt);
        verifySQLStatementExecuted("DELETE FROM APPOINTMENT");
        assertEquals("", result);
    }
    
    @Test
    public void testModifySlot()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        // FIXME - Does not match method signature!
        String result = dbmgr.modifySlot(new Date(System.currentTimeMillis()), 8, 9, 0, 30, 0);
        verifySQLStatementExecuted("DELETE FROM SLOT");
        assertEquals("", result);
    }
    
    @Test
    public void testGetSlots()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        ArrayList<Slot> pulledSlots = dbmgr.getSlots();
        verifySQLStatementExecuted("SELECT * FROM SLOT");
        
        assertEquals(2, pulledSlots.size());
        
        assertEquals(8, pulledSlots.get(0).getHour());
        assertEquals(15, pulledSlots.get(0).getMinute());
        
        assertEquals(8, pulledSlots.get(1).getHour());
        assertEquals(30, pulledSlots.get(1).getMinute());
    }
    
    @Test
    public void testGetTypeSlots()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        ArrayList<Slot> slots = dbmgr.getTypeSlots();
        verifySQLStatementExecuted("SELECT * FROM APPTSLOT");
        verifySQLStatementExecuted("SELECT * FROM AVAILSLOT");
        
        assertEquals(4, slots.size());
        
        assertEquals(8, slots.get(0).getHour());
        assertEquals(15, slots.get(0).getMinute());
        assertFalse(slots.get(0).isAppointment());
        
        assertEquals(8, slots.get(1).getHour());
        assertEquals(15, slots.get(1).getMinute());
        assertTrue(slots.get(1).isAppointment());
        assertEquals(9, slots.get(1).getAppointmentId());
        
        assertEquals(8, slots.get(2).getHour());
        assertEquals(30, slots.get(2).getMinute());
        assertFalse(slots.get(2).isAppointment());
        
        assertEquals(8, slots.get(3).getHour());
        assertEquals(30, slots.get(3).getMinute());
        assertTrue(slots.get(3).isAppointment());
        assertEquals(4, slots.get(3).getAppointmentId());
    }
    
    @Test
    public void testGetAvailableSlots()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        ArrayList<Slot> pulledSlots = dbmgr.getAvailableSlots();
        verifySQLStatementExecuted("SELECT * FROM AVAILSLOT");
        
        assertEquals(2, pulledSlots.size());
        
        assertEquals(8, pulledSlots.get(0).getHour());
        assertEquals(15, pulledSlots.get(0).getMinute());
        
        assertEquals(8, pulledSlots.get(1).getHour());
        assertEquals(30, pulledSlots.get(1).getMinute());
    }
    
    @Test
    public void testRegister()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        AdvisorAccount aa = new AdvisorAccount();
        aa.initialize("admin", "admin@mavs.uta.edu", "CSE", "password", 0);
        String result = dbmgr.register(aa);
        verifySQLStatementExecuted("INSERT INTO USER");
        assertEquals("", result);
    }
    
    @Test
    public void testValidate()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        String result = dbmgr.validate("admin@mavs.uta.edu", "temp");
        verifySQLStatementExecuted("SELECT * FROM USER");
        assertEquals("10", result);
    }
    
    @Test
    public void testGetAccount()
    {
        prepareResultSet();
        DatabaseManager dbmgr = new DatabaseManager();
        AdvisorAccount acct = dbmgr.getAccount("admin@mavs.uta.edu");
        verifySQLStatementExecuted("SELECT * FROM USER");
        assertEquals("admin", acct.getName());
    }
}
