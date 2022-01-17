package app;

import java.awt.Graphics;
import java.awt.Point;

import KSwing.containers.KPanel;
import fotoshop.project.Project;

public class ViewPanel extends KPanel
{
	private static final long serialVersionUID = 1L;

	private static ViewPanel m_viewPanelInstance;

	public static final int PADDING = 10;

	public ViewPanel()
	{

	}

	@Override
	public void initialize() 
	{
		this.addMouseListener(ViewMouseListener.getInstance());
		this.addMouseMotionListener(ViewMouseListener.getInstance());
	}

	@Override
	public void createComponents() 
	{

	}

	@Override
	public void arrangeComponents() 
	{

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

		if (Project.getInstance() != null)
		{
			Project.getInstance().render(g);
			g.drawImage(Project.getInstance().getCanvas(), PADDING, PADDING, null);
		}
	}
	
	public static void drawCursor(Point p)
	{
		int radius = 10;
		
		MainFrame.getInstance().repaint();
		ViewPanel.getInstance().getGraphics().drawArc(p.x - radius, p.y - radius, radius * 2, radius * 2, 0, 360);
	}
}
