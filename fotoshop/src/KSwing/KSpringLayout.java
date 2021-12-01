package KSwing;

import java.awt.Component;

import javax.swing.SpringLayout;

import app.PropertiesPanel;

public class KSpringLayout extends SpringLayout
{
	private int PADDING = 10;
	
	public KSpringLayout()
	{
		super();
	}
	
	private void appendBelow(Component a, Component b)
	{
		this.putConstraint(SpringLayout.NORTH, a, PADDING, SpringLayout.SOUTH, b);
	}
	
	private void appendRight(Component a, Component b)
	{
		this.putConstraint(SpringLayout.WEST, a, PADDING, SpringLayout.EAST, b);
	}
	
	private void alignCenter(Component a, Component b)
	{
		this.putConstraint(SpringLayout.VERTICAL_CENTER, a, 0, SpringLayout.VERTICAL_CENTER, b);
	}
	
	public void attachNorth(Component a, Component b)
	{
		this.putConstraint(SpringLayout.NORTH, a, PADDING, SpringLayout.NORTH, b);
	}
	
	public void addRow(Component[] components, PropertiesPanel parent)
	{
		// Get first component in row
		Component a = components[0];
		
		// Align the top of first component
		if (parent.getLowestComponent() != null)
		{
			appendBelow(a, parent.getLowestComponent());
		}
		else
		{
			attachNorth(a, parent);
		}
		
		// Set the first component as the new lowest component
		parent.setLowestComponent(a);
		
		// Align left edge of first component with panel edge
		this.putConstraint(SpringLayout.WEST, a, PADDING, SpringLayout.WEST, parent);
		
		// Add each component in row
		for (int i = 1; i < components.length; i++)
		{
			appendRight(components[i], a);
			alignCenter(components[i], a);
			a = components[i];
		}
		
		// Align right edge of last component with panel edge
		this.putConstraint(SpringLayout.EAST, parent, PADDING, SpringLayout.EAST, a);
	}
	
	public void addFirstRow(Component[] components, PropertiesPanel parent)
	{
		// Get first component in row
		Component a = components[0];
		
		// Set the first component as the new lowest component
		parent.setLowestComponent(a);
		
		// Align left edge of first component with panel edge
		this.putConstraint(SpringLayout.WEST, a, PADDING, SpringLayout.WEST, parent);
		
		// Align top edge of first component with panel edge
		this.attachNorth(a, parent);
		
		// Add each component in row
		for (int i = 1; i < components.length; i++)
		{
			appendRight(components[i], a);
			alignCenter(components[i], a);
			a = components[i];
		}
		
		// Align right edge of last component with panel edge
		this.putConstraint(SpringLayout.EAST, a, -PADDING, SpringLayout.EAST, parent);
	}
}
