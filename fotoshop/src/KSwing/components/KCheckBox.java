package KSwing.components;

import javax.swing.JCheckBox;

import KSwing.KConstants;

public class KCheckBox extends JCheckBox
{
	private static final long serialVersionUID = 1L;

	public KCheckBox(String name)
	{
		super(name);

		// Set up look and feel
		this.setBackground(KConstants.BACKGROUND_COLOR);
		this.setForeground(KConstants.TEXT_COLOR);
	}
}
