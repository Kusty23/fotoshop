package app;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KSwing.KSpringLayout;

public class PropertiesPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	protected KSpringLayout m_springLayout;

	// Current Component
	private static JComboBox<String> m_currentComponentBox;
	
	// Name
	private JLabel m_nameLabel;
	private JTextField m_nameField;

	// Dimension
	private JLabel m_widthLabel, m_heightLabel;
	private JTextField m_widthField, m_heightField;

	// Offset
	private JLabel m_offsetXLabel, m_offsetYLabel;
	private JTextField m_offsetXField, m_offsetYField;

	// Must keep track of the lowest component to 
	private Component m_lowestComponent;

	public PropertiesPanel()
	{
		m_springLayout = new KSpringLayout();
		this.setLayout(m_springLayout);
		
		addCurrentComponentSelector();
	}

	public Component getLowestComponent()
	{
		return this.m_lowestComponent;
	}
	
	public void setLowestComponent(Component c)
	{
		this.m_lowestComponent = c;
	}
	
	private void addCurrentComponentSelector()
	{		
		m_currentComponentBox = new JComboBox<String>();
		
		this.add(m_currentComponentBox);
		
		m_springLayout.addFirstRow(new Component[] {m_currentComponentBox}, this);
		
		m_currentComponentBox.addItem(InfoPanel.PROJECT_ID_STRING);
		
		for (int layerID : InfoPanel.getLayerIDs())
		{
			m_currentComponentBox.addItem(String.valueOf(layerID));
		}
	}
	
	protected void addNameProperty()
	{
		m_nameLabel = new JLabel("Name:");
		m_nameField = new JTextField("NAME GOES HERE");

		this.add(m_nameLabel);
		this.add(m_nameField);

		m_springLayout.addRow(new Component[] {m_nameLabel, m_nameField}, this);
	}

	protected void addDimensionProperty()
	{
		// Size
		m_widthLabel = new JLabel("Width:");
		m_heightLabel = new JLabel("Height:");
		m_widthField = new JTextField("Width");
		m_heightField = new JTextField("Height");

		this.add(m_widthLabel);
		this.add(m_widthField);
		this.add(m_heightLabel);
		this.add(m_heightField);

		m_springLayout.addRow(new Component[] {m_widthLabel, m_widthField, m_heightLabel, m_heightField}, this);
	}

	protected void addOffsetProperty()
	{
		// Size
		m_offsetXLabel = new JLabel("X:");
		m_offsetYLabel = new JLabel("Y:");
		m_offsetXField = new JTextField("OffsetX");
		m_offsetYField = new JTextField("OffsetY");

		this.add(m_offsetXLabel);
		this.add(m_offsetXField);
		this.add(m_offsetYLabel);
		this.add(m_offsetYField);

		m_springLayout.addRow(new Component[] {m_offsetXLabel, m_offsetXField, m_offsetYLabel, m_offsetYField}, this);
	}
}
