/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import com.mockrunner.jdbc.PreparedStatementResultSetHandler;
import com.mockrunner.mock.jdbc.MockConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse4361.businessobjects.AdvisorAccount;

/**
 *
 * @author Andrew
 */
public class RegisterAdvisorTest extends BasicJDBCTestCaseAdapter{
    
    public RegisterAdvisorTest() {
    }
    
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("INSERT INTO USER");

    }

    /**
     * Test of queryDB method, of class RegisterAdvisor.
     */
    @Test
    public void testQueryDB() throws Exception {
        System.out.println("queryDB");
        AdvisorAccount aa = new AdvisorAccount();
        aa.initialize("admin", "admin@mavs.uta.edu", "CSE", "password", 0);
        RegisterAdvisor instance = new RegisterAdvisor(aa);
        instance.execute();
        // TODO review the generated test code and remove the default call to fail.
        verifySQLStatementExecuted("INSERT INTO USER");
        assertNotNull(instance.getResult());
        assertEquals("", instance.getResult());
    }
    
    @Test
    public void testError() throws Exception {
        prepareError();
        System.out.println("queryDB");
        AdvisorAccount aa = new AdvisorAccount();
        aa.initialize("admin", "admin@mavs.uta.edu", "CSE", "password", 0);
        RegisterAdvisor instance = new RegisterAdvisor(aa);
        instance.execute();
        // TODO review the generated test code and remove the default call to fail.
        verifySQLStatementNotExecuted("INSERT INTO USER");
    }
    
}
