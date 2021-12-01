package app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import fotoshop.Project;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private ToolbarPanel m_tp;
	private ViewPanel m_vp;
	private InfoPanel m_ip;
	
	private static MainFrame m_mainFrameInstance;
	private static Project m_project;
	
	private MainFrame()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(1000, 700));
		
		m_tp = new ToolbarPanel();
		this.add(m_tp, BorderLayout.PAGE_START);
		
		m_vp = new ViewPanel();
		this.add(m_vp, BorderLayout.CENTER);
		
		m_ip = new InfoPanel();
		this.add(m_ip, BorderLayout.LINE_END);
		
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
		
		MainFrame.m_project = new Project("New Project", this.m_vp.getWidth() - 20, this.m_vp.getHeight() - 20);
		this.m_vp.repaint();
	}
}
