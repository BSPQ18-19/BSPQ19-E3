package es.deusto.client.controller;

import es.deusto.client.model.ClientModel;
import es.deusto.client.view.LoginJDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginController {
    private LoginJDialog login;
    private JPanel contentPane;
    private JLabel loginLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordConfirmField;
    private JLabel labelConfirmPassword;
    private JTextField emailField;
    private JLabel labelEmail;

    private ClientModel model;
    private Integer mode;

    LoginController(Integer mode) {
        this.mode = mode;

        initComponents();
        initListeners();
        initModel();
    }

    private void initModel() {
        model = ClientModel.getModel();
    }

    private void initComponents() {
        login = new LoginJDialog();

        loginLabel = login.getLoginLabel();

        contentPane = login.getContentPane();
        buttonOK = login.getButtonOK();
        buttonCancel = login.getButtonCancel();
        passwordField = login.getPasswordField();
        usernameField = login.getUsernameField();

        emailField = login.getEmailField();
        labelEmail = login.getLabelEmail();
        passwordConfirmField = login.getPasswordConfirmField();
        labelConfirmPassword = login.getLabelConfirmPassword();

        switch (mode) {
            case 1:
                loginLabel.setText("Register your user");
                showRegisterFields();
                break;
            case 2:
                loginLabel.setText("Login as admin");
                hideRegisterFields();
                break;
            default:
                loginLabel.setText("Login as user");
                hideRegisterFields();
        }
    }

    void showLoginWindow() {
        //Show landing page
        login.setVisible(true);
    }

    private void showRegisterFields() {
        passwordConfirmField.setVisible(true);
        labelConfirmPassword.setVisible(true);
        emailField.setVisible(true);
        labelEmail.setVisible(true);
    }

    private void hideRegisterFields() {
        passwordConfirmField.setVisible(false);
        labelConfirmPassword.setVisible(false);
        emailField.setVisible(false);
        labelEmail.setVisible(false);
    }

    private void initListeners() {
        buttonOK.addActionListener(this::onOK);
        buttonCancel.addActionListener(this::onCancel);
    }

    private void onOK(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String email = null;
        char[] password = passwordField.getPassword();
        char[] confirmation = null;

        if(mode == 1) {
            email = emailField.getText();
            confirmation = passwordConfirmField.getPassword();
        }

        try {
            model.logIn(username, password, email, confirmation, mode);
            login.dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(login, "Problem: " + ex.getMessage());
        }
    }

    private void onCancel(ActionEvent actionEvent) {
        // add your code here
        login.dispose();
    }

    public LoginJDialog getLogin() {
        return login;
    }
}
