package es.deusto.server;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.rmi.RemoteException;

import es.deusto.server.*;
import es.deusto.server.jdo.User;
/**
 * Unit test for the server part.
 */
public class ServerTest
    extends TestCase
{
	private Server server;
    private User user;
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ServerTest( String testName ){
        super( testName );
        user = new User("usernameTest2", "password", "email@gmail.com");
        try {
			server = new Server();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServerTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws RemoteException 
     */
    public void testApp() throws RemoteException
    {
	assertTrue(server.registerUser("usernameTest2", "password", "email@gmail.com"));
	assertTrue(server.logIn("usernameTest2", "password").equals(user));
    assertTrue(server.logIn("usernameFalse", "password") == null);
    
    
    }
}
