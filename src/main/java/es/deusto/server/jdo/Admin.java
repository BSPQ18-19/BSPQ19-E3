package es.deusto.server.jdo;

import java.util.ArrayList;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.COMPLETE_TABLE)
public class Admin extends User{
	
	@Persistent(defaultFetchGroup="true")
	@Join
	public ArrayList<Article> ownArticles= new ArrayList<Article>();
	
	public Admin(String username, String password, String email, ArrayList<Article> articles) {
		super(username, password, email);
		this.ownArticles=articles;
	}
	public Admin(String username, String password, String email) {
		super(username, password, email);
	}
	private Admin(){super();}
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
