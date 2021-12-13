package KSwing;

import javax.swing.JCheckBox;

public class KCheckBox extends JCheckBox
{
	public KCheckBox(String name)
	{
		super(name);

		// Set up look and feel
		this.setBackground(KConstants.BACKGROUND_COLOR);
		this.setForeground(KConstants.TEXT_COLOR);
	}
}
