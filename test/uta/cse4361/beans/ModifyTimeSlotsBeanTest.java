/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frank R.
 */
public class ModifyTimeSlotsBeanTest extends BasicJDBCTestCaseAdapter implements uta.cse4361.interfaces.Constants{
    
    public ModifyTimeSlotsBeanTest() {
    }

    @Test
    public void testModifySlot()
    {
    	// FIXME - Does not match method signature!
        ModifyTimeSlotsBean instance = new ModifyTimeSlotsBean(8, 9, 0, 30, new Date(System.currentTimeMillis()));
        String result = instance.modifySlot();
        verifySQLStatementExecuted("DELETE FROM \"SLOT\"");
        assertEquals(SUCCESS_MESSAGE, result);
    }
    /**
     * Test of setDate method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetDate() {
        Date d = new Date();
        ModifyTimeSlotsBean instance = new ModifyTimeSlotsBean();
        instance.setDate(d);
        assertEquals(d, instance.getDate());
    }

    /**
     * Test of setStartHour method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetStartHour() {
        int sH = 11;
        ModifyTimeSlotsBean instance = new ModifyTimeSlotsBean();
        instance.setStartHr(sH);
        assertEquals(sH, instance.getStartHr());
    }

    /**
     * Test of setEndHour method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetEndHour() {
        int eH = 12;
        ModifyTimeSlotsBean instance = new ModifyTimeSlotsBean();
        instance.setEndHr(eH);
        assertEquals(eH, instance.getEndHr());
    }

    /**
     * Test of setStartMinute method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetStartMinute() {
        int sM = 30;
        ModifyTimeSlotsBean instance = new ModifyTimeSlotsBean();
        instance.setStartMin(sM);
        assertEquals(sM, instance.getStartMin());
    }

    /**
     * Test of setEndMinute method, of class TimeAllocationSlotBean.
     */
    @Test
    public void testSetEndMinute() {
        int eM = 26;
        ModifyTimeSlotsBean instance = new ModifyTimeSlotsBean();
        instance.setEndMin(eM);
        assertEquals(eM, instance.getEndMin());
    }
}
