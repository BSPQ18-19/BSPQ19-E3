/** @package es.deusto.server                                                                                                                  
    @brief This is the brief documentation for the Java package es.deusto.server which is a package for the logic of the server
    
    This class is the server package contains the interface and the class of the server wich communicate with clients and the DB.
*/
package es.deusto.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;

public interface IServer extends Remote {

	
	/**
	 * Register a user in the DB
	 * 
	 * @param login    The username of the person
	 * @param password The password of the person
	 * @param email    The email of the person
	 * @return Returns a Boolean, if the transaction works well returns true
	 */
	Boolean registerUser(String login, String password, String email) throws RemoteException;

	
	/**
	 * To log in a USER
	 * 
	 * @param user The username of the person
	 * @param pass the p logger.info("---The article does not exist in the db, so it
	 *             cannot be edited---"); assword of that person
	 * @return Returns the user of that person
	 */
	User logIn(String user, String pass) throws RemoteException;

	
	/**
	 * To log in a ADMIN
	 * 
	 * @param user The username of the person
	 * @param pass the password of that person
	 * @return Returns the admin of that person
	 */
	Admin logInAdmin(String user, String pass) throws RemoteException;

	
	/**
	 * You send the title of an article which is the one that you want to read and returns the article.
	 * @param String The title of the article
	 * @return Article Returns the article
	 */
	Article readArticle(String title) throws RemoteException;

	/**
	 * To create an article
	 * 
	 * @param Article the article to create
	 * @param Admin   the admin that is creating the article
	 * @return boolean true(created) false(not created)
	 */
	Boolean createArticle(Article art, Admin author) throws RemoteException;

	
	/**
	 * Search the articles which have one category
	 * @param String The category we want to read
	 * @return ArrayList<Article> returns the list of articles with that category 
	 */
	ArrayList<Article> searchArticleCategory(String category) throws RemoteException;

	/**
	 * Search the articles of one author
	 * @param String The name of the author we want to read
	 * @return ArrayList<Article> returns the list of articles of that author 
	 */
	ArrayList<Article> searchArticleAuthor(String author) throws RemoteException;

	/**
	 * To delete an existing article
	 * 
	 * @param Article, the one to be deleted that must be searched in the db
	 * @param Admin, the one that is deleting the article
	 * @return boolean true(deleted) false(not deleted)
	 */
	Boolean deleteArticle(Article art, Admin autho) throws RemoteException;

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
	Boolean editArticle(Article e, String newTitle, String newBody, Admin autho) throws RemoteException;
	/**
	 * A method which search the most visited articles
	 * @return ArrayList<Article> returns the top articles 
	 */
	ArrayList<Article> viewTopArticle() throws RemoteException;
	/**
	 * Returns the articles that see the user when enters
	 * 
	 * @return ArrayList<Article> Returns the articles that the user will see in the timeline
	 */
	ArrayList<Article> getFirstArticles() throws RemoteException;

}
