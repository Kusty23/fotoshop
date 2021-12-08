package fotoshop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import app.InfoPanel;
import app.ViewPanel;

public class Layer 
{
	private static int m_nextID;

	private String m_name;
	private int m_id;

	private BufferedImage m_original, m_image;

	private Dimension m_dimension;
	private double m_originalAspect;

	private Dimension m_offset;
	
	private double m_opacity;
	
	private int m_blendMode;

	public Layer(String name)
	{
		this.m_name = name;
		this.m_id = Layer.m_nextID;
		Layer.m_nextID++;

		this.m_original = new BufferedImage(Project.getInstance().getDimension().width, Project.getInstance().getDimension().height, BufferedImage.TYPE_INT_ARGB);
		this.m_image = m_original;
		
		this.m_dimension = new Dimension(Project.getInstance().getDimension().width, Project.getInstance().getDimension().height);
		this.m_originalAspect = m_dimension.getWidth() / m_dimension.getHeight();

		this.m_offset = new Dimension(0,0);
		
		this.m_opacity = 1.0;
		
		this.m_blendMode = BlendingModes.NORMAL;

		initLayer();
	}

	public Layer(String name, BufferedImage image)
	{
		this.m_name = name;
		this.m_id = Layer.m_nextID;
		Layer.m_nextID++;
		
		this.m_original = image;
		this.m_image = image;

		this.m_blendMode = BlendingModes.NORMAL;
		
		this.m_offset = new Dimension(0,0);
		
		this.m_opacity = 1.0;
		
		this.m_dimension = new Dimension(image.getWidth(), image.getHeight());
		this.m_originalAspect = m_dimension.getWidth() / m_dimension.getHeight();
		
		initLayer();
	}

	private void initLayer()
	{
		InfoPanel.getInstance().addLayer(this);
	}

	public void drawToCanvas()
	{
		if (m_image == null)
			return;

		BufferedImage canvas = Project.getInstance().getCanvas();

		int x = 0;
		int xO = x + m_offset.width;

		while (x < this.m_dimension.width && xO < canvas.getWidth())
		{
			if (xO < 0)
			{
				x++;
				xO++;
				continue;
			}

			int y = 0;
			int yO = y + m_offset.height;

			while (y < this.m_dimension.height && yO < canvas.getHeight())
			{
				if (yO < 0)
				{
					y++;
					yO++;
					continue;
				}

				int canvasRGB = canvas.getRGB(xO, yO);
				int imageRGB = m_image.getRGB(x, y);

				int compositeRGB;

				if (m_id != 0)
				compositeRGB = BlendingModes.combinePixel(imageRGB, canvasRGB, m_blendMode, m_opacity);
				else
					compositeRGB = BlendingModes.combinePixel(imageRGB, canvasRGB, BlendingModes.NORMAL, m_opacity);

				canvas.setRGB(xO, yO, compositeRGB);
				
				y++;
				yO++;
			}
			
			x++;
			xO++;
		}
	}

	private void resizeImage()
	{
		BufferedImage resizedImage = new BufferedImage(m_dimension.width, m_dimension.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();

		float xScale = (float)  m_dimension.width / m_original.getWidth();
		float yScale = (float) m_dimension.height / m_original.getHeight();

		AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
		g.drawRenderedImage(m_original, at);
		g.dispose();

		m_image = resizedImage;
	}

	public void brushAt(int x, int y)
	{
		int radius = 100;
		
		for (int i=-radius; i<radius; i++)
		{
			for (int j=-radius; j<radius;j++)
			{
				if (Math.pow(i, 2) + Math.pow(j, 2) < radius)
				{
					int x0 = x - i - ViewPanel.PADDING - m_offset.width;
					int y0 = y - j - ViewPanel.PADDING - m_offset.height;
					
					if (x0 < 0 || x0 >= m_image.getWidth())
						continue;
					
					if (y0 < 0 || y0 >= m_image.getHeight())
						continue;
					
					m_image.setRGB(x0, y0, Project.getInstance().BRUSH_COLOR);
				}
			}
		}
		
		ViewPanel.getInstance().repaint();
	}
	
	public Dimension getDimension()
	{
		return this.m_dimension;
	}

	public void setDimension(Dimension dimension)
	{
		this.m_dimension = dimension;

		resizeImage();
	}

	public double getOriginalAspect()
	{
		return this.m_originalAspect;
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
	
	public double getOpacity()
	{
		return this.m_opacity;
	}
	
	public void setOpacity(double opacity)
	{
		this.m_opacity = opacity;
	}
	
	public int getBlendMode()
	{
		return this.m_blendMode;
	}
	
	public void setBlendMode(int mode)
	{
		this.m_blendMode = mode;
	}
}
