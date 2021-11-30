import java.awt.image.BufferedImage;

public class Layer 
{
	MainFrame m_mainFrame;
	
	String m_name;
	
	BufferedImage m_image;
	
	int m_width, m_height;
	int m_posx, m_posy;
	
	public Layer(Project project, String name)
	{
		this.m_name = name;
		
		this.m_width = project.m_width;
		this.m_height = project.m_height;
		
		this.m_posx = 0;
		this.m_posy = 0;
	}
	
	public Layer(MainFrame mf, String name, BufferedImage image)
	{
		this.m_mainFrame = mf;
		this.m_name = name;
		this.m_image = image;
		
		this.m_width = image.getWidth();
		this.m_height = image.getHeight();
		
		this.m_posx = 0;
		this.m_posy = 0;
	}
	
	public void setImage(BufferedImage image)
	{
		this.m_image = image;
	}
}
