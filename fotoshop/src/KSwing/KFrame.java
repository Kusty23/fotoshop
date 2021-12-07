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
		
		this.setVisible(true);
	}
	
	@Override
	abstract public void initialize();

	@Override
	abstract public void createComponents();

	@Override
	abstract public void arrangeComponents();

}
