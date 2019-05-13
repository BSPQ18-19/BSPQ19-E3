package es.deusto.client;

import es.deusto.client.controller.LandingPageController;
import es.deusto.client.service.RMIService;

import javax.swing.*;
/**
 * Java Swing GUI Application with IntelliJ IDEA IDE replacing Alberto console version
 * @author Petr & Alberto
 */
public class Client {
    public static void main(String[] args) { //throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //Logger logger = ClientLogger.getLogger();

        if (args.length != 3) {
            System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
            System.exit(0);
        }

        //Change default look for app - it doesn't work, IDK
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        //Always run GUI code on the event dispatch thread (lambda syntax)
        SwingUtilities.invokeLater(Client::run);
        //logger.info("Client initialization finished");

        Client client = new Client();
        client.setService(args[0], Integer.parseInt(args[1]), args[2]);
    }

    private static void run() {
        //Create new object landingPage
        LandingPageController landingPage = new LandingPageController();
        landingPage.showLandingPageWindow();
    }

    private RMIService service = null;

    private void setService(String IP, Integer PORT, String SERVER_NAME) {
        service = RMIService.getService();
        service.setService(IP, PORT, SERVER_NAME);
    }

}
