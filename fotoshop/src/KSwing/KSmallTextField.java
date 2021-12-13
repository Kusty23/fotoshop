package KSwing;

import java.awt.Dimension;

import javax.swing.JTextField;

public class KSmallTextField extends JTextField
{
	private static final long serialVersionUID = 1L;

	private final int WIDTH = 45;

	public KSmallTextField(String text)
	{
		super(text);

		// Set up look and feel
		this.setBackground(KConstants.COMPONENT_COLOR);
		this.setForeground(KConstants.TEXT_COLOR);

		this.setMinimumSize(new Dimension(WIDTH, this.getMinimumSize().height));
		this.setPreferredSize(new Dimension(WIDTH, this.getPreferredSize().height));
		this.setMaximumSize(new Dimension(WIDTH, this.getMaximumSize().height));
	}
}
