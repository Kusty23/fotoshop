package app;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fotoshop.Layer;

public class ViewMouseListener implements MouseListener, MouseMotionListener
{

	private int m_pressedX, m_pressedY;
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("Click - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		m_pressedX = e.getX();
		m_pressedY = e.getY();
		
		System.out.println("Press - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		System.out.println("Mouse moved from " + m_pressedX + "," + m_pressedY + " to " + e.getX() + "," + e.getY());
		
		int m_releasedX = e.getX();
		int m_releasedY = e.getY();
		
		Layer l = InfoPanel.getInstance().getCurrentLayer();
		if (l != null)
		{
			Dimension curOffset = l.getOffset();
			
			int delX = m_releasedX - m_pressedX;
			int delY = m_releasedY - m_pressedY;
			
			Dimension newOffset = new Dimension((int) (curOffset.getWidth() + delX) , (int) (curOffset.getHeight() + delY));
			
			l.setOffset(newOffset);
			
			System.out.println("Image offset now " + newOffset);
			
			ViewPanel.getInstance().repaint();
		}
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
		//System.out.println("Drag - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		//System.out.println("Move - " + e.getX() + "," + e.getY());
	}

}
