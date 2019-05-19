package es.deusto.client.model;

import es.deusto.client.logger.LoggerClient;
import es.deusto.client.service.RMIService;
import es.deusto.server.jdo.User;
import org.apache.log4j.Logger;

public class ClientModel {
    private static ClientModel model = new ClientModel();
    private Logger LOGGER;
    private RMIService service;

    private User loggedUser = null;

    private ClientModel() {
        LOGGER =  LoggerClient.getLogger();
        service = RMIService.getService();
    }

    public static ClientModel getModel() {
        return model;
    }

    public void logIn(String username, char[] password, String email, Integer mode) throws IllegalArgumentException {
        if (username.length() == 0 || password.length == 0 || (email != null && email.length() == 0)) {
            throw new IllegalArgumentException("You must fill in all fields");
        }
        if (username.length() < 4) {
            throw new IllegalArgumentException("Username is too short");
        }
        if (password.length < 4) {
            throw new IllegalArgumentException("Password is too weak (please use more than 5 characters");
        }
        if (email != null && email.length() < 6) {
            throw new IllegalArgumentException("Your email adress is too short");
        }

        try {
            switch (mode) {
                case 1:
                    if(service.getServer().registerUser(username, new String(password), email)) {
                        loggedUser = service.getServer().logIn(username, new String(password));
                    } else {
                        throw new IllegalArgumentException("User can not be create");
                    }
                    break;
                case 2:
                    loggedUser = service.getServer().logInAdmin(username, new String(password));
                    break;
                default:
                    loggedUser = service.getServer().logIn(username, new String(password));
            }

            LOGGER.info("Log in is done for user: " + model);
        } catch (Exception e) {
            throw new IllegalArgumentException("User doesn't exist");
        }
    }

    public void logOut() {
        LOGGER.info("Your user was log out " + model);
        loggedUser = null;
    }

    @Override
    public String toString() {
        return loggedUser.username;
    }

    public Boolean isLoggedUser() {
        return loggedUser != null;
    }
}
