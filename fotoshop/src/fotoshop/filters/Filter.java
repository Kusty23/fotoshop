package fotoshop.filters;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public abstract class Filter 
{
	protected final static int SEED = 0;
	
	public Filter()
	{
	}
	
	public abstract BufferedImage applyFilter(BufferedImage image);
	
	public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
