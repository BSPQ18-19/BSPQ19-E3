package es.deusto.server.jdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ArticleTest{
	private Article art;
	private Article art2;
	private Article art3;

	private Admin admin;
	private Admin admin2;
	private Admin admin3;

	/**
	 * @return the suite of tests being tested
	 */
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ArticleTest.class);
	}
	
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 * @return 
	 */
	@Before
	public void setUp() {
		admin = new Admin("admin1", "password", "email");
		admin2 = new Admin("admin2", "password", "email");
		admin3 = new Admin("admin1", "password", "email");

		art = new Article("Article1", "body", "category", admin);
		art2 = new Article("Article2", "body", "category", 123, admin2);
		art3 = new Article("Article1", "body", "category", admin3);

	}

	/**
	 * Test of the data
	 */
	@Test
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
		assertEquals(art.getAdmin().username, admin.username);
		art.setAdmin(admin2);
		assertEquals(art.getAdmin().username, admin2.username);
		assertTrue(art.hashCode() != art2.hashCode());
		assertEquals(art.toString(), "title1" + "\n" + "hello" + "\n" + "Category: " + "Sport" + " Visits: " + 13);
	}
}
