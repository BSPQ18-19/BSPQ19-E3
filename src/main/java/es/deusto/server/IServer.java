package es.deusto.server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	
	
	void registerUser(String login, String password) throws RemoteException;
	
	
	
	/*
	 * 
	 * Log In (Username, password) -> Returns User
	 * Log Out () -> Void
	 * Sign In (Username, pass) -> Returns User
	 * Read article (Id?) -> Return ArticleDTO
	 * Create article -> (Article(Title, body, User (Type Author))) -> Returns Boolean
	 * Search article -> (Title, Visualizations) -> Returns List<Article>
	 * Delete article -> (ArticleDTO) -> Returns Boolean
	 * Edit article -> (ArticleDTO) -> Returns Boolean
	 * ViewTopArticle -> () -> Returns List<Article>
	 * 
	 */

} 
