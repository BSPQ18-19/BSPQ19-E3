package es.deusto.client;

import javax.swing.*;

/*import es.deusto.server.IServer;
import es.deusto.server.Server;
import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;*/

import es.deusto.client.GUI.LandingPageJFrame;
import java.util.logging.Logger;
import es.deusto.client.logger.ClientLogger;


/**
 * Java Swing GUI Application with IntelliJ IDEA IDE replacing Alberto console version
 * @author Petr & Alberto
 */
public class Client {
	public static void main(String[] args) { //throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
		Logger logger = ClientLogger.getLogger();

		if (args.length != 3) {
			logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		//Change default look for app - it doesn't work, IDK
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		//Always run GUI code on the event dispatch thread (lambda syntax)
		SwingUtilities.invokeLater(Client::run);
		logger.info("Client initialization finished");
	}

	private static void run() {
		//Create new object landingPage
		LandingPageJFrame landingPage = new LandingPageJFrame();
		//Size the landingPage
		landingPage.pack();
		//Show it
		landingPage.setVisible(true);
		//App in the middle of the screen
		landingPage.setLocationRelativeTo(null);
	}
}
