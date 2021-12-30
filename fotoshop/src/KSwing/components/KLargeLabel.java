package KSwing.components;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import KSwing.KConstants;

public class KLargeLabel extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 100;

	public KLargeLabel(String text)
	{
		super(text);
		
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.setMinimumSize(new Dimension(WIDTH, this.getMinimumSize().height));
		this.setPreferredSize(new Dimension(WIDTH, this.getPreferredSize().height));
		this.setMaximumSize(new Dimension(WIDTH, this.getMaximumSize().height));
		
		this.setForeground(KConstants.TEXT_COLOR);
	}
}
