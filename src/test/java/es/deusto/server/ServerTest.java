package es.deusto.server;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import es.deusto.server.*;
import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Unit test for the server part.
 */

public class ServerTest {
	private Server server;
	private User user;
	private Admin admin;

	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	/**
	 * @return the suite of tests being tested
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ServerTest.class);
	}

	@Before
	public void setUp() {
		user = new User("usernameTest2", "password", "email@gmail.com");
		admin = new Admin("FDR", "FDR", "alberto@gmail.com");

		try {
			server = new Server();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@org.junit.Test
	@PerfTest(invocations = 1000, threads = 10)
	@Required(max = 1000)
	public void testLogInUser() throws RemoteException, InterruptedException {
		assertTrue(server.logIn("usernameTest2", "password").username.equals(user.username));
		assertTrue((server.logIn("usernameFalse", "password") == null));
	}

	@org.junit.Test
	@PerfTest(invocations = 1000, threads = 10)
	@Required(max = 1000, average = 60)
	public void testLogInAdmin() throws RemoteException {
		assertTrue(admin.equals(server.logInAdmin(admin.username, admin.password)));
		assertTrue(server.logInAdmin("NOEXIST", "NOEXIST") == null);
	}

	@org.junit.Test
	@PerfTest(invocations = 1000, threads = 10)
	@Required(max = 1000, average = 20)
	public void testRegister() throws RemoteException, InterruptedException {
		assertTrue(server.registerUser("usernameTest2", "password", "email@gmail.com"));
	}

	@org.junit.Test
	@PerfTest(invocations = 100, threads = 10)
	@Required(max = 1000, average = 300)
	public void testArticlesManagement() throws RemoteException, InterruptedException {

		assertTrue(server.readArticle("NOEXIST") == null);
		assertEquals("Title1", server.readArticle("Title1").getTitle());

		assertFalse(server.createArticle(new Article("Title1", "body", "category", admin), admin));
		assertFalse(server.deleteArticle(new Article("NOEXIST", "body", "category", admin), admin));

		assertEquals("Title1", server.searchArticleTitle("Title1").getTitle());
		assertEquals(3, server.searchArticleAuthor("Raul").size());

		ArrayList<Article> top = server.viewTopArticle();
		assertEquals(5, top.size());
		for (int i = 1; i < top.size() - 1; i++) {
			assertTrue(top.get(i - 1).getVisits() == top.get(i).getVisits()
					|| top.get(i - 1).getVisits() > top.get(i).getVisits());
		}

		assertTrue(server.getFirstArticles().size() != 0);
		server.searchArticleCategory("Sport");
		server.editArticle(new Article("Title1", "body", "category", admin), "Title1", "body", admin);
	}

	@org.junit.Test
	@PerfTest(invocations = 1000, threads = 10)
	@Required(max = 1000, average = 80)
	public void getFirstArticles() throws RemoteException, InterruptedException {
		server.getFirstArticles();
	}

}
