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
public class GetAdvisorTest extends BasicJDBCTestCaseAdapter{
    
    public GetAdvisorTest() {
    }
    
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
    
    private void prepareError(){

        MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
        PreparedStatementResultSetHandler resultSetHandler = connection.getPreparedStatementResultSetHandler();

        resultSetHandler.prepareThrowsSQLException("SELECT * FROM USER");

    }

    /**
     * Test of queryDB method, of class GetAdvisor.
     */
    @Test
    public void testQueryDB() throws Exception {
        prepareResultSet();
        GetAdvisor instance = new GetAdvisor("admin@uta.edu");
        instance.execute();
        verifySQLStatementExecuted("SELECT * FROM USER");
        assertNotNull(instance.getResult());
        AdvisorAccount acct = (AdvisorAccount) instance.getResult();
        assertEquals("admin", acct.getName());
    }
    
    @Test
    public void testSQLError() throws Exception {
        prepareError();
        GetAdvisor instance = new GetAdvisor("admin@uta.edu");
        instance.execute();
        verifySQLStatementNotExecuted("SELECT * FROM USER");
    }

    
}
