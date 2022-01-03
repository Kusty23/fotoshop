package fotoshop.infopanels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

import KSwing.components.KCheckBox;
import KSwing.components.KComboBox;
import KSwing.components.KLargeTextField;
import KSwing.components.KSmallLabel;
import KSwing.components.KSmallTextField;
import KSwing.containers.KPanel;
import fotoshop.BlendingModes;
import fotoshop.Layer;
import fotoshop.project.Project;

public class LayerInfoPanel extends KPanel
{
	private static final long serialVersionUID = 1L;

	private Layer m_layer;
	
	// Name
	private KSmallLabel m_nameLabel;
	private KLargeTextField m_nameField;

	// Dimension
	private KSmallLabel m_dimensionLabel;
	private KSmallTextField m_widthField, m_heightField;
	private KCheckBox m_useOriginalAspectRatio;

	// Offset
	private KSmallLabel m_offsetLabel;
	private KSmallTextField m_offsetXField, m_offsetYField;

	// Opacity
	private KSmallLabel m_opacityLabel;
	private KLargeTextField m_opacityField;
	
	// Blend Mode
	private KSmallLabel m_blendLabel;
	private KComboBox<String> m_blendBox;
	private Dictionary<String, Integer> m_blendDict;
	
	public LayerInfoPanel()
	{
		super();
	}
	
	@Override
	public void initialize() 
	{
		m_layer = Project.getInstance().getCurrentLayer();
		
		m_blendDict = new Hashtable<String, Integer>();
		m_blendDict.put("Normal", BlendingModes.NORMAL);
		m_blendDict.put("Dissolve", BlendingModes.DISSOLVE);
		m_blendDict.put("Multiply", BlendingModes.MULTIPLY);
		m_blendDict.put("Screen", BlendingModes.SCREEN);
		m_blendDict.put("Color Burn", BlendingModes.COLOR_BURN);
		m_blendDict.put("Color Dodge", BlendingModes.COLOR_DODGE);
		m_blendDict.put("Overwrite", BlendingModes.OVERWRITE);
	}

	@Override
	public void createComponents() 
	{
		// Name
		m_nameLabel = new KSmallLabel("Name:");
		m_nameField = new KLargeTextField(m_layer.getName());

		m_nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateName();}
		});
		
		// Dimension
		m_dimensionLabel = new KSmallLabel("Size:");
		m_widthField = new KSmallTextField(String.valueOf(m_layer.getDimension().width));
		m_heightField = new KSmallTextField(String.valueOf(m_layer.getDimension().height));

		m_widthField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateWidth();}
		});
		m_heightField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateHeight();}
		});
		
		m_useOriginalAspectRatio = new KCheckBox("Keep Aspect Ratio");		
		m_useOriginalAspectRatio.setSelected(true);
		
		// Offset
		m_offsetLabel = new KSmallLabel("Offset:");
		m_offsetXField = new KSmallTextField(String.valueOf(m_layer.getOffset().width));
		m_offsetYField = new KSmallTextField(String.valueOf(m_layer.getOffset().height));

		m_offsetXField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset();}
		});

		m_offsetYField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset ();}
		});
		
		// Opacity
		m_opacityLabel = new KSmallLabel("Opacity:");
		m_opacityField = new KLargeTextField(String.valueOf(m_layer.getOpacity()));
		
		m_opacityField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOpacity();}
		});
		
		// Blend Mode
		m_blendLabel = new KSmallLabel("Blend:");

		String[] options = {"Normal", "Dissolve", "Multiply", "Screen", "Color Burn", "Color Dodge", "Overwrite"};
		m_blendBox = new KComboBox<String>(options);

		m_blendBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateBlend();}
		});
	}

	@Override
	public void arrangeComponents() 
	{
		// Name
		this.addComponent(m_nameLabel, 1);
		this.addComponent(m_nameField, 2);
		
		// Dimension
		this.newRow();
		this.addComponent(m_dimensionLabel, 1);
		this.addComponent(m_widthField, 1);
		this.addComponent(m_heightField, 1);
		
		this.newRow();
		this.addComponent(m_useOriginalAspectRatio, 3);
		
		// Offset
		this.newRow();
		this.addComponent(m_offsetLabel, 1);
		this.addComponent(m_offsetXField, 1);
		this.addComponent(m_offsetYField, 1);
		
		// Opacity
		this.newRow();
		this.addComponent(m_opacityLabel, 1);
		this.addComponent(m_opacityField, 2);
		
		// Blend Mode
		this.newRow();
		this.addComponent(m_blendLabel, 1);
		this.addComponent(m_blendBox, 2);
	}
	
	private void updateName()
	{
		m_layer.setName(m_nameField.getText());
	}
	
	private void updateWidth()
	{
		int width = Integer.valueOf(m_widthField.getText());
		int height = Integer.valueOf(m_heightField.getText());
		
		if (m_useOriginalAspectRatio.isSelected())
		{
			height = (int) (width / m_layer.getOriginalAspect());
			m_heightField.setText(String.valueOf(height));
		}
		
		m_layer.setDimension(new Dimension(width, height));
	}
	
	private void updateHeight()
	{
		int width = Integer.valueOf(m_widthField.getText());
		int height = Integer.valueOf(m_heightField.getText());
		
		if (m_useOriginalAspectRatio.isSelected())
		{
			width = (int) (height * m_layer.getOriginalAspect());
			m_widthField.setText(String.valueOf(width));
		}
		
		m_layer.setDimension(new Dimension(width, height));
	}
	
	private void updateOffset()
	{
		int offsetX = Integer.valueOf(m_offsetXField.getText());
		int offsetY = Integer.valueOf(m_offsetYField.getText());
		
		m_layer.setOffset(new Dimension(offsetX, offsetY));
	}
	
	private void updateOpacity()
	{
		double opacity = Double.valueOf(m_opacityField.getText());
		
		m_layer.setOpacity(opacity);
	}
	
	private void updateBlend()
	{		
		int mode = m_blendDict.get(m_blendBox.getSelectedItem());
		
		m_layer.setBlendMode(mode);
	}
}
