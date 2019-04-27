package es.deusto.server.jdo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
public class UserTest{

	private User user;
	private User user2;
	private User user3;

	
	/**
	 * @return the suite of tests being tested
	 */
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(UserTest.class);
	}
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	@Before
	public void UserTest() {
		user = new User("user1", "password", "email");
		user2 = new User("user2", "password", "email");
		user3 = new User("user1", "password", "email");
	}

	

	/**
	 * Test of the data
	 */
	@org.junit.Test
	public void testApp() {
		// User test
		assertTrue(user.equals(user3));
		assertFalse(user.equals(user2));

		assertEquals(user.getLogin(), "user1");
		assertEquals(user.getPassword(), "password");
		assertEquals(user.getEmail(), "email");
		assertEquals(user.hashCode(), user3.hashCode());
		assertEquals(user.toString(), "user1::password");
		user.setUsername("username");
		user.setPassword("pass");
		assertEquals(user.getLogin(), "username");
		assertEquals(user.getPassword(), "pass");

	}
}
