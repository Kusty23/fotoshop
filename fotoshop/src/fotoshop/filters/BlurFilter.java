package fotoshop.filters;

import java.awt.image.BufferedImage;

import fotoshop.BlendingModes;

public class BlurFilter extends Filter
{
	public static BufferedImage applyFilter(BufferedImage img, int radius) 
	{
		BufferedImage image = Filter.deepCopy(img);

		for (int x=0; x<image.getWidth(); x++)
		{
			for (int y=0; y<image.getHeight(); y++)
			{
				int a0 = 0, r0 = 0, g0 = 0, b0 = 0;
				int count = 0;
				
				for (int i=-radius; i<radius; i++)
				{
					for (int j=-radius; j<radius; j++)
					{
						if (i + x >= 0 && i + x < img.getWidth() && j + y >= 0 && j + y < img.getHeight())
						{
							count++;
							
							int p0 = img.getRGB(i + x, j + y);
							
							a0 += (p0>>24) & 0xff;
							r0 += (p0>>16) & 0xff;
							g0 += (p0>>8) & 0xff;
							b0 += p0 & 0xff;
						}
					}
				}
				
				// Average the values
				int a = a0 / count;
				int r = r0 / count;
				int g = g0 / count;
				int b = b0 / count;
				
				int p = BlendingModes.packColor(a, r, g, b);
				
				image.setRGB(x, y, p);
			}
		}

		return image;
	}

}
