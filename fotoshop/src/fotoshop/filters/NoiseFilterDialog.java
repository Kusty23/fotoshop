package fotoshop.filters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

import KSwing.components.KButton;
import KSwing.components.KComboBox;
import KSwing.components.KLargeLabel;
import KSwing.components.KLargeTextField;
import KSwing.containers.KDialog;
import app.MainFrame;
import fotoshop.project.Project;

public class NoiseFilterDialog extends KDialog
{
	private static final long serialVersionUID = 1L;

	// Noise Strength
	private KLargeLabel m_strengthLabel;
	private KLargeTextField m_strengthField;
	
	// Noise Type
	private KLargeLabel m_typeLabel;
	private KComboBox<String> m_typeBox;
	private Dictionary<String, Integer> m_typeDict;

	// OK Button
	KButton m_okButton;

	public NoiseFilterDialog()
	{
		super();
	}

	@Override
	public void initialize() 
	{
		this.setLocation((int) (MainFrame.getInstance().getSize().getWidth() / 2), 200);
		
		m_typeDict = new Hashtable<String, Integer>();
		m_typeDict.put("Monochromatic", NoiseFilter.MONO);
		m_typeDict.put("Multichromatic", NoiseFilter.MULTI);
	}

	@Override
	public void createComponents() 
	{
		// Noise Strength
		m_strengthLabel = new KLargeLabel("Noise Strength:");
		m_strengthField = new KLargeTextField("10");

		// Noise Type
		m_typeLabel = new KLargeLabel("Noise Type:");
		m_typeBox = new KComboBox<String>(new String[] {"Monochromatic", "Multichromatic"});

		// OK Button
		m_okButton = new KButton("OK");
		m_okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {onOK();}
		});
	}

	@Override
	public void arrangeComponents() 
	{
		// Noise Strength
		m_contentPane.addComponent(m_strengthLabel, 1);
		m_contentPane.addComponent(m_strengthField, 2);

		// Noise Type
		m_contentPane.newRow();
		m_contentPane.addComponent(m_typeLabel, 1);
		m_contentPane.addComponent(m_typeBox, 2);
		
		// OK Button
		m_contentPane.newRow();
		m_contentPane.addComponentAt(m_okButton, 2, 1);
	}

	private void onOK()
	{
		int strength = Integer.valueOf(m_strengthField.getText());
		
		int mode = m_typeDict.get(m_typeBox.getSelectedItem());

		Project.getInstance().getCurrentLayer().addNoiseFilter(strength, mode);

		this.dispose();
	}
}
