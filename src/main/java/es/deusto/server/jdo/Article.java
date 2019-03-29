package es.deusto.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Article {
	
	@PrimaryKey
	public String title = "";
	public String body = "";
	public int visits = 0;
	public String category = "";
	public Admin author;
	
	public Article(String title, String body, int visits, String category, Admin author) {
		super();
		this.title = title;
		this.body = body;
		this.visits = visits;
		this.category = category;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Admin getAuthor() {
		return author;
	}

	public void setAuthor(Admin author) {
		this.author = author;
	}
	
	
}
