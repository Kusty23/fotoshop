package KSwing;

import javax.swing.JPanel;

public class KPanel extends JPanel implements KContainer
{
	private static final long serialVersionUID = 1L;

	public KPanel()
	{
		// Set up look and feel
		this.setBackground(KConstants.BACKGROUND_COLOR);
		
		initialize();
		createComponents();
		arrangeComponents();
		
		this.setVisible(true);
	}
	
	@Override
	public void initialize() {
	}

	@Override
	public void createComponents() {
	}

	@Override
	public void arrangeComponents() {
	}
}
