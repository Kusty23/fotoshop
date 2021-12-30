package fotoshop.project;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import KSwing.components.KSmallLabel;
import KSwing.components.KButton;
import KSwing.components.KLargeLabel;
import KSwing.components.KLargeTextField;
import KSwing.components.KSmallTextField;
import KSwing.containers.KDialog;
import app.InfoPanel;
import app.MainFrame;
import app.ViewPanel;

public class NewProjectDialog extends KDialog
{
	private static final long serialVersionUID = 1L;

	GridBagConstraints gbc;

	// Project Name
	KLargeTextField m_nameField;
	KLargeLabel m_nameLabel;
	
	// Dimension
	private KSmallLabel m_dimensionLabel;
	private KSmallTextField m_widthField, m_heightField;
	
	// OK Button
	KButton m_okButton;

	public NewProjectDialog()
	{
		super();
	}

	@Override
	public void initialize() 
	{
		this.setSize(400, 300);
		this.setLocation((int) (MainFrame.getInstance().getSize().getWidth() / 2), 200);

		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
	}

	@Override
	public void createComponents() 
	{
		// Project Name
		m_nameLabel = new KLargeLabel("Project Name:");
		m_nameField = new KLargeTextField("PROJECT NAME");
		
		// Dimension
		m_dimensionLabel = new KSmallLabel("Size:");
		m_widthField = new KSmallTextField(String.valueOf(100));
		m_heightField = new KSmallTextField(String.valueOf(100));
		
		// OK Button
		m_okButton = new KButton("OK");
		m_okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {onOK();}
		});
	}

	@Override
	public void arrangeComponents() 
	{
		// Project Name
		this.addComponent(m_nameLabel, 1);
		this.addComponent(m_nameField, 2);
		
		// Dimension
		this.newRow();
		
		this.addComponent(m_dimensionLabel, 1);
		this.addComponent(m_widthField, 1);
		this.addComponent(m_heightField, 1);
		
		// OK Button
		this.newRow();
		
		this.addComponentAt(m_okButton, 1, 2);
	}
	
	private void onOK()
	{
		// Project Name
		String name = m_nameField.getText();
		
		// Dimension
		int width = Integer.valueOf(m_widthField.getText());
		int height = Integer.valueOf(m_heightField.getText());
		Dimension pdimension = new Dimension(width, height);
		
		// Create new project
		Project.createNewProject(name, pdimension);
		
		// Redraw the window
		InfoPanel.getInstance().initProjectPropertiesPanel();
		ViewPanel.getInstance().repaint();
		
		// Destroy the window
		this.dispose();
	}
}
