package es.deusto.client.model;

import es.deusto.client.logger.LoggerClient;
import es.deusto.client.service.RMIService;
import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientModel {
    private static ClientModel model = new ClientModel();
    private Logger LOGGER;
    private RMIService service;

    private Article article = null;
    private User loggedUser = null;
    private Admin loggedAdmin = null;
    private Integer mode = 0;
    private DefaultListModel<String> articles;
    private Integer index = 0;
    private ResourceBundle bundle = ResourceBundle.getBundle("lang/translations_ES");

    private ClientModel() {
        LOGGER =  LoggerClient.getLogger();
        service = RMIService.getService();

        articles = new DefaultListModel<String>();
        connectArticles();
    }

    public static ClientModel getModel() {
        return model;
    }

    public void logIn(String username, char[] password, String email, char[] confirmation, Integer mode) throws IllegalArgumentException {
        String passwordString = new String(password);
        this.mode = mode;

        if (username.length() == 0 || password.length == 0 || (email != null && email.length() == 0)) {
            throw new IllegalArgumentException("You must fill in all fields");
        }
        if (username.length() < 4) {
            throw new IllegalArgumentException("Username is too short");
        }
        if (password.length < 4) {
            throw new IllegalArgumentException("Password is too weak (please use more than 5 characters");
        }
        if (mode == 1) {
            String confirmationString = new String(confirmation);

            if(email.length() < 6) {
                throw new IllegalArgumentException("Your email adress is too short");
            }
            if(!passwordString.equals(confirmationString)) {
                throw new IllegalArgumentException("Passwords do not match");
            }
        }

        try {
            switch (mode) {
                case 1:
                    if(service.getServer().registerUser(username, passwordString, email)) {
                        loggedUser = service.getServer().logIn(username, passwordString);
                    } else {
                        throw new IllegalArgumentException("User can not be create");
                    }
                    break;
                case 2:
                    loggedAdmin = service.getServer().logInAdmin(username, passwordString);
                    break;
                default:
                    loggedUser = service.getServer().logIn(username, passwordString);
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
        mode = 0;
    }

    public Boolean isLogInUser() {
        return loggedUser != null || loggedAdmin != null;
    }

    public Integer getMode() {
        return mode;
    }

    @Override
    public String toString() {
        if (loggedUser != null) {
            return loggedUser.username;
        }
        if (loggedAdmin != null) {
            return loggedAdmin.username;
        }
        return "";
    }

    private void connectArticles() {
        try {
            ArrayList<Article> arts = service.getServer().getFirstArticles();
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

    public Article editArticle(Article art, String title, String body) {
        try {
            //article = service.getServer().editArticle(art, title, body);
        } catch (Exception ex) {
            LOGGER.info(ex);
        }
        return article;
    }

    public void addArticle(String title, String body) {
        article = new Article(title, body, null, loggedAdmin);

        try {
            if(service.getServer().createArticle(article, loggedAdmin)) {
                articles.add(index, title);
            }
        } catch (RemoteException ex) {
            LOGGER.info(ex);
        }
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}
