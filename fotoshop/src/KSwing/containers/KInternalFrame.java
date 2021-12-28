package KSwing.containers;

import javax.swing.JInternalFrame;

import KSwing.KConstants;

public class KInternalFrame extends JInternalFrame implements KContainer
{
	private static final long serialVersionUID = 1L;

	public KInternalFrame()
	{
		initialize();
		createComponents();
		arrangeComponents();
		
		// Set up look and feel
		this.getContentPane().setBackground(KConstants.BACKGROUND_COLOR);

		this.setVisible(true);
	}
	
	@Override
	public void initialize() 
	{
		this.setSize(300, 200);
		this.setLocation(100, 100);
	}

	@Override
	public void createComponents() 
	{

	}

	@Override
	public void arrangeComponents() 
	{

	}
}
