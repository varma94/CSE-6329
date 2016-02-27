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
import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.interfaces.Constants;

/**
 *
 * @author Frank R.
 */
public class LogInBeanTest extends BasicJDBCTestCaseAdapter implements Constants{
    
    private void prepareResultSet(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        MockResultSet result = resultSetHandler.createResultSet();
        result.addColumn("UserName", new Object[] {"admin"});
        result.addColumn("UserEmail", new Object[]{"admin@mavs.uta.edu"});
        result.addColumn("UserDepartment", new Object[]{"CSE"});
        result.addColumn("UserID", new Object[]{"1"});
        result.addColumn("UserRank", new Object[]{"0"});

        
        resultSetHandler.prepareGlobalResultSet(result);
    }
    
    public LogInBeanTest() {
    }
    
    public void testLogIn()
    {
        prepareResultSet();
        LogInBean instance = new LogInBean();
        instance.setEmail("admin@mavs.uta.edu");
        instance.setPassword("temp");
        String result = instance.LogIn();
        verifySQLStatementExecuted("SELECT * FROM \"USER\"");
        
        assertEquals("10", result);
    }
    
    public void testSetEmail()
    {
        LogInBean instance = new LogInBean();
        instance.setEmail("admin@mavs.uta.edu");
        assertEquals("admin@mavs.uta.edu", instance.getEmail());
    }
    
    public void testSetPassword()
    {
        LogInBean instance = new LogInBean();
        instance.setPassword("temp");
        assertEquals(AdvisorAccount.hashPassword("temp"), instance.getPassword());
    }

}
