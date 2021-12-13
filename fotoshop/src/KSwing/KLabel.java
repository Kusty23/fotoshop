package KSwing;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class KLabel extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 50;

	public KLabel(String text)
	{
		super(text);
		
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.setMinimumSize(new Dimension(WIDTH, this.getMinimumSize().height));
		this.setPreferredSize(new Dimension(WIDTH, this.getPreferredSize().height));
		this.setMaximumSize(new Dimension(WIDTH, this.getMaximumSize().height));
		
		this.setForeground(KConstants.TEXT_COLOR);
	}
}
