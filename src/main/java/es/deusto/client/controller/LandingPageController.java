package es.deusto.client.controller;

import es.deusto.client.view.LandingPageJFrame;
import es.deusto.client.view.LoginJDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPageController {
    private LandingPageJFrame landingPage;
    private JPanel panelMain;
    private JButton buttonLoginUser;
    private JButton buttonRegister;
    private JButton buttonAdmin;
    private JComboBox comboBoxLanguage;
    private JList listArticles;
    private JLabel labelArticles;

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
        //Show it
        landingPage.setVisible(true);
    }

    private void initListeners() {
        buttonLoginUser.addActionListener(LandingPageController::actionPerformed);
        buttonRegister.addActionListener(LandingPageController::actionPerformed2);
        buttonAdmin.addActionListener(LandingPageController::actionPerformed3);
    }

    private static void actionPerformed(ActionEvent e) {
        //Open login modal box
        /*LoginJDialog loginJDialog = new LoginJDialog();
            loginJDialog.pack();
            loginJDialog.setLocationRelativeTo(panelMain);
            loginJDialog.setVisible(true);*/
    }

    private static void actionPerformed2(ActionEvent e) {
    }


    private static void actionPerformed3(ActionEvent e) {
    }
}
