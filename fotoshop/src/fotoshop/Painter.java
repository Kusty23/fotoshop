package fotoshop;

import java.awt.Point;

import app.ViewPanel;
import fotoshop.project.Project;

public class Painter 
{
	public static void paintLine(Layer layer, Point a, Point b)
	{
		double distance = Point.distance(a.x, a.y, b.x, b.y);

		if (distance < 10)
		{
			brushAt(layer, a.x, a.y, Project.getInstance().getBrushRadius());
			return;
		}
		
		System.out.println(distance);
		
		double ax = a.x;
		double ay = a.y;
		double bx = b.x;
		double by = b.y;
				
		int x, y;
		
		int steps = 10;
		double delX = (ax - bx) / steps;
		double delY = (ay - by) / steps;
		
		if (ax < bx && delX >= 0)
		{
			x = (int) ax;
			y = (int) ay;
		}
		else
		{
			x = (int) bx;
			y = (int) by;
		}
	
		for (int i=0; i<steps; i++)
		{
			brushAt(layer, x, y, Project.getInstance().getBrushRadius());
			
			x += delX;
			y += delY;
		}
	}
	
	public static void brushAt(Layer layer, int x, int y, int brushRadius)
	{
		for (int i=-brushRadius; i<brushRadius; i++)
		{
			for (int j=-brushRadius; j<brushRadius;j++)
			{
				if (Math.pow(i, 2) + Math.pow(j, 2) < brushRadius)
				{
					int x0 = x - i - ViewPanel.PADDING;
					int y0 = y - j - ViewPanel.PADDING;

					if (x0 < 0 || x0 >= layer.getImage().getWidth())
						continue;

					if (y0 < 0 || y0 >= layer.getImage().getHeight())
						continue;
					
					layer.setRGB(x0, y0, Project.getInstance().BRUSH_COLOR);
				}
			}
		}
		
		ViewPanel.getInstance().repaint();
	}
}
