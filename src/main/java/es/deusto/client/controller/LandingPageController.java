package es.deusto.client.controller;

import es.deusto.client.logger.LoggerClient;
import es.deusto.client.model.ClientModel;
import es.deusto.client.view.LandingPageJFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.*;
import java.util.ResourceBundle;
import java.util.Scanner;


public class LandingPageController {
    private static ClientModel model = ClientModel.getModel();
    private LandingPageJFrame landingPage;
    private JPanel panelMain;
    private JButton buttonLoginUser;
    private JButton buttonRegister;
    private JButton buttonAdmin;
    private JButton buttonAddArticle;
    private JList listArticles;
    private JLabel labelArticles;
    private JComboBox comboBoxLanguage;

    private Logger LOGGER;
    private LoginController login;
    private ArticleController article;
    private Boolean userLoggedIn = false;
    private Integer mode = 0;

    public LandingPageController() {
        LOGGER =  LoggerClient.getLogger();
        //Scanner scan = new Scanner(System.in);

        initComponents();
        initListeners();
        initModel();
    }

    private void initComponents() {
        landingPage = new LandingPageJFrame();

        panelMain = landingPage.getPanelMain();
        buttonLoginUser = landingPage.getButtonLoginUser();
        buttonRegister = landingPage.getButtonRegister();
        buttonAdmin = landingPage.getButtonAdmin();
        listArticles = landingPage.getListArticles();
        labelArticles = landingPage.getLabelArticles();
        buttonAddArticle = landingPage.getButtonNewArticle();
        comboBoxLanguage = landingPage.getComboBoxLanguage();
    }

    public void showLandingPageWindow() {
        //Show landing page
        landingPage.setVisible(true);

        //When user don't log in
        listArticles.setVisible(false);
        labelArticles.setVisible(false);

        //Only for Admin
        buttonAddArticle.setVisible(true);
    }

    private void hideLogin() {
        buttonLoginUser.setText(model.getBundle().getString("LogIn_User"));

        buttonRegister.setVisible(false);
        buttonAdmin.setVisible(false);

        listArticles.setVisible(true);
        labelArticles.setVisible(true);

        listArticles.setModel(model.getArticles());


        LOGGER.info(this.mode);
        if(this.mode == 2) {
            buttonAddArticle.setVisible(true);
        }
    }

    private void showLogin() {
        buttonLoginUser.setText("Login");

        buttonRegister.setVisible(true);
        buttonAdmin.setVisible(true);

        listArticles.setVisible(false);
        labelArticles.setVisible(false);

        if(this.mode == 2) {
            buttonAddArticle.setVisible(false);
        }
    }

    private void initListeners() {
        buttonLoginUser.addActionListener(this::actionLoginUser);
        buttonRegister.addActionListener(this::actionRegister);
        buttonAdmin.addActionListener(this::actionAdmin);
        listArticles.addListSelectionListener(this::actionArticle);
        buttonAddArticle.addActionListener(this::actionAddArticle);
        comboBoxLanguage.addActionListener(this::switchLanguages);
    }

    private void initModel() {
        model = ClientModel.getModel();
    }

    private void actionLoginUser(ActionEvent e) {
        //Open login modal box
        if (!model.isLogInUser()) {
            login = new LoginController(0);

            //Check after close window, if user is logged in
            login.getLogin().addHierarchyListener(this::hierarchyUserChanged);

            //Load articles
            model.getArticles();

            //Show modal box
            login.showLoginWindow();
        } else {
            //Use for terminate logged user
            model.logOut();

            //Detection of log in
            userLoggedIn = false;

            //Show buttons when nobody log in
            showLogin();
        }
    }

    private void actionRegister(ActionEvent e) {
        login = new LoginController(1);

        //Check after close window, if user is logged in
        login.getLogin().addHierarchyListener(this::hierarchyUserChanged);

        login.showLoginWindow();
    }


    private void actionAdmin(ActionEvent e) {
        login = new LoginController(2);

        //Check after close window, if user is logged in
        login.getLogin().addHierarchyListener(this::hierarchyUserChanged);

        login.showLoginWindow();
    }

    private void actionArticle(ListSelectionEvent listSelectionEvent) {
        article = new ArticleController(listArticles.getSelectedValue().toString());

        article.showArticleWindow();
    }

    private void actionAddArticle(ActionEvent actionEvent) {
        article = new ArticleController(null);

        //Check after close window, if admin add new arcitle
        login.getLogin().addHierarchyListener(this::hierarchyArticleChanged);

        article.showArticleWindow();
    }

    private void hierarchyUserChanged(HierarchyEvent e1) {
        if (model.isLogInUser() & !userLoggedIn) {
            //Which role is log in
            this.mode = model.getMode();
            LOGGER.info("Role level: " + this.mode);

            //Hide all option for log in
            hideLogin();

            //Detection of log in (Fix hierarchy bux)
            userLoggedIn = true;

            LOGGER.info("Detected user log in user: " + model);
        }
    }

    private void hierarchyArticleChanged(HierarchyEvent e1) {
        listArticles.setModel(model.getArticles());
    }

    private void switchLanguages(ActionEvent actionEvent) {
        /*String lang = (String) this.comboBoxLanguage.getSelectedItem();
        if (lang == "Česky") {
            model.setBundle(ResourceBundle.getBundle("lang/translations_CZ"));
        }*/
    }
}
