package fotoshop;

import java.awt.Point;

public class Painter 
{
	public static void paintLine(Layer layer, Point a, Point b)
	{
		double ax = a.x;
		double ay = a.y;
		double bx = b.x;
		double by = b.y;
		
		double slope = bx - ax != 0 ? (by - ay) / (bx - ax): 0;
		
		int x, y;
		
		if (a.x < b.x)
		{
			x = a.x;
			y = a.y;
			
			if (a.y < b.y)
			{
				while (x < b.x && y < b.y)
				{
					layer.brushAt(x, y);
					
					x += 1;
					y += slope;
				}
			}
			else
			{
				while (x < b.x && y > b.y)
				{
					layer.brushAt(x, y);
					
					x += 1;
					y += slope;
				}
			}
		}
		else
		{
			x = b.x;
			y = b.y;
			
			if (a.y < b.y)
			{
				while (x < a.x && y < a.y)
				{
					layer.brushAt(x, y);
					
					x += 1;
					y += slope;
				}
			}
			else
			{
				while (x < a.x && y > a.y)
				{
					layer.brushAt(x, y);
					
					x += 1;
					y += slope;
				}
			}
		}		
	}
}
