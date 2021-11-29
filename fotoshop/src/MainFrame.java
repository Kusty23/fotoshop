import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	ToolbarPanel m_tp;
	ViewPanel m_vp;
	
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
		
		this.setVisible(true);
	}
	
	public void sendImageFileToViewPanel(File file)
	{
		m_vp.loadImage(file);
	}
}
