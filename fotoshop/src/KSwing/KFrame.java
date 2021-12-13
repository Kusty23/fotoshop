package KSwing;

import javax.swing.JFrame;

public abstract class KFrame extends JFrame implements KContainer
{
	private static final long serialVersionUID = 1L;

	protected KFrame()
	{
		initialize();
		createComponents();
		arrangeComponents();
		
		// Set up look and feel
		this.getContentPane().setBackground(KConstants.BACKGROUND_COLOR);

		this.setVisible(true);
	}

	@Override
	abstract public void initialize();

	@Override
	abstract public void createComponents();

	@Override
	abstract public void arrangeComponents();

}
