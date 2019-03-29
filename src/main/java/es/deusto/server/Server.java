package es.deusto.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;
import es.deusto.server.*;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	protected Server() throws RemoteException {
		super();
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}
	
	protected void finalize () throws Throwable {
		if (tx.isActive()) {
            tx.rollback();
        }
        pm.close();
	}

	
	public void registerUser(String login, String password) {
		//Register User
		
	}


	@Override
	public User logIn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logOut() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User signIn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article readArticle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createArticle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Article> searchArticleTitle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Article> searchArticleCategory() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteArticle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean editArticle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Article> viewTopArticle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
