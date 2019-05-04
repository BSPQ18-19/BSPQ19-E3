package es.deusto.client.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPageJFrame extends JFrame {
    private JButton buttonLoginUser;
    private JPanel panelMain;
    private JButton buttonRegister;
    private JButton buttonAdmin;
    private JComboBox comboBoxLanguage;
    private JList listArticles;
    private JLabel labelArticles;

    public LandingPageJFrame() {
        //This uses the form designer form
        add(panelMain);
        setTitle("Deusto Newspaper");
        //Call event terminate client, after close app
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonLoginUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open login modal box
                LoginJDialog loginJDialog = new LoginJDialog();
                loginJDialog.pack();
                loginJDialog.setLocationRelativeTo(panelMain);
                loginJDialog.setVisible(true);
            }
        });
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open login modal box
                LoginJDialog loginAdminJDialog = new LoginJDialog();
                loginAdminJDialog.pack();
                loginAdminJDialog.setLocationRelativeTo(panelMain);
                loginAdminJDialog.setVisible(true);
            }
        });
    }
}
