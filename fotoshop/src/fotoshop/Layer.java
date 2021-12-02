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
	private Dimension m_dimension;
	private Dimension m_offset;
	
	public Layer(String name)
	{
		this.m_name = name;
		this.m_id = Layer.m_nextID;
		Layer.m_nextID++;
		
		this.m_dimension = new Dimension(MainFrame.getProject().getDimension().width, MainFrame.getProject().getDimension().height);
		
		this.m_offset = new Dimension(0,0);
		
		initLayer();
	}
	
	public Layer(String name, BufferedImage image)
	{
		this(name);
		
		this.m_image = image;
		
		this.m_dimension = new Dimension(image.getWidth(), image.getHeight());
	}
	
	private void initLayer()
	{
		InfoPanel.getInstance().addLayer(this);
	}
	
	public void drawToCanvas()
	{
		if (m_image == null)
			return;
		
		System.out.println("Rendering layer");
		
		BufferedImage canvas = MainFrame.getProject().getCanvas();
		
		int x = 0;
		
		while (x < this.m_dimension.width && x + m_offset.width < canvas.getWidth())
		{
			if (x + m_offset.width < 0)
			{
				x++;
				continue;
			}
			
			int y = 0;
			
			while (y < this.m_dimension.height && y + m_offset.height < canvas.getHeight())
			{
				if (y + m_offset.height < 0)
				{
					y++;
					continue;
				}
				
				canvas.setRGB(x + m_offset.width, y + m_offset.height, m_image.getRGB(x, y));
				y++;
			}
			x++;
		}
	}
	
	public Dimension getDimension()
	{
		return this.m_dimension;
	}
	
	public void setDimension(Dimension dimension)
	{
		this.m_dimension = dimension;
	}
	
	public Dimension getOffset()
	{
		return this.m_offset;
	}
	
	public void setOffset(Dimension offset)
	{
		this.m_offset = offset;
	}
	
	public BufferedImage getImage()
	{
		return this.m_image;
	}
	
	public String getName()
	{
		return this.m_name;
	}
	
	public void setName(String name)
	{
		this.m_name = name;
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
