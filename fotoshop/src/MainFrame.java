import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	ToolbarPanel m_tp;
	ViewPanel m_vp;
	InfoPanel m_ip;
	
	Project m_project;
	
	public MainFrame()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(1000, 700));
		
		m_tp = new ToolbarPanel(this);
		this.add(m_tp, BorderLayout.PAGE_START);
		
		m_vp = new ViewPanel(this);
		this.add(m_vp, BorderLayout.CENTER);
		
		m_ip = new InfoPanel(this);
		this.add(m_ip, BorderLayout.LINE_END);
		
		this.m_project = new Project(this, "Demo", 400, 400);
		
		this.setVisible(true);
	}
	
	public void sendImageFileToViewPanel(File file)
	{
		m_vp.loadImage(file);
	}
	
	public void createNewProject()
	{
		//NewProjectDialog npd = new NewProjectDialog(this);
		
		this.m_project = new Project(this, "New Project", this.m_vp.getWidth() - 20, this.m_vp.getHeight() - 20);
		this.m_vp.repaint();
	}
}
