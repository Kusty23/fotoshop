package app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import fotoshop.Project;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private static MainFrame m_mainFrameInstance;
	
	private static Project m_project;
	
	private MainFrame()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(1000, 700));
		
		this.add(ToolbarPanel.getInstance(), BorderLayout.PAGE_START);
		
		this.add(ViewPanel.getInstance(), BorderLayout.CENTER);
		
		this.add(InfoPanel.getInstance(), BorderLayout.LINE_END);
		
		this.setVisible(true);
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
		
		MainFrame.m_project = new Project("New Project", ViewPanel.getInstance().getWidth() - 20, ViewPanel.getInstance().getHeight() - 20);
		ViewPanel.getInstance().repaint();
	}
}
