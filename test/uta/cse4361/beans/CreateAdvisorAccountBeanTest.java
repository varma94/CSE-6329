/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse4361.businessobjects.AdvisorAccount;

/**
 *
 * @author Frank R.
 */
public class CreateAdvisorAccountBeanTest extends BasicJDBCTestCaseAdapter implements uta.cse4361.interfaces.Constants{
    
    public CreateAdvisorAccountBeanTest() {
    }

    @Test
    public void testAdvisorValid()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setName("Admin");
        instance.setEmail("admin@mavs.uta.edu");
        instance.setDepartment("CSE");
        instance.setRank(0);
        instance.setTempPassword("temp");
        instance.setID(1);
        
        String result = instance.Advisor();
        verifySQLStatementExecuted("INSERT INTO \"USER\"");
        
        assertEquals(SUCCESS_MESSAGE, result);
    }
    
    @Test
    public void testAdvisorInvalid()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setName("");
        instance.setEmail("admin@mavs.uta.edu");
        instance.setDepartment("CSE");
        instance.setRank(0);
        instance.setTempPassword("temp");
        instance.setID(1);
        
        String result = instance.Advisor();
        verifySQLStatementNotExecuted("INSERT INTO \"USER\"");
        
        assertEquals(CREATE_ADVISOR_FAIL, result);
    }
    
    @Test
    public void testSetName()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setName("admin");
        assertEquals("admin", instance.getName());
    }
    
    @Test
    public void testSetEmail()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setEmail("admin@mavs.uta.edu");
        assertEquals("admin@mavs.uta.edu", instance.getEmail());
    }
    
    @Test
    public void testSetDepartment()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setDepartment("CSE");
        assertEquals("CSE", instance.getDepartment());
    }
    
    @Test
    public void testSetRank()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setRank(0);
        assertEquals(0, instance.getRank());
    }
    
    @Test
    public void testSetPassword()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setTempPassword("temp");
        assertEquals("temp", instance.getTempPassword());
    }
    
    @Test
    public void testSetID()
    {
        CreateAdvisorAccountBean instance = new CreateAdvisorAccountBean();
        instance.setID(1);
        assertEquals(1, instance.getID());
    }
}
