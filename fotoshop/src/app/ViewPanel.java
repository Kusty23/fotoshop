package app;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fotoshop.Layer;
import fotoshop.Project;

public class ViewPanel extends JPanel
{
	BufferedImage m_displayedImage;

	public ViewPanel()
	{
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

		// Draw transparency checkerboard
		Project p = MainFrame.getProject();

		if (p != null)
		{		
			MainFrame.getProject().render(g);
			g.drawImage(p.m_canvas, 10, 10, null);
		}
	}
}
