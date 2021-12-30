package KSwing.components;

import javax.swing.JButton;

import KSwing.KConstants;

public class KButton extends JButton
{
	private static final long serialVersionUID = 3727241047629787232L;

	public KButton(String text)
	{
		super(text);
		
		this.setBackground(KConstants.COMPONENT_COLOR);
	}
}
