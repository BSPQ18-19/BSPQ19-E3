/** @package es.deusto.client.GUI
    @brief This is the brief documentation for the Java package es.deusto.client.GUI which is a package for the client GUI

    This classes are the client GUI
*/
package es.deusto.client.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;




import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
	
	
	private MainWindow mainWindow = this;
	public MainWindow() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JButton btnLoginAsUser = new JButton("Login as User");
		btnLoginAsUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindowUser loginWindow = new LoginWindowUser(mainWindow,1);
				loginWindow.dispose();
				loginWindow.setVisible(true);
				dispose();
				
			}
		});
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		panel.add(btnLoginAsUser);
		
		JButton btnLoginAsAdmin = new JButton("Login as Admin");
		btnLoginAsAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindowUser loginWindow = new LoginWindowUser(mainWindow,2);
				loginWindow.dispose();
				loginWindow.setVisible(true);
				dispose();
				
				
			}
		});
		panel.add(btnLoginAsAdmin);
		
		
		
		
		
	}

	
}
