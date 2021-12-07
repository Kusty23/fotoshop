package app;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import KSwing.KComboBox;
import KSwing.KLabel;
import KSwing.KLargeTextField;
import KSwing.KPanel;
import KSwing.KSpringLayout;
import KSwing.KSmallTextField;
import fotoshop.Layer;

public class PropertiesPanel extends KPanel
{
	private static final long serialVersionUID = 1L;

	private KSpringLayout m_springLayout;
	
	private Layer m_layer;

	// Name
	private KLabel m_nameLabel;
	private KLargeTextField m_nameField;

	// Dimension
	private KLabel m_dimensionLabel;
	private KSmallTextField m_widthField, m_heightField;
	private JCheckBox m_useOriginalAspectRatio;

	// Offset
	private KLabel m_offsetLabel;
	private KSmallTextField m_offsetXField, m_offsetYField;

	// Opacity
	private KLabel m_opacityLabel;
	private KLargeTextField m_opacityField;
	
	// Blend Mode
	private KLabel m_blendLabel;
	private KComboBox<String> m_blendBox;

	// Must keep track of the lowest component to 
	private Component m_lowestComponent;

	public PropertiesPanel()
	{		

	}

	@Override
	public void initialize() 
	{

	}

	@Override
	public void createComponents() 
	{

	}

	@Override
	public void arrangeComponents() 
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
		m_nameLabel = new KLabel("Name:");

		m_nameField = new KLargeTextField(name);

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
		m_dimensionLabel = new KLabel("Size:");
		m_widthField = new KSmallTextField(String.valueOf(dimension.width));
		m_heightField = new KSmallTextField(String.valueOf(dimension.height));

		// Add ActionListener
		m_widthField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateDimension(e);}
		});

		m_heightField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateDimension(e);}
		});

		m_widthField.setName("m_widthField");
		m_heightField.setName("m_heightField");

		this.add(m_dimensionLabel);
		this.add(m_widthField);
		this.add(m_heightField);

		m_springLayout.addRow(new Component[] {m_dimensionLabel, m_widthField, m_heightField}, this);

		// Use original aspect ratio
		m_useOriginalAspectRatio = new JCheckBox("Use original aspect ratio");
		m_useOriginalAspectRatio.setSelected(true);

		this.add(m_useOriginalAspectRatio);

		m_springLayout.addRow(new Component[] {m_useOriginalAspectRatio}, this);
	}

	protected void addOffsetProperty(Dimension offset)
	{
		m_offsetLabel = new KLabel("Offset:");
		m_offsetXField = new KSmallTextField(String.valueOf(offset.width));
		m_offsetYField = new KSmallTextField(String.valueOf(offset.height));

		m_offsetXField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset();}
		});

		m_offsetYField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset ();}
		});

		this.add(m_offsetLabel);
		this.add(m_offsetXField);
		this.add(m_offsetYField);

		m_springLayout.addRow(new Component[] {m_offsetLabel, m_offsetXField, m_offsetYField}, this);
	}

	public void addOpacityProperty(double opacity)
	{
		m_opacityLabel = new KLabel("Opacity:");
		m_opacityField = new KLargeTextField(String.valueOf(opacity));
		
		m_opacityField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOpacity();}
		});
		
		this.add(m_opacityLabel);
		this.add(m_opacityField);

		m_springLayout.addRow(new Component[] {m_opacityLabel, m_opacityField}, this);
	}
	
	public void addBlendProperty()
	{
		m_blendLabel = new KLabel("Blend:");

		String[] options = {"Normal", "Dissolve", "Multiply", "Screen", "Color Burn", "Color Dodge", "Overwrite"};
		m_blendBox = new KComboBox<String>(options);

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
	
	private void updateOpacity()
	{
		double opacity = Double.parseDouble(m_opacityField.getText());
		
		m_layer.setOpacity(opacity);
		
		ViewPanel.getInstance().repaint();
	}
	
	private void updateBlend()
	{		
		m_layer.setBlendMode(m_blendBox.getSelectedIndex());
	
		ViewPanel.getInstance().repaint();
	}
}
