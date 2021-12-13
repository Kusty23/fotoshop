package fotoshop.filters;

import java.awt.image.BufferedImage;
import java.util.Random;

import fotoshop.BlendingModes;

public class NoiseFilter extends Filter
{
	public static final int MONO = 0;
	public static final int MULTI = 1;

	public NoiseFilter() 
	{
		super();
	}

	public static BufferedImage applyFilter(BufferedImage img, int strength, int mode) 
	{
		BufferedImage image = Filter.deepCopy(img);
		
		if (mode == NoiseFilter.MONO)
			image = monochromatic(image, strength);
		else if (mode == NoiseFilter.MULTI)
			image = multichromatic(image, strength);
		
		return image;
	}
	
	private static BufferedImage monochromatic(BufferedImage image, int strength)
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

				int delta = (int) (random.nextDouble() * strength);

				delta -= strength / 2;

				r += delta;
				g += delta;
				b += delta;

				p = BlendingModes.packColor(a, r, g, b);

				image.setRGB(x, y, p);
			}
		}
		
		return image;
	}
	
	private static BufferedImage multichromatic(BufferedImage image, int strength)
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

				int deltaR = (int) (random.nextDouble() * strength);
				int deltaG = (int) (random.nextDouble() * strength);
				int deltaB = (int) (random.nextDouble() * strength);
				
				deltaR -= strength / 2;
				deltaG -= strength / 2;
				deltaB -= strength / 2;
				
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
