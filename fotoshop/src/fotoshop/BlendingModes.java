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
	private static int m_imgB, m_imgA;

	private static int m_rImgB, m_gImgB, m_bImgB, m_aImgB;
	private static int m_rImgA, m_gImgA, m_bImgA, m_aImgA;

	private static double m_rImgBD, m_gImgBD, m_bImgBD, m_aImgBD;
	private static double m_rImgAD, m_gImgAD, m_bImgAD, m_aImgAD;

	private static double cra, cga, cba;
	private static double crb, cgb, cbb;

	public static int combinePixel(int imgA, int imgB, int mode, double alphaA)
	{
		m_imgA = imgA;
		m_imgB = imgB;

		if (m_random == null)
			m_random = new Random();

		loadRGBA();
		
		m_aImgA *= alphaA;
		
		convertToDoubles();
		preMultiply();

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
		double a_comp = m_aImgAD + (m_aImgBD * (1.0 - m_aImgAD));

		double r_comp = (cra + (crb * (1.0 - m_aImgAD))) / a_comp;
		double g_comp = (cga + (cgb * (1.0 - m_aImgAD))) / a_comp;
		double b_comp = (cba + (cbb * (1.0 - m_aImgAD))) / a_comp;

		int a = (int) (a_comp * 255);
		int r = (int) (r_comp * 255);
		int g = (int) (g_comp * 255);
		int b = (int) (b_comp * 255);

		int p = packColor(a, r, g, b);

		return p;
	}

	private static int dissolve()
	{
		double x = m_random.nextDouble();

		if (x < m_aImgAD)
			return m_imgA;
		else
			return m_imgB;
	}

	private static int multiply()
	{
		if (m_aImgAD == 0)
		{
			return m_imgB;
		}

		double a_comp = m_aImgAD + (m_aImgBD * (1.0 - m_aImgAD));

		double r_comp = m_rImgAD * m_rImgBD;
		double g_comp = m_gImgAD * m_gImgBD;
		double b_comp = m_bImgAD * m_bImgBD;

		int a = (int) (a_comp * 255);
		int r = (int) (r_comp * 255);
		int g = (int) (g_comp * 255);
		int b = (int) (b_comp * 255);

		int p = packColor(a, r, g, b);

		return p;
	}

	private static int screen()
	{
		double a_comp = m_aImgAD + (m_aImgBD * (1.0 - m_aImgAD));

		double r_comp = 1 - (1 - m_rImgAD) * (1 - m_rImgBD);
		double g_comp = 1 - (1 - m_gImgAD) * (1 - m_gImgBD);
		double b_comp = 1 - (1 - m_bImgAD) * (1 - m_bImgBD);

		int a = (int) (a_comp * 255);
		int r = (int) (r_comp * 255);
		int g = (int) (g_comp * 255);
		int b = (int) (b_comp * 255);

		int p = a << 24 | r << 16 | g << 8 | b;

		return p;
	}

	private static int colorBurn()
	{
		if (m_aImgAD == 0)
		{
			return m_imgB;
		}
		if (m_aImgBD == 0)
		{
			return m_imgA;
		}
		
		double a_comp = m_aImgAD + (m_aImgBD * (1.0 - m_aImgAD));
		
		double r_comp = 1 - ((1.0 - m_rImgBD) / m_rImgAD);
		double g_comp = 1 - ((1.0 - m_gImgBD) / m_gImgAD);
		double b_comp = 1 - ((1.0 - m_bImgBD) / m_bImgAD);

		return packColor(a_comp, r_comp, g_comp, b_comp);
	}
	
	private static int colorDodge()
	{
		if (m_aImgAD == 0)
		{
			return m_imgB;
		}
		if (m_aImgBD == 0)
		{
			return m_imgA;
		}
		
		double a_comp = m_aImgAD + (m_aImgBD * (1.0 - m_aImgAD));
		
		double r_comp = m_rImgBD / (1.0 - m_rImgAD);
		double g_comp = m_gImgBD / (1.0 - m_gImgAD);
		double b_comp = m_bImgBD / (1.0 - m_bImgAD);

		return packColor(a_comp, r_comp, g_comp, b_comp);
	}
	
	private static int overwrite()
	{
		return packColor(m_aImgA, m_rImgA, m_gImgA, m_bImgA);
	}

	private static void loadRGBA()
	{
		m_aImgB = (m_imgB>>24) & 0xff;
		m_rImgB = (m_imgB>>16) & 0xff;
		m_gImgB = (m_imgB>>8) & 0xff;
		m_bImgB = m_imgB & 0xff;

		m_aImgA = (m_imgA>>24) & 0xff;
		m_rImgA = (m_imgA>>16) & 0xff;
		m_gImgA = (m_imgA>>8) & 0xff;
		m_bImgA = m_imgA & 0xff;
	}

	private static void convertToDoubles()
	{
		m_aImgBD = m_aImgB / 255.0;
		m_rImgBD = m_rImgB / 255.0;
		m_gImgBD = m_gImgB / 255.0;
		m_bImgBD = m_bImgB / 255.0;

		m_aImgAD = m_aImgA / 255.0;
		m_rImgAD = m_rImgA / 255.0;
		m_gImgAD = m_gImgA / 255.0;
		m_bImgAD = m_bImgA / 255.0;
	}

	private static void preMultiply()
	{
		cra = m_rImgAD * m_aImgAD;
		cga = m_gImgAD * m_aImgAD;
		cba = m_bImgAD * m_aImgAD;

		crb = m_rImgBD * m_aImgBD;
		cgb = m_gImgBD * m_aImgBD;
		cbb = m_bImgBD * m_aImgBD;
	}

	public static int packColor(int a, int r, int g, int b)
	{
		int p = a << 24 | r << 16 | g << 8 | b;
		return p;
	}
	
	public static int packColor(double a, double r, double g, double b)
	{
		int aa = clamp((int) (a * 255), 0, 255);
		int rr = clamp((int) (r * 255), 0, 255);
		int gg = clamp((int) (g * 255), 0, 255);
		int bb = clamp((int) (b * 255), 0, 255);			
		
		return packColor(aa, rr, gg, bb);
	}
	
	public static int clamp(int value, int min, int max) 
	{
        return Math.max(min, Math.min(max, value));
    }

}
