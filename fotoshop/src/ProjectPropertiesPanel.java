import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KSwing.KSpringLayout;

public class ProjectPropertiesPanel extends JPanel
{
	Project m_project;
	
	KSpringLayout m_springLayout;
	
	public ProjectPropertiesPanel(Project project)
	{
		this.m_project = project;
		
		m_springLayout = new KSpringLayout();
		this.setLayout(m_springLayout);
		
		// Name
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField("Insert Name Here");
		
		this.add(nameLabel);
		this.add(nameField);
		
		m_springLayout.alignRow(new Component[] {nameLabel, nameField}, this);
		
		// Size
		JLabel widthLabel = new JLabel("Width:");
		JLabel heightLabel = new JLabel("Height:");
		JTextField widthField = new JTextField("Width");
		JTextField heightField = new JTextField("Height");
		
		this.add(widthLabel);
		this.add(widthField);
		this.add(heightLabel);
		this.add(heightField);
		
		m_springLayout.alignRowSouth(new Component[] {widthLabel, widthField, heightLabel, heightField}, nameLabel, this);
	}
}
