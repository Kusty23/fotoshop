package fotoshop.infopanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import KSwing.components.KCheckBox;
import KSwing.components.KComboBox;
import KSwing.components.KLargeTextField;
import KSwing.components.KSmallLabel;
import KSwing.components.KSmallTextField;
import KSwing.containers.KPanel;

public class LayerInfoPanel extends KPanel
{
	private static final long serialVersionUID = 1L;

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
	
	public LayerInfoPanel()
	{
		super();
	}
	
	@Override
	public void initialize() 
	{

	}

	@Override
	public void createComponents() 
	{
		// Name
		m_nameLabel = new KSmallLabel("Name:");
		m_nameField = new KLargeTextField("name");

		m_nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateName();}
		});
		
		// Dimension
		m_dimensionLabel = new KSmallLabel("Size:");
		m_widthField = new KSmallTextField(String.valueOf(100));
		m_heightField = new KSmallTextField(String.valueOf(100));

		m_widthField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateDimension(e);}
		});
		m_heightField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateDimension(e);}
		});
		
		m_useOriginalAspectRatio = new KCheckBox("Keep Aspect Ratio");		
		
		// Offset
		m_offsetLabel = new KSmallLabel("Offset:");
		m_offsetXField = new KSmallTextField(String.valueOf(100));
		m_offsetYField = new KSmallTextField(String.valueOf(100));

		m_offsetXField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset();}
		});

		m_offsetYField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateOffset ();}
		});
		
		// Opacity
		m_opacityLabel = new KSmallLabel("Opacity:");
		m_opacityField = new KLargeTextField(String.valueOf(100));
		
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
				
	}
	
	private void updateDimension(ActionEvent e)
	{

	}
	
	private void updateOffset()
	{

	}
	
	private void updateOpacity()
	{

	}
	
	private void updateBlend()
	{		

	}
}
