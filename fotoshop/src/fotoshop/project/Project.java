package fotoshop.project;
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
import fotoshop.Layer;

public class Project 
{
	private static Project m_projectInstance;
	
	private String m_name;
	private Dimension m_dimension;
	
	private ArrayList<Layer> m_layers;
	private Layer m_currentLayer;
	
	private BufferedImage m_canvas;
	
	public int BRUSH_COLOR = 0;
	
	private Project()
	{
		this.m_name = "New Project";
		this.m_dimension = new Dimension(ViewPanel.getInstance().getWidth() - 100, ViewPanel.getInstance().getHeight());
		
		this.m_canvas = new BufferedImage(m_dimension.width, m_dimension.height, BufferedImage.TYPE_INT_ARGB);
		
		this.m_layers = new ArrayList<Layer>();
		this.m_currentLayer = null;
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
	
	public Layer getCurrentLayer()
	{
		return this.m_currentLayer;
	}
	
	public void setCurrentLayer(Layer layer)
	{
		this.m_currentLayer = layer;
	}
	
	public static void createNewProject(String name, Dimension dimension)
	{
		m_projectInstance = new Project();
		
		m_projectInstance.setName(name);
		m_projectInstance.setDimension(dimension);
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
		m_currentLayer = newLayer;
		
		MainFrame.getInstance().repaint();
	}
	
	public void importLayer(File file)
	{
		try 
		{
			BufferedImage image = ImageIO.read(file);
			
			Layer newLayer = new Layer("New Layer", image);
			
			this.m_layers.add(newLayer);
			m_currentLayer = newLayer;
			
			MainFrame.getInstance().repaint();
		} 
		catch (IOException e) 
		{
			System.out.println("Error while loading " + file.getAbsolutePath());
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
