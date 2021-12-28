package app;

import java.awt.Graphics;
import KSwing.KPanel;
import fotoshop.Project;

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

		Project.getInstance().render(g);
		g.drawImage(Project.getInstance().getCanvas(), PADDING, PADDING, null);
	}
}
