package app;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import fotoshop.Project;

public class ViewPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static ViewPanel m_viewPanelInstance;

	public ViewPanel()
	{
		this.setBackground(new Color(212,212,212));

		this.setVisible(true);
	}

	public static ViewPanel getInstance()
	{
		if (m_viewPanelInstance == null)
			m_viewPanelInstance = new ViewPanel();
		
		return ViewPanel.m_viewPanelInstance;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Project p = MainFrame.getProject();

		if (p != null)
		{		
			p.render(g);
			g.drawImage(p.getCanvas(), 10, 10, null);
		}
	}
}
