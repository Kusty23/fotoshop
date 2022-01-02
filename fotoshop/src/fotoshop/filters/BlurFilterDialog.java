package fotoshop.filters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import KSwing.components.KButton;
import KSwing.components.KLargeLabel;
import KSwing.components.KSmallTextField;
import KSwing.containers.KDialog;
import app.MainFrame;
import fotoshop.project.Project;

public class BlurFilterDialog extends KDialog
{
	private static final long serialVersionUID = 1L;

	// Blur Radius
	private KLargeLabel m_radiusLabel;
	private KSmallTextField m_radiusField;

	// OK Button
	KButton m_okButton;

	public BlurFilterDialog()
	{
		super();
	}

	@Override
	public void initialize() 
	{
		this.setLocation((int) (MainFrame.getInstance().getSize().getWidth() / 2), 200);
	}

	@Override
	public void createComponents() 
	{
		// Blur Radius
		m_radiusLabel = new KLargeLabel("Blur Radius:");
		m_radiusField = new KSmallTextField("10");

		// OK Button
		m_okButton = new KButton("OK");
		m_okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {onOK();}
		});
	}

	@Override
	public void arrangeComponents() 
	{
		// Blur Radius
		m_contentPane.addComponent(m_radiusLabel, 1);
		m_contentPane.addComponent(m_radiusField, 1);

		// OK Button
		m_contentPane.newRow();
		m_contentPane.addComponentAt(m_okButton, 1, 1);
	}

	private void onOK()
	{
		int radius = Integer.valueOf(m_radiusField.getText());
		
		Project.getInstance().getCurrentLayer().addBlurFilter(radius);
		
		this.dispose();
	}
}
