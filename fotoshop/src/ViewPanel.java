import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ViewPanel extends JPanel
{
	MainFrame m_mainFrame;
	BufferedImage m_displayedImage;
	
	public ViewPanel(MainFrame mf)
	{
		this.m_mainFrame = mf;
		
		this.setBackground(new Color(212,212,212));
		
		this.setVisible(true);
	}
	
	private void processImage()
	{
		// Do something
	}
	
	public void loadImage(File imageFile)
	{
		// Try to load the image
		try 
		{
			m_displayedImage = ImageIO.read(imageFile);
			
			this.m_mainFrame.m_project.layer1 = new Layer(this.m_mainFrame, "Layer", m_displayedImage);
			
			System.out.println("Loaded " + imageFile.getAbsolutePath());
		} 
		catch (IOException e) 
		{
			System.out.println("Couldn't load " + imageFile.getAbsolutePath());
		}
		
		this.repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Draw transparency checkerboard
		this.m_mainFrame.m_project.drawTransparencyGrid(g);
	}
}
