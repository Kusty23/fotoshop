package app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import KSwing.KFrame;
import fotoshop.Project;

public class MainFrame extends KFrame
{
	private static final long serialVersionUID = 1L;

	private static MainFrame m_mainFrameInstance;

	private static Project m_project;

	private MainFrame()
	{
		super();

		createNewProject();
	}

	@Override
	public void initialize() 
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);

		this.setSize(new Dimension(1000, 700));

		this.addKeyListener(new MainKeyListener());
	}

	@Override
	public void createComponents() 
	{
		return;
	}

	@Override
	public void arrangeComponents() 
	{
		this.setLayout(new BorderLayout());

		this.add(ToolBar.getInstance(), BorderLayout.PAGE_START);
		this.add(ViewPanel.getInstance(), BorderLayout.CENTER);	
		this.add(InfoPanel.getInstance(), BorderLayout.LINE_END);
	}

	public static MainFrame getInstance()
	{
		if (m_mainFrameInstance == null)
			m_mainFrameInstance = new MainFrame();

		return m_mainFrameInstance;
	}

	public static Project getProject()
	{
		return m_project;
	}

	public void createNewProject()
	{
		//NewProjectDialog npd = new NewProjectDialog(this);

		MainFrame.m_project = new Project("New Project", ViewPanel.getInstance().getWidth() - 200, ViewPanel.getInstance().getHeight() - 20);
		InfoPanel.getInstance().initProjectPropertiesPanel();
		ViewPanel.getInstance().repaint();
	}
}
