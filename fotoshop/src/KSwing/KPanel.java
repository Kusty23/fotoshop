package KSwing;

import javax.swing.JPanel;

public abstract class KPanel extends JPanel implements KContainer
{
	private static final long serialVersionUID = 1L;

	public KPanel()
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
