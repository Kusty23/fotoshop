package KSwing.containers;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import KSwing.KConstants;

public class KDialog extends JDialog implements KContainer
{
	private static final long serialVersionUID = 1L;

	private GridBagLayout m_gbl;
	private GridBagConstraints m_gbc;
	
	public KDialog()
	{
		// Set layout manager
		m_gbl = new GridBagLayout();
		this.setLayout(m_gbl);
		
		// Set up grid bag constraints
		m_gbc = new GridBagConstraints();
		m_gbc.anchor = GridBagConstraints.LINE_START;
		m_gbc.insets = new Insets(2, 5, 2, 5);
		m_gbc.gridx = 0;
		m_gbc.gridy = 0;
		
		initialize();
		createComponents();
		arrangeComponents();
		
		// Set up look and feel
		this.getContentPane().setBackground(KConstants.BACKGROUND_COLOR);
		
		// Can't let users resize the dialog, it will mess up layout
		this.setResizable(false);

		// Give the dialog a bit of padding around the components
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		
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
	
	protected void addComponent(Component c, int gridwidth)
	{
		m_gbc.gridwidth = gridwidth;
		
		this.add(c, m_gbc);
		
		m_gbc.gridx++;
	}
	
	protected void addComponentAt(Component c, int gridwidth, int gridx)
	{
		m_gbc.gridx = gridx;
		m_gbc.gridwidth = gridwidth;
		
		this.add(c, m_gbc);
		
		m_gbc.gridx++;
	}
	
	protected void newRow()
	{
		m_gbc.gridx = 0;
		m_gbc.gridy++;
	}
}
