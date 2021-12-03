package app;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KSwing.KSpringLayout;
import fotoshop.Layer;

public class PropertiesPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private KSpringLayout m_springLayout;

	private Layer m_layer;

	// Name
	private JLabel m_nameLabel;
	private JTextField m_nameField;

	// Dimension
	private JLabel m_widthLabel, m_heightLabel;
	private JTextField m_widthField, m_heightField;
	private JCheckBox m_useOriginalAspectRatio;

	// Offset
	private JLabel m_offsetXLabel, m_offsetYLabel;
	private JTextField m_offsetXField, m_offsetYField;

	// Blend Mode
	private JLabel m_blendLabel;
	private JComboBox<String> m_blendBox;

	// Must keep track of the lowest component to 
	private Component m_lowestComponent;

	public PropertiesPanel()
	{		
		m_springLayout = new KSpringLayout();
		this.setLayout(m_springLayout);
	}

	public Component getLowestComponent()
	{
		return this.m_lowestComponent;
	}

	public void setLowestComponent(Component c)
	{
		this.m_lowestComponent = c;
	}

	public void setLayer(Layer layer)
	{
		this.m_layer = layer;
	}

	public Layer getLayer()
	{
		return this.m_layer;
	}

	protected void addNameProperty(String name)
	{
		m_nameLabel = new JLabel("Name:");

		m_nameField = new JTextField(name);

		// Add ActionListener
		m_nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateName();}
		});


		this.add(m_nameLabel);
		this.add(m_nameField);

		m_springLayout.addRow(new Component[] {m_nameLabel, m_nameField}, this);
	}

	protected void addDimensionProperty(Dimension dimension)
	{
		m_widthLabel = new JLabel("Width:");
		m_heightLabel = new JLabel("Height:");
		m_widthField = new JTextField(String.valueOf(dimension.width));
		m_heightField = new JTextField(String.valueOf(dimension.height));

		// Add ActionListener
		m_widthField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateDimension(e);}
		});

		m_heightField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateDimension(e);}
		});

		m_widthField.setName("m_widthField");
		m_heightField.setName("m_heightField");

		this.add(m_widthLabel);
		this.add(m_widthField);
		this.add(m_heightLabel);
		this.add(m_heightField);

		m_springLayout.addRow(new Component[] {m_widthLabel, m_widthField, m_heightLabel, m_heightField}, this);

		// Use original aspect ratio
		m_useOriginalAspectRatio = new JCheckBox("Use original aspect ratio");

		this.add(m_useOriginalAspectRatio);

		m_springLayout.addRow(new Component[] {m_useOriginalAspectRatio}, this);
	}

	protected void addOffsetProperty(Dimension offset)
	{
		// Size
		m_offsetXLabel = new JLabel("X:");
		m_offsetYLabel = new JLabel("Y:");
		m_offsetXField = new JTextField(String.valueOf(offset.width));
		m_offsetYField = new JTextField(String.valueOf(offset.height));

		// Add ActionListener
		m_offsetXField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset();}
		});

		m_offsetYField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset ();}
		});

		this.add(m_offsetXLabel);
		this.add(m_offsetXField);
		this.add(m_offsetYLabel);
		this.add(m_offsetYField);

		m_springLayout.addRow(new Component[] {m_offsetXLabel, m_offsetXField, m_offsetYLabel, m_offsetYField}, this);
	}

	public void addBlendProperty()
	{
		m_blendLabel = new JLabel("Blend: ");

		String[] options = {"Normal", "Dissolve", "Multiply", "Screen", "Overwrite"};
		m_blendBox = new JComboBox<String>(options);

		m_blendBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateBlend();}
		});

		this.add(m_blendLabel);
		this.add(m_blendBox);

		m_springLayout.addRow(new Component[] {m_blendLabel, m_blendBox}, this);
	}

	private void updateName()
	{
		String name = m_nameField.getText();

		if (m_layer == null)
		{
			MainFrame.getProject().setName(name);
		}
		else
		{
			m_layer.setName(name);
		}		
	}

	private void updateDimension(ActionEvent e)
	{
		int x = Integer.parseInt(m_widthField.getText());
		int y = Integer.parseInt(m_heightField.getText());

		if (m_useOriginalAspectRatio.isSelected())
		{
			JTextField tf = (JTextField) e.getSource();
			double aspect = m_layer.getOriginalAspect();

			if (tf.getName() == m_widthField.getName())
			{

				y = (int) (x / aspect);
				m_heightField.setText(String.valueOf(y));
			}
			else
			{
				x = (int) (y * aspect);
				m_widthField.setText(String.valueOf(x));
			}
		}

		if (m_layer == null)
		{
			MainFrame.getProject().setDimension(new Dimension(x,y));
		}
		else
		{
			m_layer.setDimension(new Dimension(x,y));
		}

		ViewPanel.getInstance().repaint();
	}

	private void updateOffset()
	{
		int x = Integer.parseInt(m_offsetXField.getText());
		int y = Integer.parseInt(m_offsetYField.getText());

		m_layer.setOffset(new Dimension(x,y));

		ViewPanel.getInstance().repaint();
	}
	
	private void updateBlend()
	{		
		m_layer.setBlendMode(m_blendBox.getSelectedIndex());
	
		ViewPanel.getInstance().repaint();
	}
}
