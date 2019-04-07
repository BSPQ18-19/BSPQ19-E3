package es.deusto.client;

import es.deusto.server.IServer;
import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;

/**
 * Hello world!
 *
 */
public class Client 
{
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			IServer objHello = (IServer) java.rmi.Naming.lookup(name);
			//PROBE CLIENT
			//Register user
			objHello.registerUser("Luis", "Luis","luis@gmail.com"); // Luis is already in the DB so exceptions appears
			objHello.registerUser("dipina", "dipina","dipina@gmail.com"); // Dipina is created in the DB
			//LogIn
			User user = objHello.logIn("dipina", "dipina");// Log In correctly
			User user1 = objHello.logIn("USER", "USER"); // The user doesn't exists
			Admin admin1 = (Admin) objHello.logInAdmin("FDR", "FDR"); // Log in correctly
			//Articles Management
			Article art = new Article("adsa", "body", 123, "category");
			objHello.createArticle(art, admin1);
			objHello.deleteArticle(art, admin1); //Doesn't works
			
			
			
		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
