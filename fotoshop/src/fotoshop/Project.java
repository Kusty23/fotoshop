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
import app.ViewPanel;

public class Project 
{
	private static Project m_projectInstance;
	
	private String m_name;
	private Dimension m_dimension;
	
	private ArrayList<Layer> m_layers;
	private BufferedImage m_canvas;
	
	public int BRUSH_COLOR = 0;
	
	private Project()
	{
		this.m_name = "New Project";
		this.m_dimension = new Dimension(ViewPanel.getInstance().getWidth() - 100, ViewPanel.getInstance().getHeight());
		
		this.m_canvas = new BufferedImage(m_dimension.width, m_dimension.height, BufferedImage.TYPE_INT_ARGB);
		
		this.m_layers = new ArrayList<Layer>();
	}
	
	public static Project getInstance()
	{
		if (m_projectInstance == null)
			m_projectInstance = new Project();
		
		return m_projectInstance;
	}
	
	public BufferedImage getCanvas()
	{
		return this.m_canvas;
	}
	
	public Dimension getDimension() 
	{
		return this.m_dimension;
	}
	
	public void setDimension(Dimension dimension)
	{
		this.m_dimension = dimension;
		
		this.m_canvas = new BufferedImage(m_dimension.width, m_dimension.height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public String getName()
	{
		return this.m_name;
	}
	
	public void setName(String name)
	{
		this.m_name = name;
	}
	
	public static void createNewProject()
	{
		m_projectInstance = new Project();
	}
	
	public Layer getLayerFromID(int id)
	{
		for (Layer layer : m_layers)
		{
			if (layer.getID() == id)
				return layer;
		}
		
		return null;
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
	
	private void drawWhiteBackground(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(10, 10, m_dimension.width, m_dimension.height);
	}
	
	private void drawFrameOfReference(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.drawRect(10, 10, m_dimension.width, m_dimension.height);
	}

	public void render(Graphics g)
	{
		drawWhiteBackground(g);
		
		m_canvas = new BufferedImage(m_canvas.getWidth(), m_canvas.getHeight(), m_canvas.getType());
		
		for (Layer layer : this.m_layers)
		{
			layer.drawToCanvas();
		}
		
		drawFrameOfReference(g);
	}
}
