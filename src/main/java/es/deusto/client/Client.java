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
			// Register to be allowed to send messages
			objHello.registerUser("Luis", "Luis","dipina@gmail.com");
			objHello.registerUser("dipina", "dipina","dipina@gmail.com");
			User user = objHello.logIn("dipina", "dipina");
			Admin user1 = (Admin) objHello.logInAdmin("FDR", "FDR");
			objHello.createArticle("adsa", "body", 123, "category", user1);
			System.out.println(user1.username);
			//System.out.println("* Message coming from the server: '" + objHello.SayHello());
			
		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
