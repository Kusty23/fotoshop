package app;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fotoshop.BlendingModes;
import fotoshop.Layer;

public class ViewMouseListener implements MouseListener, MouseMotionListener
{	
	private int m_initialX, m_initialY;
	private int m_initialOffsetX, m_initialOffsetY;

	private static boolean m_mouseDown = false;

	private static int MOUSE_MODE;

	private static final int MOVE_MODE = 0;
	private static final int BRUSH_MODE = 1;

	public ViewMouseListener()
	{
		super();

		MOUSE_MODE = BRUSH_MODE;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("Click - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		m_mouseDown = true;
		
		if (MOUSE_MODE == MOVE_MODE)
		{
			m_initialX = e.getX();
			m_initialY = e.getY();

			Layer layer = InfoPanel.getInstance().getCurrentLayer();
			if (layer != null)
			{
				m_initialOffsetX = (int) layer.getOffset().getWidth();
				m_initialOffsetY = (int) layer.getOffset().getHeight();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		m_mouseDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		//System.out.println("Enter - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		//System.out.println("Exit - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if (MOUSE_MODE == MOVE_MODE)
		{
			int m_draggedX = e.getX();
			int m_draggedY = e.getY();

			Layer layer = InfoPanel.getInstance().getCurrentLayer();
			if (layer != null)
			{		
				int delX = m_draggedX - m_initialX;			
				int delY = m_draggedY - m_initialY;

				int posX = (int) (m_initialOffsetX + delX);
				int posY = (int) (m_initialOffsetY + delY);

				Dimension newOffset = new Dimension(posX, posY);

				layer.setOffset(newOffset);

				ViewPanel.getInstance().repaint();
			}
		}
		else
		{
			Layer layer = InfoPanel.getInstance().getCurrentLayer();
			if (layer != null)
			{
				int x = e.getX();
				int y = e.getY();
				
				if (x > 0 && x < layer.getDimension().width && y > 0 && y< layer.getDimension().height)
				
				layer.brushAt(x, y, BlendingModes.packColor(255, 58, 93, 217));
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (m_mouseDown)
		{
			System.out.println("Mouse down at " + e.getX() + "," + e.getY());
		}
	}

}
