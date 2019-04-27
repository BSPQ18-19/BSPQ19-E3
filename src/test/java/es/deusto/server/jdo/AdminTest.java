package es.deusto.server.jdo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AdminTest{
	private Article art;
	private Article art2;
	private Article art3;

	private Admin admin;
	private Admin admin2;
	private Admin admin3;
	ArrayList<Article> articles;

	/**
	 * @return the suite of tests being tested
	 */
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(AdminTest.class);
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
		admin3 = new Admin("admin1", "password", "email");

		art = new Article("title1", "body", "category", admin);
		art2 = new Article("title2", "body", "category", admin2);
		art3 = new Article("title3", "body", "category", admin2);
		articles = new ArrayList<Article>();
		articles.add(art2);
		articles.add(art3);
		admin2 = new Admin("admin2", "password", "email", articles);
	}

	
	

	/**
	 * Test of the data
	 */
	@org.junit.Test
	public void testApp() {
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
		admin.setOwnArticles(articles);
	}
}
