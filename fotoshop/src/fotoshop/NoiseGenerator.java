package fotoshop;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NoiseGenerator 
{
	public static void addMonochromaticNoise(BufferedImage input, int strength)
	{
		Random random = new Random(1234);
		
		for (int x=0; x<input.getWidth(); x++)
		{
			for (int y=0; y<input.getHeight(); y++)
			{
				int p = input.getRGB(x, y);
				
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
				
				input.setRGB(x, y, p);
			}
		}
	}
	
	public static void addMulticoloredNoise(BufferedImage input, int strength)
	{
		Random random = new Random(1234);
		
		for (int x=0; x<input.getWidth(); x++)
		{
			for (int y=0; y<input.getHeight(); y++)
			{
				int p = input.getRGB(x, y);
				
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
				
				input.setRGB(x, y, p);
			}
		}
	}
}
