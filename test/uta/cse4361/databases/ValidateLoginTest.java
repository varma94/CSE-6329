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

/**
 *
 * @author Andrew
 */
public class ValidateLoginTest extends BasicJDBCTestCaseAdapter{
    
    public ValidateLoginTest() {
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
     * Test of queryDB method, of class ValidateLogin.
     */
    @Test
    public void testQueryDB() throws Exception {
        prepareResultSet();
        System.out.println("queryDB");
        ValidateLogin instance = new ValidateLogin("admin@mavs.uta.edu", "temp");
        instance.execute();
        verifySQLStatementExecuted("SELECT * FROM USER");
        String result = (String) instance.getResult();
        
        assertEquals("10", result);
    }

    @Test
    public void testSQLError() throws Exception {
        prepareError();
        System.out.println("queryDB");
        ValidateLogin instance = new ValidateLogin("admin@mavs.uta.edu", "temp");
        instance.execute();
        verifySQLStatementNotExecuted("SELECT * FROM USER");
    }

    
}
