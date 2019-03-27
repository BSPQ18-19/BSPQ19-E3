package es.deusto.server.jdo;

import java.util.ArrayList;

public class Admin extends User{
	
	public ArrayList<Article> ownArticles;
	
	public Admin(String username, String password, ArrayList<Article> articles) {
		super(username, password);
		this.ownArticles=articles;
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
