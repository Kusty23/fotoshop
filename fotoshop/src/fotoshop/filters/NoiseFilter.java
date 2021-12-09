package fotoshop.filters;

import java.awt.image.BufferedImage;
import java.util.Random;

import fotoshop.BlendingModes;

public class NoiseFilter extends Filter
{
	public static final int MONO = 0;
	public static final int MULTI = 1;

	private int m_strength;
	private int m_mode;

	public NoiseFilter() 
	{
		super();
	}

	public NoiseFilter(int strength, int mode)
	{
		super();

		this.m_strength = strength;
		this.m_mode = mode;
	}

	@Override
	public BufferedImage applyFilter(BufferedImage img) 
	{
		BufferedImage image = Filter.deepCopy(img);
		
		if (m_mode == NoiseFilter.MONO)
			image = monochromatic(image);
		else if (m_mode == NoiseFilter.MULTI)
			image = multichromatic(image);
		
		return image;
	}
	
	private BufferedImage monochromatic(BufferedImage image)
	{
		Random random = new Random(SEED);

		for (int x=0; x<image.getWidth(); x++)
		{
			for (int y=0; y<image.getHeight(); y++)
			{
				int p = image.getRGB(x, y);

				int a = (p>>24) & 0xff;
				int r = (p>>16) & 0xff;
				int g = (p>>8) & 0xff;
				int b = p & 0xff;

				int delta = (int) (random.nextDouble() * m_strength);

				delta -= m_strength / 2;

				r += delta;
				g += delta;
				b += delta;

				p = BlendingModes.packColor(a, r, g, b);

				image.setRGB(x, y, p);
			}
		}
		
		return image;
	}
	
	private BufferedImage multichromatic(BufferedImage image)
	{
		Random random = new Random(SEED);

		for (int x=0; x<image.getWidth(); x++)
		{
			for (int y=0; y<image.getHeight(); y++)
			{
				int p = image.getRGB(x, y);

				int a = (p>>24) & 0xff;
				int r = (p>>16) & 0xff;
				int g = (p>>8) & 0xff;
				int b = p & 0xff;

				int deltaR = (int) (random.nextDouble() * m_strength);
				int deltaG = (int) (random.nextDouble() * m_strength);
				int deltaB = (int) (random.nextDouble() * m_strength);
				
				deltaR -= m_strength / 2;
				deltaG -= m_strength / 2;
				deltaB -= m_strength / 2;
				
				r += deltaR;
				g += deltaG;
				b += deltaB;

				p = BlendingModes.packColor(a, r, g, b);

				image.setRGB(x, y, p);
			}
		}
		
		return image;
	}
}
