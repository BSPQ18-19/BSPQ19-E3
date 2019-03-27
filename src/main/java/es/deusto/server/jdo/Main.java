package es.deusto.server.jdo;


import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Extent;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

/**
* This is a sample Main, just for the purpose of showing some pieces of code
*
*/
public class Main
{
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
			User dipina = new User("dipina", "dipina");
			pm.makePersistent(dipina);					 
            tx.commit();
            System.out.println("User and his messages have been persisted");
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
	
            System.out.println("Deleting all users from persistence");
            Query<User> q2 = pm.newQuery(User.class);
            long numberInstancesDeleted2 = q2.deletePersistentAll();
            System.out.println("Deleted " + numberInstancesDeleted2 + " users");
			
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
