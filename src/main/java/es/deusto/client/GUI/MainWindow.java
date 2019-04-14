package es.deusto.client.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
	
	
	private MainWindow mainWindow = this;
	public MainWindow() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("242px"),
				ColumnSpec.decode("242px"),},
			new RowSpec[] {
				RowSpec.decode("311px"),}));
		
		JButton btnLoginAsUser = new JButton("Login as User");
		btnLoginAsUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindowUser loginWindow = new LoginWindowUser(mainWindow,1);
				loginWindow.dispose();
				loginWindow.setVisible(true);
				dispose();
				
			}
		});
		panel.add(btnLoginAsUser, "1, 1, fill, fill");
		
		JButton btnLoginAsAdmin = new JButton("Login as Admin");
		btnLoginAsAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindowUser loginWindow = new LoginWindowUser(mainWindow,2);
				loginWindow.dispose();
				loginWindow.setVisible(true);
				dispose();
				
				
			}
		});
		panel.add(btnLoginAsAdmin, "2, 1, fill, fill");
		
		
		
		
		
	}

	
}
