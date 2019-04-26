package es.deusto.server.jdo;

import static org.junit.Assert.assertNotEquals;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the data of the app.
 */
public class DataTest extends TestCase {
	private Article art;
	private Article art2;
	private Article art3;

	private Admin admin;
	private Admin admin2;
	private Admin admin3;

	private User user;
	private User user2;
	private User user3;

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public DataTest(String testName) {
		super(testName);
		
		admin = new Admin("admin1", "password", "email");
		admin2 = new Admin("admin2", "password", "email");
		admin3 = new Admin("admin1", "password", "email");

		user = new User("user1", "password", "email");
		user2 = new User("user2", "password", "email");
		user3 = new User("user1", "password", "email");

		art = new Article("Article1", "body", "category", admin);
		art2 = new Article("Article2", "body", "category", 123, admin2);
		art3 = new Article("Article1", "body", "category", admin3);

	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DataTest.class);
	}

	/**
	 * Test of the data
	 */
	public void testApp() {
		// Articles Test
		assertTrue(art.equals(art3));
		assertFalse(art.equals(art2));

		assertEquals(art.getTitle(), "Article1");
		assertEquals(art.getBody(), "body");
		assertEquals(art.getCategory(), "category");
		assertEquals(art.getVisits(), 0);

		art.setTitle("title1");
		art.setBody("hello");
		art.setCategory("Sport");
		art.setVisits(13);
		assertEquals(art.getTitle(), "title1");
		assertEquals(art.getBody(), "hello");
		assertEquals(art.getCategory(), "Sport");
		assertEquals(art.getVisits(), 13);
		assertNotEquals(art.hashCode(), art2.hashCode());
		assertEquals(art.toString(), "title1::hello");

		// Admins test
		assertTrue(admin.equals(admin3));
		assertFalse(admin.equals(admin2));

		assertEquals(admin.getLogin(), "admin1");
		assertEquals(admin.getPassword(), "password");
		assertEquals(admin.getEmail(), "email");
		assertEquals(admin.getOwnArticles().size(), 0);
		admin.addArticle(art);
		assertEquals(admin.getOwnArticles().size(), 1);
		assertEquals(admin.getOwnArticles().get(0).getTitle(), "title1");
		admin.deleteArticle(art);
		assertEquals(admin.getOwnArticles().size(), 0);

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
