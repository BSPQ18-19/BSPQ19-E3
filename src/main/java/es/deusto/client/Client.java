package es.deusto.client;

import es.deusto.client.logger.LoggerClient;
import org.apache.log4j.Logger;
import es.deusto.client.controller.LandingPageController;
import es.deusto.client.service.RMIService;

import javax.swing.*;
/**
 * Client main class in network application (client-server paradigm)
 * Java Swing GUI Application with IntelliJ IDEA IDE insted of Alberto console version from previous commits.
 * @author Petr & Alberto
 */
public class Client {

    public static void main(String[] args) {
        Logger LOGGER = LoggerClient.getLogger();

        if (args.length != 3) {
            LOGGER.fatal("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
            System.exit(0);
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Client client = new Client();
        client.setService(args[0], Integer.parseInt(args[1]), args[2]);

        //Always run GUI code on the event dispatch thread (lambda syntax)
        SwingUtilities.invokeLater(Client::run);
        LOGGER.info("Client initialization finished");
    }

    private static void run() {
        //Create new object landingPage
        LandingPageController landingPage = new LandingPageController();
        landingPage.showLandingPageWindow();
    }

    private void setService(String IP, Integer PORT, String SERVER_NAME) {
        RMIService service = RMIService.getService();
        service.setService(IP, PORT, SERVER_NAME);
    }

}
