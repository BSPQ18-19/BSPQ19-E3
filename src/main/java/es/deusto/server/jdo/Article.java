package es.deusto.server.jdo;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	public String title = "";
	public String body = "";
	public int visits = 0;
	public String category = "";

	/**
	 * Constructor where you can also set the visits
	 * @param title The title for the article
	 * @param body The body of the article
	 * @param visits How many visits the article have
	 * @param category The category of the article
	 */
	public Article(String title, String body, int visits, String category) {
		super();
		this.title = title;
		this.body = body;
		this.visits = visits;
		this.category = category;
	}

	/**
	 * Constructor for a new article where you can't set the visits (Visits == 0)
	 * @param title The title for the article
	 * @param body The body of the article
	 * @param category The category of the article
	 */
	public Article(String title, String body, String category) {
		super();
		this.title = title;
		this.body = body;
		this.visits = 0;
		this.category = category;
	}
	
	private Article() {
	}
	
	/**
	 * 
	 * @return The title of this article
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title To set a new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @return Return the body of the article
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * 
	 * @param body Set the new body of the article
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * 
	 * @return Return the visits of the article
	 */
	public int getVisits() {
		return visits;
	}
	
	/**
	 * 
	 * @param visits To set how many visits the article has
	 */
	public void setVisits(int visits) {
		this.visits = visits;
	}

	/**
	 * 
	 * @return Gets you the category of the article
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * 
	 * @param category To set a new category of the article
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Compare two articles
	 * @param obj The article to compare with
	 * @return If they are equal or not
	 */
	public boolean equals(Article obj) {
		if (obj.title.equals(this.title)) {
			return true;
		}
		return false;
	}

	/**
	 * Hashcode for the article
	 */
	public int hashCode() {
		return this.title.hashCode() * this.body.hashCode();
	}
	
	/**
	 * Method to print the article
	 */
	public String toString() {
		return this.title + "::" + this.body;
	}
}
