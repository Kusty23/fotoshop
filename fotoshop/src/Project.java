import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Project 
{
	MainFrame m_mainFrame;
	
	String m_name;
	int m_width, m_height;
	
	ArrayList<Layer> m_layers;
	
	Layer layer1;
	
	public Project(MainFrame mf, String name, int width, int height)
	{
		this.m_mainFrame = mf;
		this.m_name = name;
		this.m_width = width;
		this.m_height = height;
		
		this.m_layers = new ArrayList<Layer>();
	}
	
	public void newLayer()
	{
		Layer newLayer = new Layer(this, "New Layer");
		
		m_layers.add(newLayer);
		
		System.out.println(m_layers);
		
		m_mainFrame.m_ip.add(new JLabel("layer"));
		
		this.m_mainFrame.validate();
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
		
		drawLayer(g);
	}

	public void drawLayer(Graphics g)
	{
		if (layer1 == null)
			return;
		
		g.drawImage(layer1.m_image, 10 + layer1.m_posx, 10 + layer1.m_posy, m_mainFrame);
	}
}
