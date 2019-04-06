package es.deusto.client.GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import es.deusto.server.Server;
import es.deusto.server.jdo.User;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;




public class Login extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public Login() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(84, 11, 416, 60);
		getContentPane().add(panel);
		JLabel Logo = new JLabel();
		Logo.setIcon(new ImageIcon(""));
		Logo.setBounds(0, 0, 416, 60);
		panel.add(Logo);
	
		
		
		textField = new JTextField();
		textField.setBounds(125, 175, 120, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(339, 175, 120, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(151, 150, 73, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(362, 150, 63, 14);
		getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user= lblUsername.getText();
				String pass= lblPassword.getText();
				
			
			}
		});
		btnLogin.setBounds(452, 327, 89, 23);
		getContentPane().add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 584, 361);
		getContentPane().add(panel_1);
		
	}
}

