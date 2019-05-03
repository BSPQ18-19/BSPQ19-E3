package es.deusto.client;

import javax.swing.*;
import java.util.logging.Logger;

//Server

//Client
import es.deusto.client.gui.LandingPageJFrame;


/**
 * Java Swing GUI Application with IntelliJ IDEA IDE
 * For understanding
 * 1. https://www.youtube.com/watch?v=G1Zo3UKzB4A
 */
public class Client {
	static Logger logger = Logger.getLogger(Client.class.getName());

	public static void main(String[] args) { //throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
		if (args.length != 3) {
			logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		//Change default look for app - it doesn't work, IDK
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		//Always run GUI code on the event dispatch thread (lambda syntax)
		SwingUtilities.invokeLater(Client::run);

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
