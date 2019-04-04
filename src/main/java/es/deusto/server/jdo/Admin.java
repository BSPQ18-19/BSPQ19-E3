package es.deusto.server.jdo;

import java.util.ArrayList;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Admin extends User{
	
	public ArrayList<Article> ownArticles= new ArrayList<Article>();
	
	public Admin(String username, String password, ArrayList<Article> articles) {
		super(username, password);
		this.ownArticles=articles;
	}
	public Admin(String username, String password) {
		super(username, password);
	}

	public void addArticle(Article article) {
		ownArticles.add(article);
	}
	
	
	public ArrayList<Article> getOwnArticles() {
		return ownArticles;
	}

	public void setOwnArticles(ArrayList<Article> ownArticles) {
		this.ownArticles = ownArticles;
	}
	
}
