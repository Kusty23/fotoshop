package app;
import java.awt.BorderLayout;
import java.awt.Dimension;

import KSwing.containers.KFrame;
import KSwing.containers.KInternalFrame;
import fotoshop.Project;
import fotoshop.toolbar.ToolBar;

public class MainFrame extends KFrame
{
	private static final long serialVersionUID = 1L;

	private static MainFrame m_mainFrameInstance;

	private MainFrame()
	{
		super();

		createNewProject();
	}

	@Override
	public void initialize() 
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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

	public void createNewProject()
	{
		//NewProjectDialog npd = new NewProjectDialog(this);
		Project.createNewProject();
		
		InfoPanel.getInstance().initProjectPropertiesPanel();
		ViewPanel.getInstance().repaint();
	}
}
