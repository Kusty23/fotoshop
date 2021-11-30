import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ProjectPropertiesPanel extends JPanel
{
	Project m_project;
	
	SpringLayout m_springLayout;
	
	private static final int PADDING = 10;
	
	public ProjectPropertiesPanel(Project project)
	{
		this.m_project = project;
		
		m_springLayout = new SpringLayout();
		this.setLayout(m_springLayout);
		
		// Name
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField("Insert Name Here");
		
		this.add(nameLabel);
		this.add(nameField);
		
		alignRow(new Component[] {nameLabel, nameField}, null);
		
		// Size
		JLabel widthLabel = new JLabel("Width:");
		JLabel heightLabel = new JLabel("Height:");
		JTextField widthField = new JTextField("Width");
		JTextField heightField = new JTextField("Height");
		
		this.add(widthLabel);
		this.add(widthField);
		this.add(heightLabel);
		this.add(heightField);
		
		alignRow(new Component[] {widthLabel, widthField, heightLabel, heightField}, nameLabel);
	}
	
	private void addBelow(Component a, Component b)
	{
		m_springLayout.putConstraint(SpringLayout.NORTH, a, PADDING, SpringLayout.SOUTH, b);
	}
	
	private void addEast(Component a, Component b)
	{
		m_springLayout.putConstraint(SpringLayout.WEST, a, PADDING, SpringLayout.EAST, b);
	}
	
	private void alignCenter(Component a, Component b)
	{
		m_springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, a, 0, SpringLayout.VERTICAL_CENTER, b);
	}
	
	private void alignRow(Component[] components, Component above)
	{
		// Get first component in row
		Component a = components[0];
		
		// Add row below component if given
		if (above != null)
			addBelow(a, above);
		
		// Align left edge of first component with panel edge
		m_springLayout.putConstraint(SpringLayout.WEST, a, PADDING, SpringLayout.WEST, this);
		
		// Add each component in row
		for (int i = 1; i < components.length; i++)
		{
			addEast(components[i], a);
			alignCenter(components[i], a);
			a = components[i];
		}
		
		// Align right edge of last component with panel edge
		m_springLayout.putConstraint(SpringLayout.EAST, this, PADDING, SpringLayout.EAST, a);
	}
}
