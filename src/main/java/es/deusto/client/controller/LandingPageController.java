package es.deusto.client.controller;

import es.deusto.client.view.LandingPageJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPageController {
    private LandingPageJFrame landingPage;
    private JPanel panelMain;
    private JButton buttonLoginUser;
    private JButton buttonRegister;
    private JButton buttonAdmin;

    public LandingPageController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        landingPage = new LandingPageJFrame();

        panelMain = landingPage.getPanelMain();
        buttonLoginUser = landingPage.getButtonLoginUser();
        buttonRegister = landingPage.getButtonRegister();
        buttonAdmin = landingPage.getButtonAdmin();
    }

    public void showLandingPageWindow() {
        //Show landing page
        landingPage.setVisible(true);
    }

    private void initListeners() {
        buttonLoginUser.addActionListener(LandingPageController::actionLoginUser);
        buttonRegister.addActionListener(LandingPageController::actionRegister);
        buttonAdmin.addActionListener(LandingPageController::actionAdmin);
    }

    private static void actionLoginUser(ActionEvent e) {
        //Open login modal box
        LoginController login = new LoginController();
        login.showLoginWindow();
    }

    private static void actionRegister(ActionEvent e) {

    }


    private static void actionAdmin(ActionEvent e) {
        LoginController login = new LoginController();
        login.showLoginWindow();
    }
}
