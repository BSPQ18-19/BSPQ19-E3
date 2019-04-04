package es.deusto.server.jdo;

import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class DBTest {
	 public static void main(String args[])
	    {
	        // Create a PersistenceManagerFactory for this datastore
	        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

	        System.out.println("DataNucleus AccessPlatform with JDO");
	        System.out.println("===================================");

	        // Persistence of a set of Accounts and a User
	        PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
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
			
			

	        // Basic Extent of all Messages
	        pm = pmf.getPersistenceManager();
	        tx = pm.currentTransaction();
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
