package fotoshop;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import app.MainFrame;

public class Project 
{
	private String m_name;
	private Dimension m_dimension;
	
	private ArrayList<Layer> m_layers;
	private BufferedImage m_canvas;
	
	public Project(String name, int width, int height)
	{
		this.m_name = name;
		this.m_dimension = new Dimension(width, height);
		
		this.m_canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		this.m_layers = new ArrayList<Layer>();
	}
	
	public BufferedImage getCanvas()
	{
		return this.m_canvas;
	}
	
	public Dimension getDimension() 
	{
		return m_dimension;
	}
	
	public void newLayer()
	{
		Layer newLayer = new Layer("New Layer");
		
		m_layers.add(newLayer);
		
		MainFrame.getInstance().repaint();
	}
	
	public void importLayer(File file)
	{
		try 
		{
			BufferedImage image = ImageIO.read(file);
			
			Layer newLayer = new Layer("New Layer", image);
			this.m_layers.add(newLayer);
			
			MainFrame.getInstance().repaint();
			
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
		while (x < this.getDimension().width)
		{
			int y = buffer;
			
			startColor = !startColor;
			color = startColor;
			
			while (y < this.getDimension().height)
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
