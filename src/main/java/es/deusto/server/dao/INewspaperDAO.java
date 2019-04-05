package es.deusto.server.dao;

import java.util.ArrayList;

import es.deusto.server.jdo.*;

public interface INewspaperDAO {
	
	void storeNewUser(User user);
	ArrayList<Admin> getAdmins();
	ArrayList<User> getUsers();
	ArrayList<Article> getArticles();
	Boolean storeArticle() ;
	Boolean deleteArticle(); 
	Boolean editArticle();
}
