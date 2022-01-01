package KSwing.containers;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import KSwing.KConstants;

public class KDialog extends JDialog implements KContainer
{
	private static final long serialVersionUID = 1L;
	
	protected KPanel m_contentPane;
	
	public KDialog()
	{
		// Set content pane
		m_contentPane = new KPanel();
		this.setContentPane(m_contentPane);
		
		initialize();
		createComponents();
		arrangeComponents();
		
		// Set up look and feel
		this.getContentPane().setBackground(KConstants.BACKGROUND_COLOR);
		
		// Can't let users resize the dialog, it will mess up layout
		this.setResizable(false);

		// Give the dialog a bit of padding around the components
		m_contentPane.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		
		// Force size to size to its components
		this.pack();
		
		this.setVisible(true);
	}
	
	@Override
	public void initialize() 
	{

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
