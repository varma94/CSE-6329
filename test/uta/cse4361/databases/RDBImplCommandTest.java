/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import java.sql.SQLException;
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
public class RDBImplCommandTest extends BasicJDBCTestCaseAdapter{
    
    public RDBImplCommandTest() {
    }

    /**
     * Test of getResult method, of class RDBImplCommand.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        RDBImplCommand instance = new RDBImplCommandImpl();
        Object expResult = null;
        Object result = instance.getResult();
        assertEquals(expResult, result);
    }


    /**
     * Test of processResult method, of class RDBImplCommand.
     */
    @Test(expected=SQLException.class)
    public void testDisconnectError() throws Exception{
        System.out.println("processResult");
        RDBImplCommand instance = new RDBImplCommandImpl();
        instance.connectDB();
        instance.conn.close();
        instance.disconnectDB();
    }

    public class RDBImplCommandImpl extends RDBImplCommand {

        public void queryDB() throws SQLException {
        }

        public void processResult() {
        }
    }

}
