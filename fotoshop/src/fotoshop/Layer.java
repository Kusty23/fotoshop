package fotoshop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import app.InfoPanel;
import app.MainFrame;

public class Layer 
{
	private static int m_nextID;

	private String m_name;
	private int m_id;

	private BufferedImage m_original, m_image;

	private Dimension m_dimension;
	private double m_originalAspect;

	private Dimension m_offset;
	
	private int m_blendMode;

	public Layer(String name)
	{
		this.m_name = name;
		this.m_id = Layer.m_nextID;
		Layer.m_nextID++;

		this.m_dimension = new Dimension(MainFrame.getProject().getDimension().width, MainFrame.getProject().getDimension().height);

		this.m_offset = new Dimension(0,0);
		
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

				int canvasRGB = canvas.getRGB(x, y);
				int imageRGB = m_image.getRGB(x, y);

				int compositeRGB;

				if (m_id != 0)
				compositeRGB = BlendingModes.combine(canvasRGB, imageRGB, m_blendMode);
				else
					compositeRGB = BlendingModes.combine(canvasRGB, imageRGB, BlendingModes.NORMAL);

				canvas.setRGB(x + m_offset.width, y + m_offset.height, compositeRGB);
				y++;
			}
			x++;
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
	
	public int getBlendMode()
	{
		return this.m_blendMode;
	}
	
	public void setBlendMode(int mode)
	{
		this.m_blendMode = mode;
	}
}
