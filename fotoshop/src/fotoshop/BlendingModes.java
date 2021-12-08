package fotoshop;

import java.util.Random;

public class BlendingModes 
{
	public final static int NORMAL = 0;
	public final static int DISSOLVE = 1;

	public final static int MULTIPLY = 2;
	public final static int SCREEN = 3;
	public final static int COLOR_BURN = 4;
	public final static int COLOR_DODGE = 5;

	public final static int OVERWRITE = 6;

	private static Random m_random;

	/*
	 * Image A is the above image
	 * Image B is the lower image
	 */
	private static int pixelB, pixelA;

	private static int m_rImgB, m_gImgB, m_bImgB, m_aImgB;
	private static int m_rImgA, m_gImgA, m_bImgA, m_aImgA;

	private static double rb, gb, bb, ab;
	private static double ra, ga, ba, aa;

	private static double cra, cga, cba;
	private static double crb, cgb, cbb;

	public static int combinePixel(int imgA, int imgB, int mode, double alphaA)
	{
		pixelA = imgA;
		pixelB = imgB;

		if (m_random == null)
			m_random = new Random();

		loadRGBA();
		
		m_aImgA *= alphaA;
		
		convertToDoubles();

		switch(mode)
		{
		case OVERWRITE: return overwrite();
		case NORMAL: return normal();
		case DISSOLVE: return dissolve();
		case MULTIPLY: return multiply();
		case SCREEN: return screen();
		case COLOR_BURN: return colorBurn();
		case COLOR_DODGE: return colorDodge();

		default: return normal();
		}
	}

	private static int normal()
	{		
		double a_comp = aa + (ab * (1.0 - aa));

		preMultiply();
		
		double r_comp = (cra + (crb * (1.0 - aa))) / a_comp;
		double g_comp = (cga + (cgb * (1.0 - aa))) / a_comp;
		double b_comp = (cba + (cbb * (1.0 - aa))) / a_comp;

		return packColor(a_comp, r_comp, g_comp, b_comp);
	}

	private static int dissolve()
	{
		double x = m_random.nextDouble();

		if (x < aa)
			return pixelA;
		else
			return pixelB;
	}

	private static int multiply()
	{
		if (aa == 0)
		{
			return pixelB;
		}

		double a_comp = aa + (ab * (1.0 - aa));

		double r_comp = ra * rb;
		double g_comp = ga * gb;
		double b_comp = ba * bb;

		return packColor(a_comp, r_comp, g_comp, b_comp);
	}

	private static int screen()
	{
		double a_comp = aa + (ab * (1.0 - aa));

		double r_comp = 1 - (1 - ra) * (1 - rb);
		double g_comp = 1 - (1 - ga) * (1 - gb);
		double b_comp = 1 - (1 - ba) * (1 - bb);

		return packColor(a_comp, r_comp, g_comp, b_comp);
	}

	private static int colorBurn()
	{
		if (aa == 0)
		{
			return pixelB;
		}
		if (ab == 0)
		{
			return pixelA;
		}
		
		double a_comp = aa + (ab * (1.0 - aa));
		
		double r_comp = 1 - ((1.0 - rb) / ra);
		double g_comp = 1 - ((1.0 - gb) / ga);
		double b_comp = 1 - ((1.0 - bb) / ba);

		return packColor(a_comp, r_comp, g_comp, b_comp);
	}
	
	private static int colorDodge()
	{
		if (aa == 0)
		{
			return pixelB;
		}
		if (ab == 0)
		{
			return pixelA;
		}
		
		double a_comp = aa + (ab * (1.0 - aa));
		
		double r_comp = rb / (1.0 - ra);
		double g_comp = gb / (1.0 - ga);
		double b_comp = bb / (1.0 - ba);

		return packColor(a_comp, r_comp, g_comp, b_comp);
	}
	
	private static int overwrite()
	{
		return packColor(m_aImgA, m_rImgA, m_gImgA, m_bImgA);
	}

	private static void loadRGBA()
	{
		m_aImgB = (pixelB>>24) & 0xff;
		m_rImgB = (pixelB>>16) & 0xff;
		m_gImgB = (pixelB>>8) & 0xff;
		m_bImgB = pixelB & 0xff;

		m_aImgA = (pixelA>>24) & 0xff;
		m_rImgA = (pixelA>>16) & 0xff;
		m_gImgA = (pixelA>>8) & 0xff;
		m_bImgA = pixelA & 0xff;
	}

	private static void convertToDoubles()
	{
		ab = m_aImgB / 255.0;
		rb = m_rImgB / 255.0;
		gb = m_gImgB / 255.0;
		bb = m_bImgB / 255.0;

		aa = m_aImgA / 255.0;
		ra = m_rImgA / 255.0;
		ga = m_gImgA / 255.0;
		ba = m_bImgA / 255.0;
	}

	private static void preMultiply()
	{
		cra = ra * aa;
		cga = ga * aa;
		cba = ba * aa;

		crb = rb * ab;
		cgb = gb * ab;
		cbb = bb * ab;
	}

	public static int packColor(int a, int r, int g, int b)
	{
		a = clamp(a, 0, 255);
		r = clamp(r, 0, 255);
		g = clamp(g, 0, 255);
		b = clamp(b, 0, 255);
		
		int p = a << 24 | r << 16 | g << 8 | b;
		
		return p;
	}
	
	public static int packColor(double a, double r, double g, double b)
	{
		int aaa = (int) (a * 255);
		int rr = (int) (r * 255);
		int gg = (int) (g * 255);
		int bb = (int) (b * 255);
		
		return packColor(aaa, rr, gg, bb);
	}
	
	public static int clamp(int value, int min, int max) 
	{
        return Math.max(min, Math.min(max, value));
    }

}
