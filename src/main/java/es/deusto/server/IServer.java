package es.deusto.server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;

public interface IServer extends Remote {
	
	
	Boolean registerUser(String login, String password, String email) throws RemoteException;
	User logIn(String user, String pass) throws RemoteException;
	Admin logInAdmin(String user, String pass) throws RemoteException;
	Article readArticle() throws RemoteException;
	Boolean createArticle(String title, String body, int visits, String category, Admin autho) throws RemoteException;
	ArrayList<Article> searchArticleTitle(ArrayList<Article> art) throws RemoteException;
	ArrayList<Article> searchArticleCategory(ArrayList<Article> art) throws RemoteException;
	ArrayList<Article> searchArticleAuthor(ArrayList<Article> art) throws RemoteException;
	Boolean deleteArticle(String title) throws RemoteException;
	Boolean editArticle(Article e, String newTitle, boolean changeTitle, String newBody, boolean changeBody) throws RemoteException;
	ArrayList<Article> viewTopArticle(ArrayList<Article> art) throws RemoteException;
	Boolean SayHello() throws RemoteException;

} 
