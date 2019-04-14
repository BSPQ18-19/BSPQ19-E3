package es.deusto.client.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class MainWindow extends JFrame {
	
	
	
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
		panel.add(btnLoginAsUser, "1, 1, fill, fill");
		
		JButton btnLoginAsAdmin = new JButton("Login as Admin");
		panel.add(btnLoginAsAdmin, "2, 1, fill, fill");
		
		
		
		
		
	}

	
}
