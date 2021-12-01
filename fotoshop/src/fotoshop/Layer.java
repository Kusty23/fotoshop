package fotoshop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import app.MainFrame;

public class Layer 
{
	private String m_name;
	
	private BufferedImage m_image;
	private Dimension m_Dimension;
	private int m_offsetX, m_offsetY;
	
	public Layer(String name)
	{
		this.m_name = name;
		
		this.m_Dimension = new Dimension(MainFrame.getProject().getDimension().width, MainFrame.getProject().getDimension().height);
		
		this.m_offsetX = 0;
		this.m_offsetY = 0;
	}
	
	public Layer(String name, BufferedImage image)
	{
		this.m_name = name;
		this.m_image = image;
		
		this.m_Dimension = new Dimension(image.getWidth(), image.getHeight());
		
		this.m_offsetX = 0;
		this.m_offsetY = 0;
	}
	
	public void drawToCanvas()
	{
		System.out.println("Rendering layer");
		
		BufferedImage canvas = MainFrame.getProject().getCanvas();
		
		int x = this.m_offsetX;
		
		while (x < this.m_Dimension.width && x < canvas.getWidth())
		{
			int y = this.m_offsetY;
			
			while (y < this.m_Dimension.height && y < canvas.getHeight())
			{
				canvas.setRGB(x, y, m_image.getRGB(x, y));
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
	
	public void setImage(BufferedImage image)
	{
		this.m_image = image;
	}
}
