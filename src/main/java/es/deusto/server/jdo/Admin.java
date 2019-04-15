package es.deusto.server.jdo;

import java.util.ArrayList;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
/**
 * The class for the administrator.
 * @author albertofdr
 *
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public class Admin extends User {

	@Persistent(defaultFetchGroup = "true")
	@Join
	public ArrayList<Article> ownArticles = new ArrayList<Article>();

	/**
	 * Creates an Admin with her articles
	 * @param username The username of the admin
	 * @param password The password of the admin
	 * @param email The email of the admin
	 * @param articles The articles of the admin
	 */
	public Admin(String username, String password, String email, ArrayList<Article> articles) {
		super(username, password, email);
		this.ownArticles = articles;
	}
	/**
	 * Creates an Admin with zero articles
	 * @param username The username of the admin
	 * @param password The password of the admin
	 * @param email The email of the admin
	 */
	public Admin(String username, String password, String email) {
		super(username, password, email);
	}

	private Admin() {
		super();
	}
	
	/**
	 * 
	 * @param article To add an article for the Admin
	 */
	public void addArticle(Article article) {
		if (!ownArticles.contains(article)) {
			ownArticles.add(article);
		}
	}
	
	/**
	 * 
	 * @param article To delete an article
	 */
	public void deleteArticle(Article article) {
		if (ownArticles.contains(article)) {
			ownArticles.remove(article);
		}
	}
	
	/**
	 * 
	 * @return Returns the Admin articles
	 */
	public ArrayList<Article> getOwnArticles() {
		return ownArticles;
	}
	
	/**
	 * 
	 * @param ownArticles To set articles
	 */
	public void setOwnArticles(ArrayList<Article> ownArticles) {
		this.ownArticles = ownArticles;
	}

}
