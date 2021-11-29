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
		
		// If there is no displayed image we can stop here
		if (this.m_displayedImage == null)
			return;		
		
		// Get the aspect ratio
		double aspect = (double) this.m_displayedImage.getWidth() / (double) this.m_displayedImage.getHeight();
		
		// First we use the window width to calculate the displayed size
		int displayWidth = this.getWidth() - 20;
		int displayHeight = (int) ((double) displayWidth / aspect);
		
		// If this makes the image too tall, use the window height instead
		if (displayHeight > this.getHeight() - 20)
		{
			displayHeight = this.getHeight() - 20;
			displayWidth = (int) (displayHeight * aspect);
		}
		
		// Draw transparency checkerboard
		int x = 10;
		int checkSize = 5;
		boolean color = true;
		while (x < displayWidth + checkSize * 3)
		{
			int y = 10;
			//color = !color;
			while (y < displayHeight + checkSize)
			{
				g.setColor( color ? Color.WHITE : Color.LIGHT_GRAY);
				g.fillRect(x, y, checkSize, checkSize);
				y += checkSize;
				color = !color;
			}
			x += checkSize;
		}
		
		System.out.println("Drawing image");
		
		g.drawImage(m_displayedImage, 10, 10, displayWidth, displayHeight, null);
	}
}
