package KSwing;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class KComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;

	private final int WIDTH = 100;

	public KComboBox(T[] options)
	{
		super(options);

		// Set up look and feel
		this.setBackground(KConstants.COMPONENT_COLOR);
		this.setForeground(KConstants.TEXT_COLOR);		

		this.setMinimumSize(new Dimension(WIDTH, this.getMinimumSize().height));
		this.setPreferredSize(new Dimension(WIDTH, this.getPreferredSize().height));
		this.setMaximumSize(new Dimension(WIDTH, this.getMaximumSize().height));
	}
}
