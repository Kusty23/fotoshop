package app;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import fotoshop.Project;

public class ViewPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public ViewPanel()
	{
		this.setBackground(new Color(212,212,212));

		this.setVisible(true);
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
