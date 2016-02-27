/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

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
public class AppointmentTest {
    
    public AppointmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class Appointment.
     * If provided a correct student ID, the initializer should correctly initialize
     */
    @Test
    public void testInitializeSuccess() {
        String sMajor = "Software Engineering";
        String sName = "First Last";
        String sID = "1000123456";
        String sEmail = "hzhang@mavs.uta.edu";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test ";
        Date date = new Date();
        int sH = 13;
        int eH = 0;
        int sM = 14;
        int eM = 0;
        Appointment instance = new Appointment();
        boolean expResult = true;
        boolean result = instance.initialize(sMajor, sName, sID, sEmail, aName, type, dp, date, sH, eH, sM, eM);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of initialize method, of class Appointment.
     * With a wrong given student ID, the initializer should return fail
     */
    @Test
    public void testInitializeFail() {
        String sMajor = "Software Engineering";
        String sName = "First Last";
        String sID = "3000123456";
        String sEmail = "hzhang@mavs.uta.edu";
        String aName = "Advisor Name";
        String type = "Advising Type";
        String dp = "This is the test ";
        Date date = new Date();
        int sH = 13;
        int eH = 0;
        int sM = 14;
        int eM = 0;
        Appointment instance = new Appointment();
        boolean expResult = false;
        boolean result = instance.initialize(sMajor, sName, sID,sEmail, aName, type, dp, date, sH, eH, sM, eM);
        assertEquals(expResult, result);
    }
    @Test
    public void testSetStudentMajor() {
        String sMajor = "Software Engineering";
        Appointment instance = new Appointment();
        instance.setStudentMajor(sMajor);
        assertEquals(sMajor, instance.getStudentMajor());
    }
    
    /**
     * Test of setApptID method, of class Appointment.
     */
    @Test
    public void testSetApptID() {
        int apptID = 1000111234;
        Appointment instance = new Appointment();
        instance.setApptID(apptID);
        assertEquals(apptID, instance.getApptID());
    }

    @Test
    public void testSetStudentEmail() {
        String sEmail = "test@test.com";
        Appointment instance = new Appointment();
        instance.setStudentEmail(sEmail);
        assertEquals(sEmail, instance.getStudentEmail());
    }
    /**
     * Test of setStudentName method, of class Appointment.
     */
    @Test
    public void testSetStudentName() {
        String sName = "First Last";
        Appointment instance = new Appointment();
        instance.setStudentName(sName);
        assertEquals(sName, instance.getStudentName());
    }

    /**
     * Test of setStudentID method, of class Appointment.
     */
    @Test
    public void testSetStudentIDWithProperID() {
        String sID = "6000112345";
        Appointment instance = new Appointment();
        boolean expResult = true;
        boolean result = instance.setStudentID(sID);
        assertEquals(expResult, result);
    }

     /**
     * Test of setStudentID method, of class Appointment.
     */
    @Test
    public void testSetStudentIDWithLengthIssue() {
        String sID = "10000112345";
        Appointment instance = new Appointment();
        boolean expResult = false;
        boolean result = instance.setStudentID(sID);
        assertEquals(expResult, result);
    }
     /**
     * Test of setStudentID method, of class Appointment.
     */
    @Test
    public void testSetStudentIDWithFirstIntIssue() {
        String sID = "200012345";
        Appointment instance = new Appointment();
        boolean expResult = false;
        boolean result = instance.setStudentID(sID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setAdvisorName method, of class Appointment.
     */
    @Test
    public void testSetAdvisorName() {
        String aName = "Advisor Name";
        Appointment instance = new Appointment();
        instance.setAdvisorName(aName);
        assertEquals(aName, instance.getAdvisorName());
    }

    /**
     * Test of setDescription method, of class Appointment.
     */
    @Test
    public void testSetDescription() {
        String dp = "This is an description example for testing";
        Appointment instance = new Appointment();
        instance.setDescription(dp);
        assertEquals(dp, instance.getDescription());
    }

    /**
     * Test of setDate method, of class Appointment.
     */
    @Test
    public void testSetDate() {
        Date d = new Date();
        Appointment instance = new Appointment();
        instance.setDate(d);
        assertEquals(d, instance.getDate());
    }

    /**
     * Test of setStartHour method, of class Appointment.
     */
    @Test
    public void testSetStartHour() {
        int sH = 12;
        Appointment instance = new Appointment();
        instance.setStartHour(sH);
        assertEquals(sH, instance.getStartHour());
    }

    /**
     * Test of setEndHour method, of class Appointment.
     */
    @Test
    public void testSetEndHour() {
        int eH = 14;
        Appointment instance = new Appointment();
        instance.setEndHour(eH);
        assertEquals(eH, instance.getEndHour());
    }

    /**
     * Test of setStartMinute method, of class Appointment.
     */
    @Test
    public void testSetStartMinute() {
        int sM = 34;
        Appointment instance = new Appointment();
        instance.setStartMinute(sM);
        assertEquals(sM, instance.getStartMinute());
    }

    /**
     * Test of setEndMinute method, of class Appointment.
     */
    @Test
    public void testSetEndMinute() {
        int eM = 44;
        Appointment instance = new Appointment();
        instance.setEndMinute(eM);
        assertEquals(44, instance.getEndMinute());
    }

//    /**
//     * Test of getApptID method, of class Appointment.
//     */
//    @Test
//    public void testGetApptID() {
//        Appointment instance = new Appointment();
//        int expResult = 0;
//        int result = instance.getApptID();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getStudentName method, of class Appointment.
//     */
//    @Test
//    public void testGetStudentName() {
//        Appointment instance = new Appointment();
//        String expResult = "";
//        String result = instance.getStudentName();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getStudentID method, of class Appointment.
//     */
//    @Test
//    public void testGetStudentID() {
//        Appointment instance = new Appointment();
//        String expResult = "";
//        String result = instance.getStudentID();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAdvisorName method, of class Appointment.
//     */
//    @Test
//    public void testGetAdvisorName() {
//        Appointment instance = new Appointment();
//        String expResult = "";
//        String result = instance.getAdvisorName();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getDescription method, of class Appointment.
//     */
//    @Test
//    public void testGetDescription() {
//        Appointment instance = new Appointment();
//        String expResult = "";
//        String result = instance.getDescription();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getStartHour method, of class Appointment.
//     */
//    @Test
//    public void testGetStartHour() {
//        Appointment instance = new Appointment();
//        int expResult = 0;
//        int result = instance.getStartHour();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getEndHour method, of class Appointment.
//     */
//    @Test
//    public void testGetEndHour() {
//        Appointment instance = new Appointment();
//        int expResult = 0;
//        int result = instance.getEndHour();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getStartMinute method, of class Appointment.
//     */
//    @Test
//    public void testGetStartMinute() {
//        Appointment instance = new Appointment();
//        int expResult = 0;
//        int result = instance.getStartMinute();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getEndMinute method, of class Appointment.
//     */
//    @Test
//    public void testGetEndMinute() {
//        Appointment instance = new Appointment();
//        int expResult = 0;
//        int result = instance.getEndMinute();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getDate method, of class Appointment.
//     */
//    @Test
//    public void testGetDate() {
//        Appointment instance = new Appointment();
//        Date expResult = null;
//        Date result = instance.getDate();
//        assertEquals(expResult, result);
//    }
    
}
