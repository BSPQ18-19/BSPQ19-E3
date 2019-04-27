package es.deusto.server;

import java.rmi.Naming;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private PersistenceManager pm = null;
	private Transaction tx = null;
	// private ArrayList<Admin> admins = new ArrayList<Admin>();
	static Logger logger = Logger.getLogger(Server.class.getName());

	/**
	 * The constructor of the class (Persistence manager, transaction...)
	 * 
	 * @throws RemoteException
	 */
	public Server() throws RemoteException {
		super();
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	/**
	 * To finalize the transaction
	 */
	protected void finalize() throws Throwable {
		if (tx.isActive()) {
			tx.rollback();
		}
		// pm.close();
	}

	/**
	 * Register a user in the DB
	 * 
	 * @param login    The username of the person
	 * @param password The password of the person
	 * @param email    The email of the person
	 * @return Returns a Boolean, if the transaction works well returns true
	 */
	public Boolean registerUser(String login, String password, String email) throws RemoteException {
		logger.info("Register for the user: " + login);
		tx.begin();
		User user = null;

		try {
			user = pm.getObjectById(User.class, login);
			// If the user exists:
			logger.info("Trying to create a user that already exists");
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
			// The user doesn't exist
			user = new User(login, password, email);
			pm.makePersistent(user);
			logger.info("User created successfully");
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
		System.out.println("");
		return true;
	}

	/**
	 * To log in a USER
	 * 
	 * @param user The username of the person
	 * @param pass the p logger.info("---The article does not exist in the db, so it
	 *             cannot be edited---"); assword of that person
	 * @return Returns the user of that person
	 */
	public User logIn(String user, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		logger.info("LogIn for the user: " + user);
		User user1 = null;
		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + user + "'"
					+ " && password == '" + pass + "'");
			query.setUnique(true);
			user1 = (User) query.execute();
			tx.commit();
		} catch (Exception e) {
			logger.info("Exception thrown during retrieval of Extent : " + e.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		if (user1 != null) {
			logger.info("LogIn successfull");
		} else {
			logger.info("LogIn wrong");
		}
		return user1;
	}

	/**
	 * To log in a ADMIN
	 * 
	 * @param user The username of the person
	 * @param pass the password of that person
	 * @return Returns the admin of that person
	 */
	public Admin logInAdmin(String user, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		logger.info("LogInAdmin for the user: " + user);
		Admin user1 = null;
		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Admin.class.getName() + " WHERE username == '" + user + "'"
					+ " && password == '" + pass + "'");
			query.setUnique(true);
			user1 = (Admin) query.execute();
			tx.commit();
		} catch (Exception e) {
			logger.info("Exception thrown during retrieval of Extent : " + e.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		if (user1 == null) {
			logger.info("---The admin does not exist ---");
		} else {
			logger.info("LogInAdmin successfull");
		}
		return user1;
	}

	/**
	 * 
	 */
	public Article readArticle(String Title) throws RemoteException {
		// TODO Auto-generated method stub
		Article art = null;
		try {
			tx.begin();
			logger.info("Reading the article: " + Title);
			art = pm.getObjectById(Article.class, Title);
			tx.commit();
		} catch (Exception e) {
			logger.error("Error reading the article");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return art;
	}

	/**
	 * To create an article
	 * 
	 * @param Article the article to create
	 * @param Admin   the admin that is creating the article
	 * @return boolean true(created) false(not created)
	 */
	public Boolean createArticle(Article art, Admin autho) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			tx.begin();
			logger.info("Checking whether the article title is already in use: " + art);
			try {
				Article artDB = pm.getObjectById(Article.class, art.title);
				logger.info("The title: " + art.title + " it's already in use, the transaction wasn't successful");
				tx.commit();
				return false;
			} catch (Exception e) {
				logger.info("Creating a new article with title: " + art.title);
				logger.info("Body: " + art.body);
				logger.info("Number of visits: " + art.visits);
				logger.info("Categorized as: " + art.category);
				autho = pm.getObjectById(Admin.class, autho.username);
				pm.deletePersistent(autho);
				// autho.setPassword("FDR");
				// autho.setUsername("FDR1");
				logger.info(autho.toString());
				logger.info("New article with title: " + art.title + " created successfully");
				tx.commit();
				autho.setUsername("FDR1");
				tx.begin();
				pm.makePersistent(autho);
				tx.commit();

			}
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		try {
			tx.begin();
			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/**
	 * To edit an article
	 * 
	 * @param        Article, the one to edit that must be searched in db
	 * @param String newTitle, the new title that must be given to the article
	 * @param        boolean changeTitle, whether the title must be changed or not
	 * @param String newBody, the new body that must be given to the article
	 * @param        boolean changeBody, whether the body must be changed or not
	 * @param        Admin, the one that is editing the article
	 * @return boolean true(edited) false(not edited)
	 * 
	 */
	public Boolean editArticle(Article art, String newTitle, String newBody, 
			Admin autho) throws RemoteException {
		// It's made so you can only change the articles title and body, we can make it
		// more complex later.
		/*
		try {
			tx.begin();
			logger.info("Searching for the article to edit in the db...");
			try {
				Article artDB = pm.getObjectById(Article.class, art.title);

				if (changeTitle == true) {
					logger.info("Changing the title...");
					artDB.setTitle(newTitle);
				}
				if (changeBody == true) {
					logger.info("Changing the body...");
					artDB.setBody(newBody);
				}
				// Deleting the old article in the db
				logger.info("Deleting old article in the db...");
				deleteArticle(art, autho);
				// Save the new article in the db
				logger.info("Saving edited article in the db...");
				autho = pm.getObjectById(Admin.class, autho.username);
				autho.addArticle(artDB);

				return true;

			} catch (Exception e) {
				logger.info("---The article does not exist in the db, so it cannot be edited---");
			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}*/
		return false;
	}

	/**
	 * To delete an existing article
	 * 
	 * @param Article, the one to be deleted that must be searched in the db
	 * @param Admin, the one that is deleting the article
	 * @return boolean true(deleted) false(not deleted)
	 */
	public Boolean deleteArticle(Article art, Admin autho) throws RemoteException {
		logger.info("Deleting the article: " + art);
		Boolean delete = false;
		try {
			tx.begin();
			try {
				Article artDB = pm.getObjectById(Article.class, art.title);
				autho = pm.getObjectById(Admin.class, autho.username);
				autho.deleteArticle(artDB);
				pm.deletePersistent(artDB);
				delete = true;
			} catch (Exception e) {
				logger.info("The article doesn't exist");
				delete = false;
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return delete;
	}

	/**
	 * 
	 */
	public Article searchArticleTitle(String title) throws RemoteException {
		Article artDB = null;
		logger.info("SearchArticleTitle: " + title);
		try {
			tx.begin();
			artDB = pm.getObjectById(Article.class, title);
			tx.commit();
		} catch (Exception e) {
			logger.info("SearchArticleTitle: There is no a article with that title");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return artDB;

	}

	/**
	 * 
	 */
	public ArrayList<Article> searchArticleCategory(String category) throws RemoteException {
		ArrayList<Article> arts = null;
		/*try {
			tx.begin();
			logger.info("SELECT FROM " + Article.class + " WHERE category == '" + category + "'");
			Query q = pm.newQuery("SELECT FROM " + Article.class + " WHERE category == '" + category + "'");
			arts = (ArrayList<Article>) q.execute();
			logger.info("Articles: " + arts);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		System.out.println(arts.size());*/
		return arts;
	}

	/**
	 * 
	 */
	public ArrayList<Article> searchArticleAuthor(String author) throws RemoteException {
		ArrayList<Article> ownArticles = new ArrayList<Article>();
		ArrayList<Article> artDB = new ArrayList<Article>();
		try {
			tx.begin();
			Extent<Article> e = pm.getExtent(Article.class, true);
			Iterator<Article> iter = e.iterator();
			while (iter.hasNext()) {
				artDB.add((Article) iter.next());
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		for (int i = 0; i < artDB.size(); i++) {
			if(artDB.get(i).getAdmin().getLogin().equals(author)) {
				ownArticles.add(artDB.get(i));
			}
		}
		return ownArticles;
	}

	/**
	 * 
	 */
	public ArrayList<Article> viewTopArticle() throws RemoteException {
		ArrayList<Article> artDB = new ArrayList<Article>();
		ArrayList<Integer> visits = new ArrayList<Integer>();
		try {
			tx.begin();
			Extent<Article> e = pm.getExtent(Article.class, true);
			Iterator<Article> iter = e.iterator();
			while (iter.hasNext()) {
				artDB.add((Article) iter.next());
			}
			tx.commit();
		} catch (Exception e) {
			logger.info("getFirstArticle: There is no first articles " + e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		for (int i = 0; i < artDB.size(); i++) {
			if (!visits.contains(artDB.get(i).getVisits())) {
				visits.add(artDB.get(i).getVisits());
			}
		}
		// Sort by visits
		Collections.sort(visits, Collections.reverseOrder());

		ArrayList<Article> artTop = new ArrayList<Article>();
		for (int n = 0; n < visits.size() && artTop.size() < 5; n++) {
			for (int m = 0; m < artDB.size() && artTop.size() < 5; m++) {
				if (artDB.get(m).getVisits() == visits.get(n)) {
					artTop.add(artDB.get(m));
				}
			}
		}
		return artTop;
	}

	/**
	 * Returns the articles that see the user when enters
	 * 
	 * @return Returns the articles that the user will see in the timeline
	 */
	public ArrayList<Article> getFirstArticles() {
		ArrayList<Article> artDB = new ArrayList<Article>();
		logger.info("Get the first articles");
		try {
			tx.begin();
			Extent<Article> e = pm.getExtent(Article.class, true);
			Iterator<Article> iter = e.iterator();
			while (iter.hasNext()) {
				artDB.add((Article) iter.next());
			}
			tx.commit();
		} catch (Exception e) {
			logger.info("getFirstArticle: There is no first articles " + e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return artDB;
	}

	public static void loadDB() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		logger.info("Persisting data in the database...");
		Admin alberto = new Admin("FDR", "FDR", "alberto@gmail.com");
		Admin raul = new Admin("Raul", "Sanchez", "raul@gmail.com");
		User paco = new User("Paco", "Paco", "paco@gmail.com");
		User luis = new User("Luis", "Luis", "luis@gmail.com");

		Article art1 = new Article("Title1", "Body", "Category", 0, alberto);
		Article art2 = new Article("Title2", "Body", "Category", 1, alberto);
		Article art3 = new Article("Title3", "Body", "Category", 7, alberto);
		Article art4 = new Article("Title4", "Body", "Category", 7, alberto);
		Article art5 = new Article("Title5", "Body", "Category", 5, raul);
		Article art6 = new Article("Title6", "Body", "Category", 2, raul);
		Article art7 = new Article("Title7", "Body", "Category", 5, raul);

		alberto.addArticle(art1);
		alberto.addArticle(art2);
		alberto.addArticle(art3);
		alberto.addArticle(art4);

		raul.addArticle(art5);
		raul.addArticle(art6);
		raul.addArticle(art7);

		try {
			tx.begin();
			pm.makePersistent(paco);
			pm.makePersistent(luis);
			pm.makePersistent(alberto);
			pm.makePersistent(raul);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
		logger.debug("Persisting data in the DB succeeded");
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IServer objServer = new Server();
			Naming.rebind(name, objServer);
			// loadDB();
			System.out.println("Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
			java.io.BufferedReader stdin = new java.io.BufferedReader(inputStreamReader);
			@SuppressWarnings("unused")
			String line = stdin.readLine();
		} catch (Exception e) {
			System.err.println("Hello exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
