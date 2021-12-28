package app;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewProjectDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JButton m_okButton;
	
	private Component m_lowestComponent;
	
	JTextField m_nameField;
	
	public NewProjectDialog(MainFrame mf)
	{
		this.setLocation(mf.getWidth() / 2, mf.getHeight() / 2);
		this.setSize(500, 200);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// Project Name
		JLabel projectNameLabel = new JLabel("New Project Name:");
		m_nameField = new JTextField("New Project");
		
		this.add(projectNameLabel);
		this.add(m_nameField);
		
		// OK Button
		m_okButton = new JButton("OK");
		m_okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onOK();}
		});
		
		this.add(m_okButton);
		
		this.setVisible(true);
	}
	
	public Component getLowestComponent()
	{
		return this.m_lowestComponent;
	}

	public void setLowestComponent(Component c)
	{
		this.m_lowestComponent = c;
	}
	
	private void onOK()
	{
		
	}
}
