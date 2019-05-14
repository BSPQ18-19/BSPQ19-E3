package es.deusto.client.model;

import org.apache.log4j.Logger;
import es.deusto.client.logger.LoggerClient;
import es.deusto.client.service.RMIService;
import es.deusto.server.jdo.User;

public class Login {
    private Logger LOGGER;
    private RMIService service;
    private User loggedUser = null;

    public Login() {
        LOGGER =  LoggerClient.getLogger();
        service = RMIService.getService();
    }

    public User login(String email, char[] password) {
        try {
            loggedUser = service.getServer().logIn(email, new String(password));
            LOGGER.info("Loggin is done for user " + loggedUser.getEmail());
        } catch (Exception e) {
            LOGGER.fatal("Not possible login");
        }

        return loggedUser;
    }
}
