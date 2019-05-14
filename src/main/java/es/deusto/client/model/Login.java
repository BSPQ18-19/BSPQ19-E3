package es.deusto.client.model;

import es.deusto.client.service.RMIService;
import es.deusto.server.jdo.User;

public class Login {
    private RMIService service = null;
    private User loggedUser = null;

    public Login() {
        service = RMIService.getService();
    }

    public User login(String email, char[] password) {
        try {
            loggedUser = service.getServer().logIn(email, new String(password));
            System.out.println("Loggin is done for user " + loggedUser.getEmail());
        } catch (Exception e) {
            System.out.println("Not possible login");
        }

        return loggedUser;
    }
}
