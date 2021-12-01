package app;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KSwing.KSpringLayout;

public class PropertiesPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	protected KSpringLayout m_springLayout;

	// Name
	protected JLabel m_nameLabel;
	protected JTextField m_nameField;

	// Dimension
	protected JLabel m_widthLabel, m_heightLabel;
	protected JTextField m_widthField, m_heightField;

	// Offset
	protected JLabel m_offsetXLabel, m_offsetYLabel;
	protected JTextField m_offsetXField, m_offsetYField;

	protected Component m_lowestComponent;

	public PropertiesPanel()
	{
		m_springLayout = new KSpringLayout();
		this.setLayout(m_springLayout);

		addNameProperty();
	}

	protected void addNameProperty()
	{
		m_nameLabel = new JLabel("Name:");
		m_nameField = new JTextField("NAME GOES HERE");

		this.add(m_nameLabel);
		this.add(m_nameField);

		m_springLayout.alignRow(new Component[] {m_nameLabel, m_nameField}, this);

		m_lowestComponent = m_nameLabel;
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

		m_springLayout.alignRowSouth(new Component[] {m_widthLabel, m_widthField, m_heightLabel, m_heightField}, m_nameLabel, this);
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

		m_springLayout.alignRowSouth(new Component[] {m_offsetXLabel, m_offsetXField, m_offsetYLabel, m_offsetYField}, m_widthLabel, this);
	}
}
