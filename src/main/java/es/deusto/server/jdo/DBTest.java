package es.deusto.server.jdo;

import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class DBTest {
	static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	
	 public static void main(String args[])
	    {
		 //Basic test: Creates, Read and delete data
		 //basicTestDB();
	        
		 //Load data: Loads data for the app
		 loadDB();
		 
		 //Delete data: Delete the data
		 //deleteDB();		 		 		 
	    }
	 public static void deleteDB() {
		  // Clean out the database
		 PersistenceManager pm = pmf.getPersistenceManager();
		 Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
		
	            System.out.println("Deleting all articles from persistence");
	            Query<Article> q2 = pm.newQuery(Article.class);
	            long numberInstancesDeleted2 = q2.deletePersistentAll();
	            System.out.println("Deleted " + numberInstancesDeleted2 + " articles");
	            
	            System.out.println("Deleting all admin from persistence");
	            Query<Admin> q1 = pm.newQuery(Admin.class);
	            long numberInstancesDeleted1 = q1.deletePersistentAll();
	            System.out.println("Deleted " + numberInstancesDeleted1 + " admin");

	            tx.commit();
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }

	        System.out.println("");
	 }
	 public static void loadDB() {
		 PersistenceManager pm = pmf.getPersistenceManager();
		 Transaction tx=pm.currentTransaction();
		 System.out.println("Persisting users");
		 Admin alberto = new Admin("FDR", "FDR");
		 Admin raul = new Admin("Raul", "Sanchez");
		 User paco = new User("Paco", "Paco");
		 User luis = new User("Luis", "Luis");

         Article art1 = new Article("Title1", "Body", 0, "Category", alberto);
         Article art2 = new Article("Title2", "Body", 1, "Category", alberto);
         //Article art3 = new Article("Title3", "Body", 7, "Category", alberto);
         //Article art4 = new Article("Title4", "Body", 7, "Category", alberto);
         Article art5 = new Article("Title5", "Body", 5, "Category", raul);
         Article art6 = new Article("Title6", "Body", 2, "Category", raul);
         Article art7 = new Article("Title7", "Body", 5, "Category", raul);

         alberto.addArticle(art1);
         alberto.addArticle(art2);
         //alberto.addArticle(art3);
         //alberto.addArticle(art4);

         raul.addArticle(art5);
         raul.addArticle(art6);
         raul.addArticle(art7);
       
					
		 try
	        {	
	            tx.begin();	 
				pm.makePersistent(paco);
				pm.makePersistent(luis);
				pm.makePersistent(alberto);
				pm.makePersistent(raul);
           
	            
	            tx.commit();
	        }finally
	        {
	            if (tx.isActive()){
	                tx.rollback();
	            }
	            pm.close();
	        }
		
		
	        System.out.println("");
			
	 }
	 public static void basicTestDB() {
		// Create a PersistenceManagerFactory for this datastore
		 
	        System.out.println("DataNucleus AccessPlatform with JDO");
	        System.out.println("===================================");

	        // Persistence of a set of Accounts and a User
	        
	       

	        // Basic Extent of all Messages
	        PersistenceManager pm = pmf.getPersistenceManager();
			 Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            System.out.println("Retrieving Extent for Messages");
	            Extent<Article> e = pm.getExtent(Article.class, true);
	            Iterator<Article> iter = e.iterator();
	            while (iter.hasNext())
	            {
	                Object obj = iter.next();
	                System.out.println(">  " + obj);
	            }
	            tx.commit();
	        }
	        catch (Exception e)
	        {
	            System.out.println("Exception thrown during retrieval of Extent : " + e.getMessage());
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	        System.out.println("");
	      
	        try
	        {	
	            tx.begin();
	            System.out.println("Persisting users");
				Admin Alberto = new Admin("FDR", "FDR");
	            Article art1 = new Article("Title1", "Body", 0, "Category", Alberto);
	            Article art2 = new Article("Title2", "Body", 1, "Category", Alberto);
	            Alberto.addArticle(art1);
	            Alberto.addArticle(art2);
				pm.makePersistent(Alberto);					 
	            tx.commit();
	            System.out.println("User and his articles have been persisted");
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	        System.out.println("");
			
			  // Clean out the database
	        pm = pmf.getPersistenceManager();
	        tx = pm.currentTransaction();
	        try
	        {
	            tx.begin();
		
	            System.out.println("Deleting all articles from persistence");
	            Query<Article> q2 = pm.newQuery(Article.class);
	            long numberInstancesDeleted2 = q2.deletePersistentAll();
	            System.out.println("Deleted " + numberInstancesDeleted2 + " articles");
	            
	            System.out.println("Deleting all admin from persistence");
	            Query<Admin> q1 = pm.newQuery(Admin.class);
	            long numberInstancesDeleted1 = q1.deletePersistentAll();
	            System.out.println("Deleted " + numberInstancesDeleted1 + " admin");

	            tx.commit();
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }

	        System.out.println("");
	        System.out.println("End of JDO extension for RMI assignment");
	 }
}
