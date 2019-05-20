package es.deusto.client.model;

import es.deusto.client.logger.LoggerClient;
import es.deusto.client.service.RMIService;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientModel {
    private static ClientModel model = new ClientModel();
    private Logger LOGGER;
    private RMIService service;

    private Article article = null;
    private User loggedUser = null;
    private DefaultListModel<String> articles;

    private ClientModel() {
        LOGGER =  LoggerClient.getLogger();
        service = RMIService.getService();

        articles = new DefaultListModel<>();
        connectArticles();
    }

    public static ClientModel getModel() {
        return model;
    }

    public void logIn(String username, char[] password, String email, char[] confirmation, Integer mode) throws IllegalArgumentException {
        if (username.length() == 0 || password.length == 0 || (email != null && email.length() == 0)) {
            throw new IllegalArgumentException("You must fill in all fields");
        }
        if (username.length() < 4) {
            throw new IllegalArgumentException("Username is too short");
        }
        if (password.length < 4) {
            throw new IllegalArgumentException("Password is too weak (please use more than 5 characters");
        }
        if (mode == 1 && email.length() < 6) {
            throw new IllegalArgumentException("Your email adress is too short");
        }
        if (mode == 1 && password != confirmation) {
            throw new IllegalArgumentException("Passwords do not match");
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
            LOGGER.info("User doesn't exist or problems with connection");
            throw new IllegalArgumentException("User doesn't exist");
        }
    }

    public void logOut() {
        LOGGER.info("Your user was log out " + model);
        loggedUser = null;
    }

    public Boolean isLogInUser() {
        return loggedUser != null;
    }

    @Override
    public String toString() {
        return loggedUser.username;
    }

    private void connectArticles() {
        try {
            ArrayList<Article> arts = service.getServer().getFirstArticles();
            int index = 0;
            for (Article article: arts) {
                articles.add(index, article.getTitle());
                index++;
            }
        } catch (Exception e) {
            LOGGER.info("Log in is done for user: " + model);
        }
    }

    public ListModel<String> getArticles () {
        return articles;
    }

    public Article getArticle(String title) {
        try {
            article = service.getServer().readArticle(title);
        } catch (Exception ex) {
            LOGGER.info(ex);
        }

        return article;
    }
}
