package fotoshop;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import app.MainFrame;

public class Project 
{
	String m_name;
	int m_width, m_height;
	
	public ArrayList<Layer> m_layers;
	public BufferedImage m_canvas;
	
	Layer layer1;
	
	public Project(String name, int width, int height)
	{
		this.m_name = name;
		this.m_width = width;
		this.m_height = height;
		
		this.m_canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		this.m_layers = new ArrayList<Layer>();
	}
	
	public void newLayer()
	{
		Layer newLayer = new Layer("New Layer");
		
		m_layers.add(newLayer);
		
		MainFrame.getInstance().m_vp.repaint();
	}
	
	public void importLayer(File file)
	{
		try 
		{
			BufferedImage image = ImageIO.read(file);
			
			Layer newLayer = new Layer("New Layer", image);
			MainFrame.getInstance().m_vp.loadImage(file);
			this.m_layers.add(newLayer);
			
			System.out.println("Loaded " + file.getAbsolutePath());
		} 
		catch (IOException e) 
		{
			System.out.println("Couldn't load " + file.getAbsolutePath());
		}
	}
	
	public void drawTransparencyGrid(Graphics g)
	{
		int buffer = 10;
		
		int x = buffer;
		int checkSize = 5;
		boolean color = true;
		boolean startColor = true;
		while (x < this.m_width)
		{
			int y = buffer;
			
			startColor = !startColor;
			color = startColor;
			
			while (y < this.m_height)
			{
				g.setColor( color ? Color.WHITE : Color.LIGHT_GRAY);
				g.fillRect(x, y, checkSize, checkSize);
				y += checkSize;
				color = !color;
			}
			x += checkSize;
		}
	}

	public void render(Graphics g)
	{
		drawTransparencyGrid(g);
		
		System.out.println("RENDERING");
		
		for (Layer layer : this.m_layers)
		{
			layer.drawToCanvas();
		}
	}
}
