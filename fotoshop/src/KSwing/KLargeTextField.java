package KSwing;

import java.awt.Dimension;

import javax.swing.JTextField;

public class KLargeTextField extends JTextField
{
	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 100;
	
	public KLargeTextField(String text)
	{
		super(text);
		
		this.setMinimumSize(new Dimension(WIDTH, this.getMinimumSize().height));
		this.setPreferredSize(new Dimension(WIDTH, this.getPreferredSize().height));
		this.setMaximumSize(new Dimension(WIDTH, this.getMaximumSize().height));
	}
}
