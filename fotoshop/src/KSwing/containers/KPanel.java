package KSwing.containers;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import KSwing.KConstants;

public class KPanel extends JPanel implements KContainer
{
	private static final long serialVersionUID = 1L;

	private GridBagLayout m_gbl;
	private GridBagConstraints m_gbc;

	public KPanel()
	{
		// Set up look and feel
		this.setBackground(KConstants.BACKGROUND_COLOR);

		// Set layout manager
		m_gbl = new GridBagLayout();
		this.setLayout(m_gbl);

		// Set up grid bag constraints
		m_gbc = new GridBagConstraints();
		m_gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		m_gbc.insets = new Insets(2, 5, 2, 5);
		m_gbc.gridx = 0;
		m_gbc.gridy = 0;

		initialize();
		createComponents();
		arrangeComponents();

		this.setVisible(true);
	}

	@Override
	public void initialize() {}

	@Override
	public void createComponents() {}

	@Override
	public void arrangeComponents() {}

	public void addComponent(Component c, int gridwidth)
	{
		m_gbc.gridwidth = gridwidth;

		this.add(c, m_gbc);

		m_gbc.gridx++;
	}

	public void addComponentAt(Component c, int gridwidth, int gridx)
	{
		m_gbc.gridx = gridx;
		m_gbc.gridwidth = gridwidth;

		this.add(c, m_gbc);

		m_gbc.gridx++;
	}

	public void newRow()
	{
		m_gbc.gridx = 0;
		m_gbc.gridy++;
	}
}
