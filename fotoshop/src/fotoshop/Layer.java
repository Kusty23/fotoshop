package fotoshop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import app.InfoPanel;
import app.MainFrame;

public class Layer 
{
	private static int m_nextID;
	
	private String m_name;
	private int m_id;
	
	private BufferedImage m_image;
	private Dimension m_Dimension;
	private int m_offsetX, m_offsetY;
	
	public Layer(String name)
	{
		this.m_name = name;
		this.m_id = Layer.m_nextID;
		Layer.m_nextID++;
		
		this.m_Dimension = new Dimension(MainFrame.getProject().getDimension().width, MainFrame.getProject().getDimension().height);
		
		this.m_offsetX = 0;
		this.m_offsetY = 0;
	}
	
	public Layer(String name, BufferedImage image)
	{
		this.m_name = name;
		this.m_id = Layer.m_nextID;
		Layer.m_nextID++;
		
		this.m_image = image;
		
		this.m_Dimension = new Dimension(image.getWidth(), image.getHeight());
		
		this.m_offsetX = 0;
		this.m_offsetY = 0;
		
		initLayer();
	}
	
	private void initLayer()
	{
		InfoPanel.getInstance().addLayer(this);
	}
	
	public void drawToCanvas()
	{
		System.out.println("Rendering layer");
		
		BufferedImage canvas = MainFrame.getProject().getCanvas();
		
		int x = 0;
		
		while (x < this.m_Dimension.width && x + this.m_offsetX < canvas.getWidth())
		{
			int y = 0;
			
			while (y < this.m_Dimension.height && y + this.m_offsetY < canvas.getHeight())
			{
				canvas.setRGB(x + this.m_offsetX, y + this.m_offsetY, m_image.getRGB(x, y));
				y++;
			}
			x++;
		}
	}
	
	public Dimension getDimension()
	{
		return this.m_Dimension;
	}
	
	public BufferedImage getImage()
	{
		return this.m_image;
	}
	
	public String getName()
	{
		return this.m_name;
	}
	
	public int getID()
	{
		return this.m_id;
	}
	
	public void setImage(BufferedImage image)
	{
		this.m_image = image;
	}
}
