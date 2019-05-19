package es.deusto.client.controller;

import es.deusto.client.logger.LoggerClient;
import es.deusto.client.model.ClientModel;
import es.deusto.client.view.LandingPageJFrame;
import es.deusto.client.view.LoginJDialog;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LandingPageController {
    private static ClientModel model = ClientModel.getModel();
    private LandingPageJFrame landingPage;
    private JPanel panelMain;
    private JButton buttonLoginUser;
    private JButton buttonRegister;
    private JButton buttonAdmin;
    private JList listArticles;
    private JLabel labelArticles;

    private Logger LOGGER;
    private LoginController login;
    private Boolean userLoggedIn = false;

    public LandingPageController() {
        LOGGER =  LoggerClient.getLogger();

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
    }

    public void showLandingPageWindow() {
        //Show landing page
        landingPage.setVisible(true);
    }

    public void hideLogin() {
        //buttonLoginUser.setVisible(false);
        buttonLoginUser.setText("Log out");
        buttonRegister.setVisible(false);
        buttonAdmin.setVisible(false);
    }

    public void showLogin() {
        //buttonLoginUser.setVisible(false);
        buttonLoginUser.setText("Login");
        buttonRegister.setVisible(true);
        buttonAdmin.setVisible(true);
    }

    private void initListeners() {
        buttonLoginUser.addActionListener(this::actionLoginUser);
        buttonRegister.addActionListener(this::actionRegister);
        buttonAdmin.addActionListener(this::actionAdmin);
    }

    private void initModel() {
        model = ClientModel.getModel();
    }

    private void actionLoginUser(ActionEvent e) {
        //Open login modal box
        if (!model.isLoggedUser()) {
            login = new LoginController(0);

            //Check after close window, if user is logged in
            login.getLogin().addHierarchyListener(this::hierarchyChanged);

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
        login.getLogin().addHierarchyListener(this::hierarchyChanged);

        login.showLoginWindow();
    }


    private void actionAdmin(ActionEvent e) {
        login = new LoginController(2);

        //Check after close window, if user is logged in
        login.getLogin().addHierarchyListener(this::hierarchyChanged);

        login.showLoginWindow();
    }

    private void hierarchyChanged(HierarchyEvent e1) {
        if (model.isLoggedUser() & !userLoggedIn) {
            //Hide all option for log in
            hideLogin();

            //Detection of log in (Fix hierarchy bux)
            userLoggedIn = true;

            LOGGER.info("Detected user log in user: " + model);
        }
    }
}
